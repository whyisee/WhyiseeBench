package com.base.auth2;

import com.base.auth.MyClass;
import com.base.utils.MyEnum;

public class TestMain {
    public static void main(String[] args){
        MyClass mc = new MyClass();
        System.out.println("===test===>"+mc.col1);
        //System.out.println("===test===>"+mc.col2);
        //System.out.println("===test===>"+mc.col3);
        //System.out.println("===test===>"+mc.col4);
        MyEnum ss=MyEnum.MONTH;
        MyEnum ss2=MyEnum.WEEK;
        System.out.println("===test===>"+ss.day);
        if (ss == MyEnum.WEEK){

        }
    }
}
