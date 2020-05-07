package com.base.thread;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/19 13:15
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZThread extends Thread {
    private  boolean doFlag ;
    public void run(){
        System.out.println("Test--------13:16--->:"+"thread is run...");

        while (doFlag){
            System.out.println("Test--------13:16--->:"+"thread is run...zzz");
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDoFlag(boolean doFlag){
        this.doFlag=doFlag;
    }

}
