package com.base.thread;

import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void test() throws InterruptedException {
        Bank bank = new Bank();
        Runnable runnable = new TransferRunnable(bank);

        for (int i = 0; i < 500; i++) {
            Thread t =new Thread(runnable);
            t.start();
        }
        Thread.sleep(5000);
        System.out.println("===test===>"+Thread.activeCount());

    }


}