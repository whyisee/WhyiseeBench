package com.base.thread.lock;

import net.jcip.annotations.ThreadSafe;

/**
 * use for : 锁分段技术
 *
 * @author zoukh
 * Created in:  2020/9/29 14:44
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
@ThreadSafe
public class StripedMap {
    private static final int N_LOCKS = 16;
    private final Node[] buckets ;
    private final Object[] locks;

    private static class Node {
        private Object key;
        private Object value;
        private Node nextNode;
        public Node next(){
            return nextNode;
        }
    }

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode()% buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash%N_LOCKS]){
            for  (Node m = buckets[hash];m!=null;m = m.next()){
                if (m.key.equals(key)){
                    return m.value;
                }
            }
        }
        return null;
    }

    public void lear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i%N_LOCKS]){
                buckets[i] = null;
            }
        }
    }


}
