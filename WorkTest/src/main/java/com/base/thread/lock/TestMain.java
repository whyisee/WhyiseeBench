package com.base.thread.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/29 9:59
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        Account account1 = new Account();
        account1.setBalance(1000);
        Account account2 = new Account();
        account2.setBalance(2000);

        TransferMoney tran = new TransferMoney();
        tran.transferMoney(account1,account2,200);
        System.out.println("===test===>"+account1.getBalance()+account2.getBalance());
        //ConcurrentHashMap
        //Lock
    }
}
