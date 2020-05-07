package com.bigdata.zookeeper;

import org.I0Itec.zkclient.ZkClient;

public class ZkClientTest {
    public static void main(String [] xargs){
        ZkClient zkClient=new ZkClient("hadoop01:2181",5000);
        zkClient.createPersistent("/zoo-path-zkc01",true);

    }
}
