package com.whyisee.toys;

import org.apache.commons.collections.Unmodifiable;
import org.apache.commons.collections.map.UnmodifiableMap;

import java.util.HashMap;
import java.util.Map;

public class ToysTestMain {
    public static void main(String[] args){
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

    }
}
