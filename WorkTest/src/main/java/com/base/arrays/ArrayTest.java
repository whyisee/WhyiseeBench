package com.base.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/7 13:41
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ArrayTest {
    public static void main(String args[]){
        String str="1,2,3";
        List<String> list= Arrays.asList(str.split(","));
        list.iterator();

    }

}
