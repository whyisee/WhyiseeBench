package com.base.thread;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/19 17:03
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class VolatileTest3 {
    static class Work {
          boolean  isShutDown = false;

        void shutdown() {
            isShutDown = true;
            System.out.println("shutdown!");
        }

        void doWork() {
            while (!isShutDown) {
                System.out.println("doWork");
            }
        }
    }

    public static void main(String[] args) {
        Work work = new Work();

        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::shutdown).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
    }
}