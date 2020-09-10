package com.base.thread;

import org.junit.Test;

import static org.junit.Assert.*;


public class CounterTest {
    @Test
    public void test(){
        Counter counter = new Counter();
        //counter = null;
        //counter = new Counter();
        CounterRunnable counterRunnable = new CounterRunnable();
        //counter.increment();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(counterRunnable);
            thread.start();
        }
        //wait();


        System.out.println("===test===>"+counterRunnable.counter.getValue());
    }
    public  class CounterRunnable implements Runnable{
        Counter counter = new Counter();

        @Override
        public void run() {
            counter.increment();
        }
    }

}