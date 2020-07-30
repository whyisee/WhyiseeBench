package com.whyisee.toys;

import org.apache.commons.collections.Unmodifiable;
import org.apache.commons.collections.map.UnmodifiableMap;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ToysTestMain {
    public static void main(String[] args) throws MalformedURLException {
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
        File file = new File("F:\\git\\WhyiseeBench\\WorkTest\\config\\kafka-topic.properties");
        //System.out.println("===test===>"+file.exists());
        //WindowsPath path = new WindowsPath();
        //System.out.println();
        URL url = new URL("http://node04/tmp/hadoop/core-site.xml");
        //InputStream in =
        //zConfiguration.loadResource(properties,new ZConfiguration.Resource(url),false);

        for (Map.Entry entry: properties.entrySet()) {
            System.out.println("===test===>"+entry.toString());
        }

    }
}
