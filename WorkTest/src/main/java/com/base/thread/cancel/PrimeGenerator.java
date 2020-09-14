package com.base.thread.cancel;

import net.jcip.annotations.GuardedBy;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 15:06
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel() { cancelled = true;}
    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }
}
