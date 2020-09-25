package com.base.thread.threadpool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 14:17
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
@ThreadSafe
public class ValueLatch<T> {
    @GuardedBy("this") private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return (done.getCount() == 0);
    }

    public synchronized void setValue(T newValue){
        if (!isSet()){
            value = newValue;
            done.countDown();
        }
    }
    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this){
            return value;
        }
    }
}
