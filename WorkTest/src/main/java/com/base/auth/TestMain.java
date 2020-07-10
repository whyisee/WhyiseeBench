package com.base.auth;

public class TestMain {
    public static void main(String[] args){
        MyClass mc = new MyClass();
        System.out.println("===test===>"+mc.col1);
        //System.out.println("===test===>"+mc.col2);
        System.out.println("===test===>"+mc.col3);
        System.out.println("===test===>"+mc.col4);
/*
*
其他类访问权限:
 访问修饰符 | 当前类 | 同包 | 子类 | 其他包
 -          | -     | -    | -    | -
public      |可以    |可以  |可以   |可以
protected   |可以    |可以  |可以   |不可以
default     |可以    |可以  |不可以 |不可以
private     |可以    |不可以|不可以 |不可以

子类访问权限
子类中的访问权限|当前类|同包|不同包|
--       |--    |--        |--
public   |可以  |可以       |可以
protected|可以  |可以       |可以
default  |可以  |可以       |不可以
private  |不可以|不可以    |不可以
* */
    }
}
