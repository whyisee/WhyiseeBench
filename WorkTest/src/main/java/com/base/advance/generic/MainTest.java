package com.base.advance.generic;

import com.sun.istack.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
    public static void main(String args[]) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
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

        Collection<Pair<String,Integer>> table  = new ArrayList<Pair<String, Integer>>();
        addAll(table,pair,pair,pair);
        System.out.println("===test===>"+table);
        Book book = new Book();
        EBook eBook = new EBook();
        NewPair<? extends EBook> bookPair = new NewPair<>(eBook);
        NewPair<? extends EBook> bookPair2 = new NewPair<>();
        //NewPair<? extends Book> bookPair = new NewPair<>(book);
        NewPair<EBook> eBookNewPair = new NewPair<>(eBook);
        NewPair<? super  Book> bookNewPair = new NewPair<>(eBook);
        //bookPair.setFirst(book);
        //printBuddies(bookNewPair);


        Book[] bookArrys = new Book[10];
        for (int i = 0; i < 10; i++) {
            bookArrys[i] = new Book(i*i);
        }

        minMax(bookArrys,bookNewPair);
        System.out.println("===test===>"+bookNewPair);
        NewPair.swap(bookNewPair);
        //NewPair.swapHelper(bookNewPair);

        System.out.println("===test===>"+bookNewPair);
        //bookNewPair.getFirst();
        NewPair<?> newPair = new NewPair<>();
        newPair.setSecond(null);
        //NewPair
        System.out.println("===test===>"+NewPair.hasBull(bookPair2));

        //Class
        NewPair<EBook> cNewPair = NewPair.makeNewPair(EBook.class);
        System.out.println("===test===>"+cNewPair);

        // ArrayDeque
        //LinkedList


    }
    public static <T> void addAll(Collection<T> coll, T ... ts){
        System.out.println("===test===>"+ts.length);
        for (T t: ts) {
            coll.add(t);
        }
    }

    public static void  printBuddies(NewPair<? extends  Book> p){
        Book first = p.getFirst();
        Book second = p.getSecond();
        System.out.println("===test===>"+first.getName()+" ,"+second.getName());
    }

    public static void minMax(Book[] b ,NewPair<? super Book> result){
        if(b == null || b.length==0) return;
        Book min = b[0];
        Book max = b[0];

        for (int i = 1;i < b.length; i++) {
            if(min.getPrice()>b[i].getPrice()) min = b[i];
            if(max.getPrice()<b[i].getPrice()) max = b[i];
        }
        //result.getFirst();
        result.setFirst(min);
        result.setSecond(max);
    }


}
