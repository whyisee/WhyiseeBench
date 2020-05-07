package com.data;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/12 13:43
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class DataTestMain {
    public  static void main(String [] args){

        ZStackArray zStackArray = new ZStackArray();
        zStackArray.push("aaa");
        zStackArray.push("bbb");
        zStackArray.push("ccc");

        System.out.println("Test--------13:45--->:"+zStackArray.getHead());

    }
}
