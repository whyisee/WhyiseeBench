package com.bigdata.zookeeper;

public class MainTest {
    public static void main(String [] args) throws Exception {
        ZooNodeDealAPI zooNodeDealAPI = new ZooNodeDealAPI();
        //zooNodeDealAPI.create("/zoo-path01");
        //zooNodeDealAPI.create("/zoo-path01/zoo-children01");
        System.out.println("test==>"+zooNodeDealAPI.getChildren("/zoo-path01"));
        zooNodeDealAPI.setData("/zoo-path01","123");
        System.out.println("test==>"+zooNodeDealAPI.getData("/zoo-path01"));
        zooNodeDealAPI.exists("/zoo-path01");
    }
}
