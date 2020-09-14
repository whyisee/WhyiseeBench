package com.base.thread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * use for : 使用Semaphore为容器设置边界
 *
 * @author zoukh
 * Created in:  2020/9/14 10:16
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class BoundedHashSet <T> {
    private final Set<T> set;
    private final Semaphore sem;
    public BoundedHashSet (int bound){
        this.set = Collections.synchronizedSet((new HashSet<>()));
        sem = new Semaphore(bound);
    }
    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded){
                sem.release();
            }
        }
    }

    public boolean remove (Object o){
        boolean wasRemoved = set.remove(o);
        if (wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }
}
