package com.base.thread.cancel;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 9:46
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class PrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    public void run(){
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        }catch (InterruptedException consumed){

        }
    }
    public void cancel() {interrupt();}
}
