package com.bigdata.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class ZooCreateApiASync implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args ) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("hadoop01:2181",5000,
                new ZooCreateApiASync());
        latch.await();
        zooKeeper.create("/zk-test-path5","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallback(),"xxxx");
        Thread.sleep(Integer.MAX_VALUE);
        //System.out.println("test==>"+path1);
/*        String path2 = zooKeeper.create("/zk-test-path3","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("test==>"+path2);*/
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()){
            latch.countDown();

        }
    }

}


