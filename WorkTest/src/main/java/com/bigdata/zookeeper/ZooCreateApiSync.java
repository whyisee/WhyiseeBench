package com.bigdata.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooCreateApiSync implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args ) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("hadoop01:2181",5000,
                new ZooCreateApiSync());
        latch.await();
        String path1 = zooKeeper.create("/zk-test-path1","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("test==>"+path1);
        String path2 = zooKeeper.create("/zk-test-path2","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("test==>"+path2);
    }

    @Override
    public void process(WatchedEvent event) {
        latch.countDown();
    }
}
