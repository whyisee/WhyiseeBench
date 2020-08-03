package com.whyisee.toys;

import com.base.annotation.InterfaceAudience;
import com.base.annotation.InterfaceStability;
import com.ctc.wstx.api.ReaderConfig;
import com.ctc.wstx.io.StreamBootstrapper;
import com.ctc.wstx.io.SystemId;
import com.ctc.wstx.stax.WstxInputFactory;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.StringInterner;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.poi.ss.formula.functions.T;
import org.apache.velocity.runtime.directive.Parse;
import org.codehaus.stax2.XMLStreamReader2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.lang.ref.WeakReference;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@InterfaceAudience.Private
@InterfaceStability.Stable
public class ZConfiguration implements Iterable<Map.Entry<String,String>> , Writable {

    private static final Logger LOG = LoggerFactory.getLogger(ZConfiguration.class);
    private static final Logger LOG_DEPRECATION = LoggerFactory.getLogger("com.whyisee.toys.ZConfiguration.deprecation");
    //private static final Log LOG_2 = LogFactory.getLog(ZConfiguration.class);


    private static final Set<String> TAGS = ConcurrentHashMap.newKeySet();

    private boolean quietmode = true;
    private static final String DEFAULT_STRING_CHECK = "testingforemptydefaultvalue";
    private static boolean restrictSystemPropsDefault = false;
    private boolean retrictSystemProps = restrictSystemPropsDefault;
    private boolean allowNullValueProperties = false;

    private ArrayList<Resource> resources = new ArrayList<Resource>();
    static final String UNKNOWN_RESOURCE = "Unknown";


    private Set<String> finalParameters = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    //private Set<String> finalParameters2 = ConcurrentHashMap.newKeySet();

    private boolean loadDefaults ;

    private static final WeakHashMap<ZConfiguration,Object> REGISTRY = new WeakHashMap<>();
    private final Map<String,Properties> propertyTagMap = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<String> defaultResources = new CopyOnWriteArrayList<>();
    private static final Map<ClassLoader,Map<String, WeakReference<Class<?>>>> CACHE_CLASSES =
            new WeakHashMap<>();
    private static final Class<?> NEGATIVE_CACHE_SENTINEL = NegativeCacheSentinel.class;

    private static abstract class NegativeCacheSentinel {}

    private volatile Map<String,String[]> updatingResource;
    //xml处理工厂
    private static final WstxInputFactory XML_INPUT_FACTORY =
            new WstxInputFactory();

    private static DeprecationDelta[] defaultDeprecations =
            new DeprecationDelta[]{
                    new DeprecationDelta("topolegy.script.file.name",CommonConfigurationKeys.NET_TOPOLOGY_CONFIGURED_NODE_MAPPING_KEY),
                    new DeprecationDelta("topolegy.script.number.args",CommonConfigurationKeys.NET_TOPOLOGY_CONFIGURED_NODE_MAPPING_KEY)
            };
    //声明原子更新的引用
    //首先这个引用的作用暂时还不清楚,所以看起来比较费劲
    //然后这里面的逻辑也有点绕,1 个 废弃的配置 可能存在  多个 新的配置
    //这个引用指向的是全局的,不可修改的,多个的,废弃的配置上下文 ,如果要在这个引用的基础上新添加配置, 就需要先把原来的配置取出来
    //遍历旧的配置上下文,放到新的中,遍历添加的配置,放到新的中
    //逻辑有点像原来找融合关系
    private static AtomicReference<DeprecationContext> deprecationContext =
            new AtomicReference<>(new DeprecationContext(null,defaultDeprecations));


    private Properties properties;
    private Properties overlay;
    private ClassLoader classLoader;
    {
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = Configuration.class.getClassLoader();
        }
    }






    {
        TAGS.add("1");
        //finalParameters2.add("2");
    }

    public static Set<String> getTAGS() {
        return TAGS;
    }

    public static class Resource {
        private final Object resource;
        private final String name;
        private final boolean restrictParser;

        public Resource(Object resource){
            this(resource,resource.toString());
        }
        public Resource(Object resource,boolean useRestricParser){
            this(resource,resource.toString(),useRestricParser);
        }
        public Resource(Object resource,String name){
            this(resource,name,false);
        }
        public Resource(Object resource,String name ,boolean restrictParser){
            this.resource = resource;
            this.name = name;
            this.restrictParser = restrictParser;
        }

        @Override
        public String toString() {
            return name;
        }

        private static boolean getRestrictParserDefault(Object resource){
            if (resource instanceof String|| !UserGroupInformation.isInitialized()){
                return false;
            }
            UserGroupInformation user;
            try {
                user = UserGroupInformation.getCurrentUser();
            } catch (IOException e) {
                throw new RuntimeException("Unable to determin current user",e);
            }

            return user.getRealUser() != null;
        }

        public Object getResource() {
            return resource;
        }

        public String getName() {
            return name;
        }

        public boolean isRestricted() {
            return restrictParser;
        }
    }

    public static class DeprecationDelta{
        private final String key;
        private final String[] newKeys;
        private final String customMessage;

        DeprecationDelta (String key,String[] newKeys,String customMessage){
            //检查为空,没啥用呀
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(newKeys);
            Preconditions.checkArgument(newKeys.length>0);

            this.key = key;
            this.newKeys = newKeys;
            this.customMessage = customMessage;
        }
        public DeprecationDelta(String key, String newKey,String customMessage){
            this(key,new String[]{newKey},customMessage);
        }
        public DeprecationDelta(String key,String newKey){
            this(key,new String[]{newKey},null);

        }

        public String getKey() {
            return key;
        }

        public String[] getNewKeys() {
            return newKeys;
        }

        public String getCustomMessage() {
            return customMessage;
        }
    }

    private static class DeprecationContext{
        private final Map<String,DeprecatedKeyInfo> deprecatedKeyMap;
        private final Map<String,String> reverseDeprecatedKeyMap;

        DeprecationContext (DeprecationContext other,DeprecationDelta[] deltas){
            HashMap<String,DeprecatedKeyInfo> newDeprecatedKeyMap =
                    new HashMap<>();
            HashMap<String,String> newReverseDeprecatedKeyMap =
                    new HashMap<>();

            if(other != null){
                for(Map.Entry<String,DeprecatedKeyInfo> entry:other.deprecatedKeyMap.entrySet()){
                    newDeprecatedKeyMap.put(entry.getKey(),entry.getValue());
                }
                for(Map.Entry<String,String> entry:other.reverseDeprecatedKeyMap.entrySet()){
                    newReverseDeprecatedKeyMap.put(entry.getKey(),entry.getValue());
                }
            }

            for (DeprecationDelta delta : deltas){
                if (!newDeprecatedKeyMap.containsKey(delta.getKey())){
                    DeprecatedKeyInfo newKeyInfo = new DeprecatedKeyInfo(delta.getNewKeys(),delta.getCustomMessage());
                    newDeprecatedKeyMap.put(delta.key,newKeyInfo);

                    for (String newKey : delta.getNewKeys()){
                        newReverseDeprecatedKeyMap.put(newKey,delta.key);
                    }
                }
            }
            //不允许修改
            this.deprecatedKeyMap = UnmodifiableMap.decorate(newDeprecatedKeyMap);
            this.reverseDeprecatedKeyMap = UnmodifiableMap.decorate(newReverseDeprecatedKeyMap);
            //reverseDeprecatedKeyMap.put()
        }

        public Map<String, DeprecatedKeyInfo> getDeprecatedKeyMap() {
            return deprecatedKeyMap;
        }

        public Map<String, String> getReverseDeprecatedKeyMap() {
            return reverseDeprecatedKeyMap;
        }
    }

    private static class DeprecatedKeyInfo{
        private final String[] newKeys;
        private final String customMessage;
        private final AtomicBoolean accessed = new AtomicBoolean(false);

        DeprecatedKeyInfo(String[] newKeys,String customMessage){
            this.newKeys = newKeys;
            this.customMessage = customMessage;
        }
        private final String getWarningMessage(String key){
            return getWarningMessage(key,null);
        }
        private String getWarningMessage(String key,String source){
            String warningMessage;
            if(customMessage == null){
                StringBuilder message = new StringBuilder(key);
                if(source != null){
                    message.append(" in "+source);
                }
                message.append(" is deprecated, Instead,use ");
                for (int i = 0; i < newKeys.length; i++) {
                    message.append(newKeys[i]);
                    if(i != newKeys.length-1){
                        message.append(", ");
                    }
                }
                warningMessage = message.toString();
            }else {
                warningMessage = customMessage;
            }
            return warningMessage;
        }
        boolean getAndSetAccessed(){
            return accessed.getAndSet(true);
        }
        public void clearAccessed(){accessed.set(false);}
    }





    public static void addDeprecations(DeprecationDelta[] deltas){
        DeprecationContext prev,next;
        do{
            prev = deprecationContext.get();
            next = new DeprecationContext(prev,deltas);
        }while(!deprecationContext.compareAndSet(prev,next));

    }

    @Deprecated
    public static void addDeprecation(String key,String[] newKeys,String customMessage){
        addDeprecations(new DeprecationDelta[] {new DeprecationDelta(key,newKeys,customMessage)});
    }

    public static void addDeprecation(String key ,String newKey,String customMessage){
        addDeprecation(key,new String[]{newKey},customMessage);
    }

    @Deprecated
    public static void addDeprecation(String key ,String[] newKeys){
        addDeprecation(key ,newKeys,null);
    }

    public static void addDeprecation(String key,String newKey){
        addDeprecation(key,new String[]{newKey},null);
    }

    public static boolean isDeprecated(String key){
        return deprecationContext.get().getDeprecatedKeyMap().containsKey(key);
    }

    private static String getDeprecatedKey(String key){
        return deprecationContext.get().getReverseDeprecatedKeyMap().get(key);
    }

    private static DeprecatedKeyInfo getDeprecatedKeyInfo(String key){
        return deprecationContext.get().getDeprecatedKeyMap().get(key);
    }

    //设置废弃的配置属性
    //处理也比较简单
    //先获取当前的属性,再获取废弃的配置的上下文,遍历添加
    //这里有两个当前属性,一个是正常的,一个是被覆盖的
    //获取当前属性的时候有个问题,可能当前的属性还没有加载,所以获取当前属性的流程其实比较复杂
    public void setDeprecatedProperties(){
        DeprecationContext deprecations = deprecationContext.get();
        Properties props = getProps();
        Properties overlay = getOverlay();

        for (Map.Entry<String,DeprecatedKeyInfo> entry : deprecations.getDeprecatedKeyMap().entrySet()) {
            String depKey = entry.getKey();
            if (!overlay.contains(depKey)) {
                for (String newKey : entry.getValue().newKeys) {
                    String val = overlay.getProperty(newKey);
                    if (val != null) {
                        props.setProperty(depKey,val);
                        overlay.setProperty(depKey,val);
                        break;
                    }
                }
            }
        }
    }


    //获取当前属性,当前属性为null的时候需要更新,可能有多个线程同时获取,所有加锁
    //updatingResource,正在更新的资源,先将正在更新的资源保存备份
    //正在更新的资源和被覆盖的配置,
    //加载配置文件
    //如果被覆盖属性map不为null,将被覆盖属性放入当前属性map,
    //如果有备份的真在更新的资源,则将被覆盖的属性放入正在更新的资源
    //非常晕,需要知道被覆盖的属性,和正在更新的资源还在其他什么位置有操作
    protected synchronized Properties getProps() {
        if (properties == null) {
            properties = new Properties();
            Map<String, String[]> backup = updatingResource != null ?
                    new ConcurrentHashMap<>(updatingResource) : null;
            loadResources(properties, resources, quietmode);
            if (overlay != null) {
                properties.putAll(overlay);
                if (backup != null) {
                    for (Map.Entry<Object, Object> item : overlay.entrySet()) {
                        String key = (String) item.getKey();
                        String[] source = backup.get(key);
                        if (source != null) {
                            updatingResource.put(key, source);
                        }
                    }
                }
            }
        }
        return properties;
    }

    private synchronized Properties getOverlay() {
        if (overlay == null){
            overlay = new Properties();
        }
        return overlay;
    }

    //分三部分
    //1.加载默认配置资源
    //2.加载入参指定资源
    //3.增加tag
    //先看增加tag

    private void loadResources(Properties properties,ArrayList<Resource> resources,boolean quiet){
        if (loadDefaults){
            for (String resource : defaultResources){
                loadResource(properties,new Resource(resource,false),quiet);
            }
        }

        for (int i = 0; i < resources.size(); i++) {
            Resource ret = loadResource(properties,resources.get(i),quiet);
            if (ret != null) {
                resources.set(i,ret);
            }
        }
        this.addTags(properties);
    }

    public void addTags(Properties prop) {
        try {
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_TAGS_SYSTEM)) {
                String systemTags = prop.getProperty(CommonConfigurationKeys.HADOOP_TAGS_SYSTEM);
                TAGS.addAll(Arrays.asList(systemTags.split(",")));
            }
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_TAGS_CUSTOM)) {
                String systemTags = prop.getProperty(CommonConfigurationKeys.HADOOP_TAGS_CUSTOM);
                TAGS.addAll(Arrays.asList(systemTags.split(",")));
            }
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_SYSTEM_TAGS)) {
                String systemTags = prop.getProperty(CommonConfigurationKeys.HADOOP_SYSTEM_TAGS);
                TAGS.addAll(Arrays.asList(systemTags.split(",")));
            }
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_CUSTOM_TAGS)) {
                String systemTags = prop.getProperty(CommonConfigurationKeys.HADOOP_CUSTOM_TAGS);
                TAGS.addAll(Arrays.asList(systemTags.split(",")));
            }
        } catch (Exception e) {
            LOG.trace("Error adding tags in configuration");
        }
    }

    //加载资源
    //Resource里面放的是个Object,所以就可以保存任意对象,这里主要处理了InputStream和Properties
    //获取reader,XMLStringReader2
    public Resource loadResource(Properties properties,Resource wrapper,boolean quiet){
        String name = UNKNOWN_RESOURCE;
        try{
            Object resource = wrapper.getResource();
            name = wrapper.getName();
            boolean returnCachedProperties = false;

            if (resource instanceof InputStream){
                returnCachedProperties = true;
            }else if (resource instanceof Properties){
                overlay(properties,(Properties)resource);
            }

            XMLStreamReader2 reader = getStreamReader(wrapper,quiet);
            if (reader == null){
                if (quiet){
                    return null;
                }
                throw new RuntimeException(resource + " not found");
            }
            Properties toAddTo = properties;
            if (returnCachedProperties){
                toAddTo = new Properties();
            }
            List<ParsedItem> items = new Parser(reader,wrapper,quiet).parse();
            for (ParsedItem item : items) {
                loadProperty(toAddTo,item.name,item.key,item.value,item.isFinal,item.sources);
            }
            reader.close();
            if (returnCachedProperties) {
                overlay(properties,toAddTo);
                return new Resource(toAddTo,name,wrapper.isRestricted());
            }
            return null;
        } catch (IOException e) {
            LOG.error("error parsing conf "+name,e);
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            LOG.error("error parsing conf " + name, e);
            throw new RuntimeException(e);
        }
    }

    //获取reader,xml格式,支持的类型有url,字符串-传入的是一个相对的文件路径字符串,这个,输入流-文件流,Path 是完整路径
    //这些类型最终都被转换成了输入流解析
    //再深一步看看parse如何执行
    private XMLStreamReader2 getStreamReader(Resource wrapper, boolean quiet) throws IOException, XMLStreamException {
        Object resource = wrapper.getResource();
        boolean isRestricted = wrapper.isRestricted();
        XMLStreamReader2 reader = null;

        if (resource instanceof URL){
            reader = (XMLStreamReader2)parse((URL)resource,isRestricted);
        } else if (resource instanceof String){
            URL url = getResource((String)resource);
            //System.out.println("===test===>"+url);
            reader = (XMLStreamReader2)parse(url,isRestricted);
        } else if (resource instanceof Path){
            File file = new File(((Path)resource).toUri().getPath()).getAbsoluteFile();
            if (file.exists()){
                if (!quiet){
                    LOG.debug("parsing File " + file);
                }
                reader = (XMLStreamReader2)parse(new BufferedInputStream(new FileInputStream(file)),((Path)resource).toString(),isRestricted);
            }
        } else if (resource instanceof InputStream){
            reader = (XMLStreamReader2)parse((InputStream)resource,null,isRestricted);
        }

        return reader;
    }

    public URL getResource(String name){
        //System.out.println("===test===>"+classLoader+"-"+name);
        //System.out.println("===test===>"+classLoader.getResource(""));
        return classLoader.getResource(name);
    }

    //url类型的多了一步打开流
    private XMLStreamReader parse (URL url,boolean restricted) throws IOException, XMLStreamException {
        if(!quietmode){
            if(LOG.isDebugEnabled()){
                LOG.debug("parsing URL" + url);
            }
        }
        if (url == null){
            return null;
        }
        URLConnection connection = url.openConnection();
        System.out.println("===test===>"+connection);
        if (connection instanceof JarURLConnection){
            connection.setUseCaches(false);
        }

        return parse(connection.getInputStream(),url.toString(),restricted);
    }

    //最后的方法 createSR()
    private XMLStreamReader parse(InputStream is,String systemIdStr,boolean restricted) throws XMLStreamException {
        if (!quietmode){
            LOG.debug("parsing input stream" + is);
        }
        if (is == null){
            return null;
        }
        SystemId systemId = SystemId.construct(systemIdStr);
        ReaderConfig readerConfig = XML_INPUT_FACTORY.createPrivateConfig();
        if (restricted){
            readerConfig.setProperty(XMLInputFactory.SUPPORT_DTD,false);
        }
        return XML_INPUT_FACTORY.createSR(readerConfig,systemId,
                StreamBootstrapper.getInstance(null,systemId,is),false,true);
    }

    private void readTagFromConfig(String attributeValue, String confName ,String confValue,String[] confSource){
        for (String tagStr: attributeValue.split(",")){
            try {
                tagStr = tagStr.trim();
                if (confValue == null) {
                    confValue = "";
                }
                if (propertyTagMap.containsKey(tagStr)) {
                    propertyTagMap.get(tagStr).setProperty(confName,confValue);
                } else {
                    Properties props = new Properties();
                    props.setProperty(confName,confValue);
                    propertyTagMap.put(tagStr,props);
                }
            } catch (Exception ex) {
                LOG.trace("Tag '{}' for property:{} Source:{}",tagStr,confName,confSource,ex);
            }
        }
    }

    private void overlay(Properties to ,Properties from){
        for (Map.Entry<Object,Object> entry: from.entrySet()){
            to.put(entry.getKey(),entry.getValue());
        }
    }

    private void loadProperty(Properties properties,String name ,String attr,String value,boolean finalParameter,String[] source){
        if (value != null || allowNullValueProperties) {
            if (value == null) {
                value= DEFAULT_STRING_CHECK;
            }
            if (!finalParameters.contains(attr)) {
                properties.setProperty(attr,value);
                if (source != null) {
                    putIntoUpdatingResource(attr,source);
                }
            } else {
                checkForOverride(this.properties,name,attr,value);
                if (this.properties != properties) {
                    checkForOverride(properties,name,attr,value);
                }
            }
        }
        if (finalParameter && attr != null) {
            finalParameters.add(attr);
        }
    }

    private static class ParsedItem {
        String name;
        String key;
        String value;
        boolean isFinal;
        String[] sources;

        public ParsedItem (String name,String key,String value,boolean isFinal,String[] source){
            this.name = name;
            this.key = key;
            this.value = value;
            this.isFinal = isFinal;
            this.sources = source;
        }
    }
    private class Parser{
        private final XMLStreamReader2 reader;
        private final Resource wrapper;
        private final String name;
        private final String[] nameSingletonArray;
        private final boolean isRestricted;
        private final boolean quiet;

        DeprecationContext deprecations = deprecationContext.get();

        private StringBuilder token = new StringBuilder();
        private String confName = null;
        private String confValue = null;
        private String confInclude = null;
        private String confTag = null;
        private boolean confFinal = false;
        private boolean fallbackAllowed = false;
        private boolean fallbackEntered = false;
        private boolean parseToken = false;
        private List<String> confSource = new ArrayList<>();
        private List<ParsedItem> results = new ArrayList<>();

        Parser(XMLStreamReader2 reader,Resource wrapper,boolean quiet){
            this.reader = reader;
            this.wrapper = wrapper;
            this.name = wrapper.getName();
            this.nameSingletonArray = new String[]{name};
            this.isRestricted = wrapper.isRestricted();
            this.quiet = quiet;
        }


        //解析入口
        List<ParsedItem> parse() throws XMLStreamException, IOException {
            while (reader.hasNext()){
                parseNext();
            }
            return results;
        }

        private void handleStartElement() throws IOException, XMLStreamException {
            switch (reader.getLocalName()){
                case "property":
                    handleStartProperty();
                    break;

                case "name":
                case "value":
                case "final":
                case "source":
                case "tag":
                    parseToken = true;
                    token.setLength(0);
                    break;
                case "include":
                    handleInclude();
                    break;
                case "fallback":
                    fallbackEntered = true;
                    break;
                case "configuration":
                    break;
                default:
                    break;
            }
        }
        private void handleStartProperty() {
            confName = null;
            confValue = null;
            confFinal = false;
            confTag = null;
            confSource.clear();

            int attrCount = reader.getAttributeCount();
            for (int i = 0; i < attrCount; i++) {
                String  propertyAttr = reader.getAttributeLocalName(i);
                if ("name".equals(propertyAttr)) {
                    confName = StringInterner.weakIntern(reader.getAttributeValue(i));
                } else if ("value".equals(propertyAttr)){
                    confValue = StringInterner.weakIntern(reader.getAttributeValue(i));
                } else if ("final".equals(propertyAttr)) {
                    confFinal = "true".equals(reader.getAttributeValue(i));
                } else if ("source".equals(propertyAttr)){
                    confSource.add(StringInterner.weakIntern(reader.getAttributeValue(i)));
                } else if ("tag".equals(propertyAttr)){
                    confTag = StringInterner.weakIntern(reader.getAttributeValue(i));
                }
            }
        }
        private void handleInclude() throws IOException, XMLStreamException {
            confInclude = null;
            int attrCount = reader.getAttributeCount();
            List<ParsedItem> items ;
            for (int i = 0; i < attrCount; i++) {
                String attrName = reader.getAttributeLocalName(i);
                if ("href".equals(attrName)){
                    confInclude = reader.getAttributeValue(i);
                }
            }
            if (confInclude == null){
                return;
            }
            if (isRestricted){
                throw new RuntimeException("Error parsing resource "+ wrapper + ": XInclude is not supported for restricted resources");
            }
            URL include = getResource(confInclude);
            if (include != null) {
                Resource classpathResource = new Resource(include,name,wrapper.isRestricted());
                synchronized (ZConfiguration.this){
                    XMLStreamReader2 includeReader = getStreamReader(classpathResource,quiet);
                    if (includeReader == null) {
                        throw new RuntimeException(classpathResource+" not found");
                    }
                    items = new Parser(includeReader,classpathResource,quiet).parse();
                }
            }else {
                URL url;
                try {
                    url = new URL(confInclude);
                    url.openConnection().connect();
                }catch (IOException ioe){
                    File href = new File(confInclude);
                    if (!href.isAbsolute()) {
                        File baseFile = new File(name).getParentFile();
                        href = new File(baseFile,href.getPath());
                    }
                    if (!href.exists()){
                        fallbackAllowed = true;
                        return;
                    }

                    url = href.toURI().toURL();
                }
                Resource uriResource = new Resource(url,name,wrapper.isRestricted());
                synchronized (ZConfiguration.this){
                    XMLStreamReader2 includeReader = getStreamReader(uriResource,quiet);
                    if (includeReader == null) {
                        throw new RuntimeException(uriResource + " not found");
                    }
                    items = new Parser(includeReader,uriResource,quiet).parse();
                }
            }
            results.addAll(items);
        }

        void handleEndElement() throws IOException {
            String tokenStr = token.toString();
            switch (reader.getLocalName()){
                case "name":
                    if (token.length() > 0) {
                        confName = StringInterner.weakIntern(tokenStr.trim());
                    }
                    break;
                case "value":
                    if (token.length() > 0) {
                        confValue = StringInterner.weakIntern(tokenStr.trim());
                    }
                    break;
                case "final":
                    confFinal = "true".equals(tokenStr);
                    break;
                case "source":
                    confSource.add(StringInterner.weakIntern(tokenStr.trim()));
                    break;
                case "tag":
                    if (token.length() > 0) {
                        confTag = StringInterner.weakIntern(tokenStr.trim());
                    }
                    break;
                case "include":
                    if (fallbackAllowed && !fallbackEntered){
                        throw new IOException("Fetch fail on include for '" + confInclude + "' with no fallback while loading '"
                        + name +"'");
                    }
                    fallbackEntered = false;
                    fallbackAllowed = false;
                    break;
                case "property":
                    handleEndProperty();
                    break;
                default:
                    break;
            }
        }
        void handleEndProperty(){
            if (confName == null || (!fallbackAllowed && fallbackEntered)){
                return ;
            }
            String[] confSourceArray;
            if(confSource.isEmpty()){
                confSourceArray = nameSingletonArray;
            } else {
                confSource.add("name");
                confSourceArray = confSource.toArray(new String[confSource.size()]);
            }
            if (confTag != null){
                readTagFromConfig(confTag,confName,confValue,confSourceArray);
            }

            DeprecatedKeyInfo keyInfo = deprecations.getDeprecatedKeyMap().get(confName);
            if (keyInfo != null) {
                keyInfo.clearAccessed();
                for (String key : keyInfo.newKeys){
                    results.add(new ParsedItem(name,key,confValue,confFinal,confSourceArray));
                }
            }else {
                results.add(new ParsedItem(name,confName,confValue,confFinal,confSourceArray));
            }
        }

        //解析
        //next()方法返回的是eventType

        void parseNext() throws XMLStreamException, IOException {
            switch (reader.next()){
                case XMLStreamConstants.START_ELEMENT:
                    handleStartElement();
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (parseToken) {
                        char[] text = reader.getTextCharacters();
                        token.append(text,reader.getTextStart(),reader.getTextLength());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    handleEndElement();
                    break;
                default:
                    break;
            }
        }

    }

    private void checkForOverride(Properties properties,String name,String attr ,String value){
        String propertyValue = properties.getProperty(attr);
        if (propertyValue != null && !propertyValue.equals(value)){
            LOG.warn(name+":an attempt to override final parameter: "+ attr+" ; Ignoring.");
        }
    }
    private void putIntoUpdatingResource(String key,String[] value){
        Map<String,String[]> localUR = updatingResource;
        if (localUR == null) {
            synchronized (this){
                localUR = updatingResource;
                if (localUR == null) {
                    updatingResource = localUR = new ConcurrentHashMap<>(8);
                }
            }
        }
        localUR.put(key,value);
    }
    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return null;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
