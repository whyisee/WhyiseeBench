package com.base.thread;

public class BankTest {
    public static void main(String[] args){
        Bank bank = new Bank();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new TransferRunnable(bank);

            Thread t =new Thread(runnable);
            t.start();
        }
        //Thread.sleep(5000);
        System.out.println("===test===>"+Thread.activeCount());
    }
}
