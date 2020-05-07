package com.base.advance.reflact;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/8 20:30
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class PerformanceTestMain {
    public static void main(String args[]){
        PerformanceMonitor.begin("111");
        System.out.println("Test--------20:31--->:"+111);
        try {
            Thread.currentThread().sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PerformanceMonitor.end();

    }
}
