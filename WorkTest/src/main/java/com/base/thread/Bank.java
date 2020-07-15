package com.base.thread;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private double[] accouts ;
    private Lock bankLock = new ReentrantLock();


    private static final int DEFAULT_ACCOUT_NUM = 5;
    private static final int DEFAULT_ACCOUT_AMOUNT = 5000;

    public Bank (){
        accouts = new double[DEFAULT_ACCOUT_NUM];
        for (int i = 0; i < accouts.length; i++) {
            accouts[i] = DEFAULT_ACCOUT_AMOUNT;
        }
    }

    public Bank (int num){ accouts = new double[num];}

    public void transfer(int from ,int to ,double amount){
        bankLock.lock();
        try {


        System.out.println("===test===>"+Thread.currentThread()+ Arrays.toString(accouts));
        accouts[from] -= amount;
        System.out.printf("===test===>"+"%10.2f from %d to %d",amount,from,to);
        accouts[to] += amount;
        System.out.printf("===test===>"+"Total Balance: %10.2f%n",getTotalBalance());
        } finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance(){
        double balances = 0.0;
        for (double b : accouts) {
            balances += b;
        }
        return balances;
    }

    public int size(){
        return accouts.length;
    }
}
