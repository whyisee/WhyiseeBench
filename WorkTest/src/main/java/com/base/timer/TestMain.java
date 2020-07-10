package com.base.timer;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestMain {


    public static void main(String[] args){
        //Timer timer = new Timer(false);
        //timer.schedule(new MyTimerTask(),2000L);
        int a=1;
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE >> 1);
        System.out.println(Long.MAX_VALUE >>1  > Long.MAX_VALUE +1 >>1 );
        System.out.println("===test===>"+isPrime(new BigInteger("7")));
        BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");
        bigNum = bigNum.add(BigInteger.ONE);
        System.out.println("===test===>"+isPrime(bigNum));
        int count = 1;

/*        while (count <= 5) {
            if (isPrime(bigNum)) {
                System.out.println(bigNum);
                count++;
            }
            bigNum = bigNum.add(BigInteger.ONE);
        }*/

        boolean taskFired;
        if (taskFired = (1<=2)) {
            System.out.println("===test===>"+111);
        }else {
            System.out.println("===test===>"+222);

        }
        System.out.println("===test===>"+taskFired);
        //ScheduledThreadPoolExecutor;

        String str1 = "hello2";
        final String str2 = "hello";
        String str3 = "hello";

        String str4 = str2+2;
        String str5 = str3+2;
        System.out.println("===test===>"+(str1 == str4));
        System.out.println("===test===>"+(str2 == str3));
        System.out.println("===test===>"+(str2 == str4));
        System.out.println("===test===>"+(str1 .equals( str5)));

    }
    public static boolean isPrime(BigInteger num) {
        return num.isProbablePrime(1000);

    }

}
