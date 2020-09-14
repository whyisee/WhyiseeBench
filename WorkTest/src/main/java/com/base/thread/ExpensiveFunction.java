package com.base.thread;

import java.math.BigInteger;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 10:37
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ExpensiveFunction implements Computable<String , BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
