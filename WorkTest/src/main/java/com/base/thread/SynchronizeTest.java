package com.base.thread;

public class SynchronizeTest implements Runnable {
    private static int count = 0;
    public static void main(String[] args){
        for (int i=0;i<1000;i++){
            Thread thead=new Thread(new SynchronizeTest());
            thead.start();
        }
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===test===>"+count);
    }

    @Override
    public void run() {
        System.out.println("===test===>thread start"+Thread.currentThread().getName());
        for (int i = 0;i < 1000; i++){
            synchronized (SynchronizeTest.class) {
                count++;
            }
        }
    }
}
