package com.base.thread.lock;

/**
 * use for : 银行账户对象
 *
 * @author zoukh
 * Created in:  2020/9/25 17:32
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Account {
    private String id ;
    private String name ;
    private double balance;

    public int compareTo(double amount){
        //0=相等,1=大于,-1=小于
        int resoult=0;
        if (this.balance < amount){
            resoult = -1;
        }else if (this.balance == amount){
            resoult = 0;
        }else {
            resoult = 1;
        }
        return resoult;
    }

    public void debit(double amount){
        this.balance-=amount;
    }

    public void credit(double amount){
        this.balance+=amount;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
