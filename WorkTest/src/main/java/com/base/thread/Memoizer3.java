package com.base.thread;

import java.util.Map;
import java.util.concurrent.*;

import static com.base.thread.ExceptionUtils.launderThrowaber;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 10:50
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Memoizer3<A,V> implements Computable<A,V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
    private final Computable<A,V> c;

    public Memoizer3 (Computable<A,V> c){ this.c = c ;}

    @Override
    public V compute(A arg) throws InterruptedException {
        Future <V> f = cache.get(arg);
        if (f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> futureTask = new FutureTask<V>(eval);
            f = futureTask;
            cache.put(arg,futureTask);
            futureTask.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e){
            throw launderThrowaber(e);
        }
    }
}
