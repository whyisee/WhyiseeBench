package com.bigdata.zookeeper;

import com.base.advance.reflact.MainTest;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import scala.tools.nsc.doc.model.Public;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/29 15:26
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZookeeperConstrutorUsageSimple implements Watcher{
    private static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String args[]) throws IOException, InterruptedException {
        ZooKeeper zookeeper=new ZooKeeper("node01:2182",5000,new ZookeeperConstrutorUsageSimple());
        System.out.println("Test--------15:38--->:"+zookeeper.getState());
        countDownLatch.await();
        System.out.println("Test--------15:41--->:"+"zookeeper session establishd");

    }

    @Override
    public void process(WatchedEvent event){
        System.out.println("Test--------15:42--->:"+"Receive watched event:"+event);
        if(Watcher.Event.KeeperState.SyncConnected==event.getState()){
            countDownLatch.countDown();
        }
    }
}
