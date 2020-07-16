package com.base.thread;

import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void test() throws InterruptedException {
        Bank bank = new Bank();

        for (int i = 0; i < 100; i++) {
            Runnable runnable = new TransferRunnable(bank);

            Thread t =new Thread(runnable);
            t.start();
        }
        //Thread.sleep(5000);
        System.out.println("===test===>"+Thread.activeCount());

    }


}