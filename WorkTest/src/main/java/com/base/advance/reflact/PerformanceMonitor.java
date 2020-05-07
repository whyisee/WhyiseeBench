package com.base.advance.reflact;

import java.lang.reflect.Method;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/8 20:13
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class PerformanceMonitor {
    private static ThreadLocal<MethodPerformance> performaceThreadLocal = new ThreadLocal<MethodPerformance>();

    public static void begin(String method){
        System.out.println("Test--------20:27--->:"+"begin monitor...");
        MethodPerformance mp = new MethodPerformance(method);
        performaceThreadLocal.set(mp);
    }

    public static void end(){
        System.out.println("Test--------20:28--->:"+"end monitor...");
        MethodPerformance mp=performaceThreadLocal.get();
        mp.printPerformance();
    }
}
