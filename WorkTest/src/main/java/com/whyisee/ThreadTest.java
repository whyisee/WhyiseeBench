package com.whyisee;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2018/11/27 17:03
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ThreadTest {
    private static String resouce_a = "A";
    private static String resouce_b = "B";

    public static void main(String[] args) {
        deadLock();
    }

    public static void deadLock() {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resouce_a) {
                    System.out.println("Test--------17:10--->a:");

                try {
                    Thread.sleep(3000);
                    synchronized (resouce_b) {
                        System.out.println("Test--------17:11--->b:");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resouce_b) {
                    System.out.println("Test--------17:36--->b:");
                    synchronized (resouce_a) {
                        System.out.println("Test--------17:36--->a:");
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
