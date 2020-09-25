package com.base.thread.threadpool;


import com.data.ZTree;
import com.data.ZTreeNode;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * use for : 递归并发
 *
 * @author zoukh
 * Created in:  2020/9/25 9:42
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Recursive<T> {
    public <T> void sequentialRecursive(List<ZTreeNode<T>> nodes, Collection<T> results){
        for (ZTreeNode<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildrenNodes(),results);
        }
    }


    public <T> void parallelRecursive(final Executor exec,List<ZTreeNode<T>> nodes,final Collection<T>results){
        System.out.println("===test===>"+nodes.isEmpty());
        for (final ZTreeNode<T> n : nodes){
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    results.add(n.compute());
                }
            });
            parallelRecursive(exec,n.getChildrenNodes(),results);

        }
    }

    public <T> Collection<T> getParallelResults(List<ZTreeNode<T>> nodes) throws InterruptedException {
        ExecutorService  exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec,nodes,resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }


}
