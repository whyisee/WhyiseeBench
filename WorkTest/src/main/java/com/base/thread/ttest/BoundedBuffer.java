package com.base.thread.ttest;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Semaphore;

/**
 * use for :    基于信号量的有界缓存
 *
 * @author zoukh
 * Created in:  2020/9/29 15:38
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
@ThreadSafe
public class BoundedBuffer <E>{
    private final Semaphore availableItems ,availableSpaces;
    @GuardedBy("this") private final  E[] items;
    @GuardedBy("this") private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capcity){
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capcity);
        items = (E[]) new Object[capcity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }
    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }
    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }
    private synchronized void doInsert(E x){
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract(){
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length)? 0:i;
        return  x;
    }


}
