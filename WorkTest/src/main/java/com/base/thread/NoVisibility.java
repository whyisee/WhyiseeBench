package com.base.thread;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run(){
            while (!ready){
                System.out.println("===test===>"+"no ready");
                Thread.yield();
            }
            System.out.println("===test===>"+number);
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            new ReaderThread().start();
            number = 42;
            ready = true;
        }
        for (int i = 0; i < 100; i++) {
            new ReaderThread().start();
            number = 42;
            ready = true;
        }
    }
}
