package com.base.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("===test===>"+"I am a Runnable "+this.getClass().getName());
    }
}
