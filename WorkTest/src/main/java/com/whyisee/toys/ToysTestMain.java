package com.whyisee.toys;

import com.ctc.wstx.api.ReaderConfig;
import com.ctc.wstx.io.StreamBootstrapper;
import com.ctc.wstx.io.SystemId;
import com.ctc.wstx.stax.WstxInputFactory;
import org.apache.commons.collections.Unmodifiable;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ToysTestMain {
    public static void main(String[] args) throws MalformedURLException, FileNotFoundException, XMLStreamException {
        ZConfiguration zConfiguration = new ZConfiguration();
        System.out.println("===test===>"+zConfiguration.getTAGS());
       // ZConfiguration.DeprecationDelta deprecationDelta = new ZConfiguration.DeprecationDelta("", new String[] {},"111");
        Map<String ,String> myMap = new HashMap<>();
        myMap.put("a","1");
        myMap=UnmodifiableMap.decorate(myMap);
        //myMap.put("b","1");
        //myMap.put("a","2");
        //(Map)(myMap instanceof Unmodifiable ? map : new UnmodifiableMap(map));
        System.out.println(myMap instanceof Unmodifiable );
        Properties properties = new Properties();
        Properties properties2 = new Properties();
        properties2.put("dfs.permissions","123");
        File file = new File("F:\\git\\WhyiseeBench\\WorkTest\\config\\test.xml");
        //System.out.println("===test===>"+file.exists());
        //WindowsPath path = new WindowsPath();
        //System.out.println();
        URL url = new URL("http://node04/tmp/hadoop/core-site.xml");
        String  surl = new String("config/test.xml");
        Path path = Paths.get("F:\\git\\WhyiseeBench\\WorkTest\\config\\test.xml");

        InputStream f = new FileInputStream("F:\\git\\WhyiseeBench\\WorkTest\\config\\test.xml");
        InputStream f2 = System.in;
        ZConfiguration.Resource resource = new ZConfiguration.Resource(properties2) ;

        //System.out.println(resource.getResource() instanceof Properties);

        //InputStream in =
        //zConfiguration.loadResource(properties,new ZConfiguration.Resource(url),false);

        //zConfiguration.loadResource(properties,new ZConfiguration.Resource(surl),false);
        //zConfiguration.loadResource(properties,new ZConfiguration.Resource(f2),false);
        //zConfiguration.loadResource(properties,new ZConfiguration.Resource(properties2),false);

        for (Map.Entry entry: properties.entrySet()) {
            //System.out.println("===test===>"+entry.toString());
        }
        //System.out.println(file instanceof Object);
        //System.out.println("===test===>"+"end");
        WstxInputFactory XML_INPUT_FACTORY =
                new WstxInputFactory();
        ReaderConfig readerConfig = XML_INPUT_FACTORY.createPrivateConfig();
        SystemId systemId = SystemId.construct("1");

        StringBuilder sb = new StringBuilder();

        XMLStreamReader2 reader2 = XML_INPUT_FACTORY.createSR(readerConfig,systemId,
                StreamBootstrapper.getInstance(null,systemId,f),false,true);
        System.out.println("===test===>"+reader2.next());
        System.out.println("===test===>"+reader2.getLocalName());
        System.out.println("===test===>"+reader2.next());
        System.out.println("===test===>"+reader2.getTextCharacters());
        sb.append("00",reader2.getTextStart(),reader2.getTextLength());
        System.out.println("===test===>"+sb);
        System.out.println("===test===>"+reader2.next());
        System.out.println("===test===>"+reader2.getLocalName());
        System.out.println("===test===>"+reader2.next());
        System.out.println("===test===>"+reader2.getLocalName());
        System.out.println("===test===>"+reader2.next());
        System.out.println("===test===>"+reader2.getLocalName());
    }
}
