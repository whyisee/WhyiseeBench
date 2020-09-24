package com.base.thread.threadpool;

import java.util.concurrent.ThreadFactory;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 16:54
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName){
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r,poolName);
    }
}
