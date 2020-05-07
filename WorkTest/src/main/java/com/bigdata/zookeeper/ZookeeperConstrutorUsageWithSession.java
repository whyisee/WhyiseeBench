package com.bigdata.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/29 15:49
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZookeeperConstrutorUsageWithSession implements Watcher {
    private static CountDownLatch countDownLatch= new CountDownLatch(1);
    public static void main(String args[]) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("hadoop01:2181",5000,new ZookeeperConstrutorUsageWithSession());
        countDownLatch.await();

        long sessionId=zooKeeper.getSessionId();
        byte[] passwd=zooKeeper.getSessionPasswd();

        zooKeeper = new ZooKeeper("hadoop01:2181",5000,new ZookeeperConstrutorUsageWithSession(),
                1L,
                "test".getBytes());

        zooKeeper = new ZooKeeper("hadoop01:2181",5000,new ZookeeperConstrutorUsageWithSession(),
                sessionId,
                passwd);
        Thread.sleep(Integer.MAX_VALUE);

        
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("test==>"+watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }

    }
}
