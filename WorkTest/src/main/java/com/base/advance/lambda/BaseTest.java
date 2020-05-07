package com.base.advance.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/7 17:25
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class BaseTest {
    public static void test(){
        System.out.println("Test--------18:21--->:"+11);
    }
    public static void main(String args[]){
        String[] strings = {"1","2","3"};
        List<String> num = Arrays.asList(strings);
        for(String str :num){
            System.out.println("Test--------17:30--->:"+str);
        }
        //num.forEach(x -> System.out.println("Test--------17:31--->:"+x));
        num.forEach(x -> test());

        num.forEach(System.out::println);


        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        System.out.println("Test--------18:29--->:"+Arrays.toString(players));
        //players.forEach(System.out::println);
        //num.forEach(System.out::println);


        //Arrays.sort(players);
    }
}
