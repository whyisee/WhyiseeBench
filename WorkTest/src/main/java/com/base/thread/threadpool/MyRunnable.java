package com.base.thread.threadpool;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 17:16
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("===test===>"+"this is a thread "+Thread.currentThread().getName());
    }
}
