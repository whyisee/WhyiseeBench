package com.base.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 10:38
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Memoizer2<A,V> implements Computable<A,V> {
    private final Map<A,V> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A,V> c){this.c = c;}
    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null){
            result  = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
