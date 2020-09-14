package com.base.thread;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 10:35
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
