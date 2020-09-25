package com.base.thread.threadpool;

import com.data.ZTree;
import com.data.ZTreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 17:15
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TestMain {


    public static void main(String[] args) throws InterruptedException {
        //MyThreadFactory myThreadFactory = new MyThreadFactory("Test");
/*        for (int i = 0; i < 10; i++) {
        MyAppThread mythread = (MyAppThread) myThreadFactory.newThread(new MyRunnable());
        mythread.setDebug(true);
        mythread.start();
        System.out.println("===test===>"+mythread.getThreadsCreated());
        }*/


/*        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(10);
        TimingThreadPool timingThreadPool = new TimingThreadPool(5, 10,
                6, TimeUnit.MINUTES, arrayBlockingQueue);
        timingThreadPool.execute(new MyRunnable());
        Thread.sleep(1000);
        timingThreadPool.terminated();
        timingThreadPool.shutdown();*/

        ZTree<String> zTree = new ZTree<>();
        ZTreeNode<String> zTreeNode1 = new ZTreeNode<>();
        ZTreeNode<String> zTreeNode11 = new ZTreeNode<>();
        ZTreeNode<String> zTreeNode12 = new ZTreeNode<>();
        ZTreeNode<String> zTreeNode111 = new ZTreeNode<>();
        ZTreeNode<String> zTreeNode112 = new ZTreeNode<>();
        ZTreeNode<String> zTreeNode1111 = new ZTreeNode<>();
        ZTreeNode<String> zTreeNode1112 = new ZTreeNode<>();
        List<ZTreeNode<String>> list1 = new ArrayList<>();
        List<ZTreeNode<String>> list2 = new ArrayList<>();
        List<ZTreeNode<String>> list3 = new ArrayList<>();
        List<ZTreeNode<String>> list4 = new ArrayList<>();
        zTreeNode1.setChildrenNodes(list4);
        zTreeNode11.setChildrenNodes(list4);
        zTreeNode12.setChildrenNodes(list4);
        zTreeNode111.setChildrenNodes(list4);
        zTreeNode112.setChildrenNodes(list4);
        zTreeNode1111.setChildrenNodes(list4);
        zTreeNode1112.setChildrenNodes(list4);

        list1.add(zTreeNode11);
        list1.add(zTreeNode12);
        list2.add(zTreeNode111);
        list2.add(zTreeNode112);
        list3.add(zTreeNode1111);
        list3.add(zTreeNode1112);

        zTreeNode1.setData("1");
        zTreeNode11.setData("11");
        zTreeNode12.setData("12");
        zTreeNode111.setData("111");
        zTreeNode112.setData("112");
        zTreeNode1111.setData("1111");
        zTreeNode1112.setData("1112");
        zTreeNode1.setParentNode(null);
        zTreeNode1.setChildrenNodes(list1);
        zTreeNode11.setParentNode(zTreeNode1);
        zTreeNode11.setChildrenNodes(list2);

        zTreeNode12.setParentNode(zTreeNode1);
        zTreeNode12.setChildrenNodes(list3);

        zTree.setRoot(zTreeNode1);

        Recursive recursive = new Recursive();
        Collection<String> resultQueue = new ConcurrentLinkedQueue<>();
        resultQueue=recursive.getParallelResults(zTreeNode1.getChildrenNodes());
        System.out.println("===test===>"+resultQueue.toString());

        Collection<String> resultQueue2 = new ConcurrentLinkedQueue<>();
        recursive.sequentialRecursive(zTreeNode1.getChildrenNodes(),resultQueue2);
        System.out.println("===test===>"+resultQueue2.toString());


    }
}
