package com.whyisee.toys;

import com.base.annotation.InterfaceAudience;
import com.base.annotation.InterfaceStability;
import com.ctc.wstx.stax.WstxInputFactory;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final Map<String,Properties> propertyTagM = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<String> defaultResources = new CopyOnWriteArrayList<>();
    private static final Map<ClassLoader,Map<String, WeakReference<Class<?>>>> CACHE_CLASSES =
            new WeakHashMap<>();
    private static final Class<?> NEGATIVE_CACHE_SENTINEL = NegativeCacheSentinel.class;

    private static abstract class NegativeCacheSentinel {}

    private volatile Map<String,String[]> updatingResource;
    //xml处理工厂
    private static final WstxInputFactory XML_INPUT_FACTORY =
            new WstxInputFactory();











    {
        TAGS.add("1");
        //finalParameters2.add("2");
    }

    public static Set<String> getTAGS() {
        return TAGS;
    }

    private static class Resource {
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

        public boolean isRestrictParser() {
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
            this.deprecatedKeyMap = UnmodifiableMap.decorate(newDeprecatedKeyMap);
            this.reverseDeprecatedKeyMap = UnmodifiableMap.decorate(newReverseDeprecatedKeyMap);
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
