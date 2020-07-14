package com.base.advance.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/14 17:21
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MainTest {
    //private final Collection stamps = ;
    public static void main(String args[]) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Collection
        Pair<String,Integer> pair = new Pair<>("1","2",1);
        Pair<Integer,String> pair22 = new Pair<>(1,1,"1");
        //pair22=pair;
        System.out.println("===test===>"+pair);
        pair.display("11");
        pair.getClass().getMethod("setSecond", Object.class).invoke(pair,"aaa");
        pair.display(pair);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList list2 = new ArrayList<String>();
        ArrayList<String> list3 = new ArrayList<String>();

        //list1.add(1);
        list2.add(1);
        //list3.add(1);

        List<? extends Pair> list4 = new ArrayList<>();

        //list4.add(new DateIntervel());

        List<? super DateIntervel> list5 = new ArrayList<>();
        list5.add(new DateIntervel());
        Pair<String,Integer>[] pair2=new Pair[10];
        Pair[] pair3=new Pair[10];
        Object[] o = pair3;
        pair3[0]=pair;
        pair2[0]=pair;
        o[0]=pair22;
        System.out.println("===test===>"+o[0]+pair3[0]);
        pair2[1]=new Pair<>("1","2",1);
        //pair2[3]="1";
        System.out.println("===test===>"+pair2[0]);
        //list5.add(new Pair());

/*        DateIntervel dateIntervel = new DateIntervel();
        Date date1 = new Date();
        System.out.println("===test===>"+date1);
        Thread.sleep(1000);
        dateIntervel.setFirst(date1);
        Date date2 = new Date();
        dateIntervel.setFirst(date2);


        System.out.println("===test===>"+date1);
        dateIntervel.setSecond(date1);
        System.out.println("===test===>"+dateIntervel);

        Date date3 = new Date();
        dateIntervel.setSecond(date3);

        System.out.println("===test===>"+date3);
        //dateIntervel.setFirst(date3);
        System.out.println("===test===>"+dateIntervel);
        Pair<Date,Date> pair1 = dateIntervel;
        pair1.setSecond(date1);
        System.out.println("===test===>"+pair1);*/
        Object obj = 1;
        int a = (int)obj;

    }

}
