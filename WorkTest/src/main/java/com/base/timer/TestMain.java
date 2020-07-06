package com.base.timer;

import java.math.BigInteger;
import java.util.*;

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

        while (count <= 5) {
            if (isPrime(bigNum)) {
                System.out.println(bigNum);
                count++;
            }
            bigNum = bigNum.add(BigInteger.ONE);
        }

    }
    public static boolean isPrime(BigInteger num) {
        return num.isProbablePrime(1000000000);

    }

}
