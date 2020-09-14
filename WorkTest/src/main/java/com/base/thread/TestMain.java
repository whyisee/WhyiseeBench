package com.base.thread;

import java.math.BigInteger;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMain {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.execute(new MyRunnable());
        MyFuture future = new MyFuture();
        future.setResult("111");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,2,1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));
        threadPoolExecutor.execute(new MyRunnable());
        threadPoolExecutor.submit(new MyRunnable());
        threadPoolExecutor.shutdown();
        BlockingQueue<Runnable> workQueue ;
        //workQueue.add();

/*        System.out.println("===test===>"+future.get());

        System.out.println("===test===>COUNT_BITS"+COUNT_BITS);
        System.out.println("===test===>"+CAPACITY);
        System.out.println("===test===>"+RUNNING);
        System.out.println("===test===>"+SHUTDOWN);
        System.out.println("===test===>"+STOP);
        System.out.println("===test===>"+TIDYING);
        System.out.println("===test===>"+TERMINATED);*/

        TestMain tm=new TestMain();
/*        System.out.println("===test===>"+tm.ctl.toString());
        System.out.println("===test===>runStateOf:"+runStateOf(-536870910));
        System.out.println("===test===>"+ctlOf(2,0));
        System.out.println("===test===>"+workerCountOf(161061273));
        System.out.println(-1 << 2);
        System.out.println("===test===>"+Integer.toBinaryString(-1));
        System.out.println("===test===>"+Integer.toBinaryString(-1<<2));
        System.out.println("===test===>"+threadPoolExecutor.isShutdown());*/
        System.out.println("===test===>"+tm.ctl.toString());
        System.out.println("===test===>"+tm.ctl.compareAndSet(-536870912,1+1));
        System.out.println("===test===>"+tm.ctl.toString());

/*        TestHarness testHarness = new TestHarness();
        long useTimes= testHarness.timeTasks(10,new MyRunnable());
        System.out.println("===test===>"+useTimes);
        BoundedHashSet<Bank> bankSet  = new BoundedHashSet<>(2);
        System.out.println(bankSet+"===test===>"+bankSet.add(new Bank()));*/

        //bankSet.toString();

        ExpensiveFunction expensiveFunction = new ExpensiveFunction();
        Memoizer2<String , BigInteger> memoizer2 = new Memoizer2(expensiveFunction);
        System.out.println("===test===>"+memoizer2.compute("1"));

    }
}
