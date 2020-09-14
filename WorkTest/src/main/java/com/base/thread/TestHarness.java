package com.base.thread;

import java.util.concurrent.CountDownLatch;

/**
 * use for : 闭锁,计算所有线程并发执行完的时长,
 * 同时开始,等待所有完成,通过CountDownLatch控制
 *
 * @author zoukh
 * Created in:  2020/9/14 9:53
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TestHarness {
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(){
                public void run () {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException e){}
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }
}
