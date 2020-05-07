package com.bigdata.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZooNodeDealAPI implements Watcher {
    private static  CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    static {
        try {
            zooKeeper = new ZooKeeper("hadoop01:2181",5000,
                    new ZooNodeDealAPI());
/*
            System.out.println("test==>1"+countDownLatch.getCount());
            countDownLatch.await();
            System.out.println("test==>2"+countDownLatch.getCount());
*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void create(String path) throws Exception {
        String path1 = zooKeeper.create(path,"".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("test==>"+path1);
    }
    public void delete(String path) throws Exception {
        zooKeeper.delete(path ,-1);
        //System.out.println("test==>"+path1);
    }

    public List getChildren(String path) throws Exception {
        List list =zooKeeper.getChildren(path ,false);
        return list;
        //System.out.println("test==>"+path1);
    }
    public void  setData(String path,String data) throws Exception {
        zooKeeper.setData(path ,data.getBytes(),-1);
        //return list;
        //System.out.println("test==>"+path1);
    }

    public String  getData(String path) throws Exception {
        //List list =zooKeeper.getChildren(path ,false);
        return new String(zooKeeper.getData(path,false,new Stat()));
        //System.out.println("test==>"+path1);
    }

    public void   exists(String path) throws Exception {
        //List list =zooKeeper.getChildren(path ,false);
        System.out.println("test==>"+zooKeeper.exists(path,true));
        //return new String(zooKeeper.getData(path,false,new Stat()));
        //System.out.println("test==>"+path1);
    }






    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("test==>1"+watchedEvent);
        System.out.println("test==>3"+countDownLatch.getCount());

/*        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            System.out.println("test==>2"+watchedEvent);
            countDownLatch.countDown();
            System.out.println("test==>4"+countDownLatch.getCount());

        }*/
    }
}
