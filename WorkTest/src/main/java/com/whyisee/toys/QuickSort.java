package com.whyisee.toys;

import java.util.Arrays;
import java.util.Random;

/**
 * use for : 最漂亮的快速排序
 *
 * @author zoukh
 * Created in:  2020/5/22 15:54
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class QuickSort {
    int[] SourceData;
    int comps = 0;
    float avgTimes = 0.0f;
    public QuickSort(int[] data){
        SourceData = data;
    }

    public void swap(int i,int j){
        int tmp = SourceData[i];
        SourceData[i] = SourceData[j];
        SourceData[j] = tmp;
    }
    //原始版 3-1
    public void quickSort1(int begin,int end){
        int i,m;

        if (begin >= end){
            return;
        }
        //没明白干啥用的
        swap(begin, new Random().nextInt(end-begin)+begin);
        //如果不交换的话,每次都是从第一个开始比较
        m = begin;
        for (i = begin+1; i <=end; i++){
            if (SourceData[i]<SourceData[begin]){
                swap(++m,i);
            }
        }
        swap(begin,m);
        quickSort1(begin,m-1);
        quickSort1(m+1,end);
    }
    public void mySort1(){
        //for ()
        {

        }
    }

    // 3-2
    public void quickSort2(int begin,int end){
        int i,m;

        if (begin >= end){
            return;
        }
        //没明白干啥用的
        //swap(begin, new Random().nextInt(end-begin)+begin);
        m = begin;
        for (i = begin+1; i <=end; i++){
            comps++;
            if (SourceData[i]<SourceData[begin]){
                swap(++m,i);
            }
        }
        swap(begin,m);
        quickSort2(begin,m-1);
        quickSort2(m+1,end);
    }

    // 3-3
    public void quickSort3(int begin,int end){
        int i,m;
        if (begin >= end){
            return;
        }
        //没明白干啥用的
        //swap(begin, new Random().nextInt(end-begin)+begin);
        m = begin;
        comps += end-begin;
        for (i = begin+1; i <=end; i++){
            if (SourceData[i]<SourceData[begin]){
                swap(++m,i);
            }
        }
        swap(begin,m);
        quickSort3(begin,m-1);
        quickSort3(m+1,end);
    }

    // 3-4 只统计,不排序
    public void quickSort4(int begin,int end){
        int i,m;
        if (begin >= end){
            return;
        }
        //没明白干啥用的
        //swap(begin, new Random().nextInt(end-begin)+begin);
        //m = begin;
        m = new Random().nextInt(end-begin)+begin;
        comps += end-begin;
        /*for (i = begin+1; i <=end; i++){
            if (SourceData[i]<SourceData[begin]){
                swap(++m,i);
            }
        }*/
        //swap(begin,m);
        quickSort4(begin,m-1);
        quickSort4(m+1,end);
    }

    // 3-5 只统计,不排序,开始魔幻起来了,len 为数组长度
    public void quickSort5(int len){
        int m;
        if( len <=1 ) return;

        m = new Random().nextInt(len);
        comps += len-1;
        quickSort5(m-1);
        quickSort5(len-m);
    }

    // 3-6 只统计,不排序,开始魔幻起来了,len 为数组长度,函数化
    public int quickSort6(int len){
        int m;
        if( len <=1 ) return 0;
        m = new Random().nextInt(len);
        //comps += len-1;
        return len-1 + quickSort6(m-1) + quickSort6(len-m);
    }

    // 3-7 只统计,不排序,开始魔幻起来了,len 为数组长度,函数化
    //平均比较次数
    public int quickSort7(int len){
        int m;
        int sum = 0;
        if( len <=1 ) return 0;
        m = new Random().nextInt(len);
        //comps += len-1;
        sum =  len-1 + quickSort6(m-1) + quickSort6(len-m);
        /*for (int ){

        }*/
        return sum;
    }


    // 3-8 只统计,不排序,开始魔幻起来了,len 为数组长度,函数化
    //平均比较次数
    public int quickSort8(int len){
        int m;
        int sum = 0;
        if( len <=1 ) return 0;
        m = new Random().nextInt(len);
        //comps += len-1;
        sum =  len-1 + quickSort6(m-1) + quickSort6(len-m);
        /*for (int ){

        }*/
        return sum;
    }

    public static void main(String args[]){
        int[] data0 = new int[20] ;
        for (int i = 0; i < 20; i++){
            data0[i] = new Random().nextInt(20);
        }

        int[] data = new int[1000_000_00] ;
        int[] data2 = new int[1000_000_00] ;

        Random rad = new Random();
        for (int i = 0; i < 1000_000_00; i++){
            data[i] = rad.nextInt(1000_000_000);
        }
        for (int i = 0; i < 1000_000_00; i++){
            data2[i] = rad.nextInt(1000_000_000);
        }
        //Math.random();
        //swap(begin, rad.nextInt(end-begin)+begin);

        QuickSort quickSort = new QuickSort(data);
        long beginTime = System.currentTimeMillis();
        System.out.println("Test--------16:36--->:"+beginTime);
        //quickSort.quickSort4(0,data0.length-1);
        quickSort.quickSort1(0,data.length-1);
        long endTime = System.currentTimeMillis();
        System.out.println("Test--------16:36--->:"+endTime+" use:"+(endTime-beginTime));
/*        System.out.println("Test--------16:51--->:"+Arrays.toString(data0)+quickSort.comps);
        
        System.out.println("Test--------17:20--->:"+quickSort.quickSort6(20));*/

        beginTime = System.currentTimeMillis();
        System.out.println("Test--------16:36--->:"+beginTime);
        Arrays.sort(data2);
        endTime = System.currentTimeMillis();
        System.out.println("Test--------16:36--->:"+endTime+" use:"+(endTime-beginTime));

    }
}
