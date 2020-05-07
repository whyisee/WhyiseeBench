package com.base.advance;

import org.apache.poi.ss.usermodel.DateUtil;
import scala.tools.nsc.io.MsilFile;

import java.util.Date;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/3/26 11:14
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class SynchronizedTest1 implements Runnable {

    private static int counter = 1;
    @Override
    public synchronized void  run() {
        Date startDate = new Date();
         {
            for (int i=0;i<5;i++){
                System.out.println("Test--------11:21--->:"+"Thread:"+Thread.currentThread().getName()+"--counter--"+counter++);
                System.out.println("Test--------11:22--->:"+"start:"+startDate+"--now--"+new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]){
        SynchronizedTest1 synchronizedTest1 = new SynchronizedTest1();
        SynchronizedTest1 synchronizedTest2 = new SynchronizedTest1();

        Thread thread1=new Thread(synchronizedTest1,"thread-1");
        Thread thread2=new Thread(synchronizedTest1,"thread-2");
        System.out.println("Test--------15:26--->:"+thread1);
        thread1.start();
        thread2.start();


    }
}
