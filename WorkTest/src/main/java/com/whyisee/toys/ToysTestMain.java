package com.whyisee.toys;

public class ToysTestMain {
    public static void main(String[] args){
        ZConfiguration zConfiguration = new ZConfiguration();
        System.out.println("===test===>"+zConfiguration.getTAGS());
        ZConfiguration.DeprecationDelta deprecationDelta = new ZConfiguration.DeprecationDelta("", new String[] {},"111");

    }
}
