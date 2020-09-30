package com.base.thread.ttest;
import com.whyisee.toys.Utils;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import static org.junit.Assert.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * use for : 生产者消费者测试类-挺强
 *
 * @author zoukh
 * Created in:  2020/9/29 16:58
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class PutTakeTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials ,nPairs;
    public static void main(String[] args){
        new PutTakeTest(10,10,10000).test();
        pool.shutdown();
    }

    PutTakeTest(int capacity ,int nPairs, int nTrials) {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nPairs = nPairs;
        this.nTrials = nTrials;
        this.barrier = new CyclicBarrier(nPairs* 2+1);
    }
    public void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await();
            barrier.await();
            assertEquals(putSum.get(),takeSum.get());
            System.out.println("===test===>"+putSum.get());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            try {
                int seed = (this.hashCode()^(int)System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i >0 ; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = Utils.xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i){
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
