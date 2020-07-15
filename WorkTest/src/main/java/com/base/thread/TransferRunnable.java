package com.base.thread;

public class TransferRunnable implements Runnable {
    private Bank bank;


    private static final int maxAmout = 100;
    private static final int DELAY = 10;

    public TransferRunnable (Bank bank){this.bank=bank;}

    @Override
    public void run() {

        try {
            int fromAccout = (int) (bank.size()*Math.random());
            int toAccout = (int) (bank.size()*Math.random());
            double amount = maxAmout * Math.random();
            bank.transfer(fromAccout,toAccout,amount);
            Thread.sleep((int)(DELAY*Math.random()));
        } catch (InterruptedException e){

        }
    }
}
