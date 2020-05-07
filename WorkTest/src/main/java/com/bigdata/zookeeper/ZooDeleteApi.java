package com.bigdata.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZooDeleteApi implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);

/*    public static void main(String[] args){
        ZooKeeper zooKeeper = new ZooKeeper("")
    }*/
    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
