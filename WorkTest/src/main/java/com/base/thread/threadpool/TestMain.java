package com.base.thread.threadpool;

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


    public static void main(String[] args){
        MyThreadFactory myThreadFactory = new MyThreadFactory("Test");
        for (int i = 0; i < 10; i++) {


        MyAppThread mythread = (MyAppThread) myThreadFactory.newThread(new MyRunnable());
        mythread.setDebug(true);
        mythread.start();
        System.out.println("===test===>"+mythread.getThreadsCreated());

        }
    }
}
