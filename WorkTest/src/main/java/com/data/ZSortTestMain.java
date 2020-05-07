package com.data;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/10 10:22
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZSortTestMain {
    public static void main(String [] args){
        ZSort zSort=new ZSort();
        int [] a={1,3,4,2,6,5,7,9,8};
        int [] b=Arrays.copyOf(a,9);
        //zSort.bubbleSort(a);
        //zSort.insertSort(a);
        //zSort.shellSort(a);
        zSort.insertSort(a);
        System.out.println("Test--------10:24--->:"+Arrays.toString( zSort.mergeSort(a)));
        System.out.println("Test--------10:24--->:"+Arrays.toString(zSort.quickSort(a)));

    }


}
