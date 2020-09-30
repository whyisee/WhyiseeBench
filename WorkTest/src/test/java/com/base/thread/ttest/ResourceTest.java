package com.base.thread.ttest;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/30 9:16
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ResourceTest {
    int CAPACITY  = 10;
    class Big { double[] data = new double[100000];}

    public void testLeak() throws InterruptedException {
        BoundedBuffer<Big> bb = new BoundedBuffer<>(CAPACITY);
        //int heapSize1 = System.;

    }
}
