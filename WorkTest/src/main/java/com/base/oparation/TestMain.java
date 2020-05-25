package com.base.oparation;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/15 17:16
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TestMain {
    public static void main(String args[]){
        int i = 0b11111;

        String str;
        //i = i & 1;
        System.out.println("Test--------17:28--->:"+0b1000);
        System.out.println("Test--------17:16--->:"+(1) / 8);
        byte b = 0b11 ;
        System.out.println("Test--------17:21--->:"+b);
        System.out.println("Test--------18:00--->:"+"看".compareTo("3"));
        System.out.println("test==>"+"1".chars());
        IntStream in = "试试".chars();
        int[] arr=in.toArray();

//        IntStream in2 = "11233".codePoints();
//        int[] arr2=in2.toArray();
        System.out.println("test==>"+ Arrays.toString(arr));
    }
}
