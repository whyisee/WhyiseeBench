package com.base.thread.ttest;

import com.whyisee.toys.Utils;
import junit.framework.TestCase;

import static org.junit.Assert.*;

public class BoundedBufferTest extends TestCase {
    static final int LOCKUP_DETECT_TIMEOUT = 2000;
    public void testIsEmptyWhenConstructed(){
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertFalse(bb.isEmpty());
        assertTrue(bb.isFull());
    }

    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread() {
            public void run() {
            try {
                int unused = bb.take();
                fail();
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception e) {
            fail();
        }
    }

    public void test(){
        System.out.println("===test===>"+ Utils.xorShift(1));
        System.out.println("===test===>"+ Utils.xorShift(1));
        System.out.println("===test===>"+ Utils.xorShift(1));

    }

}