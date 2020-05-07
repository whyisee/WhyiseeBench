package com.data;

//import org.apache.hadoop.hbase.security.User;

import org.apache.velocity.runtime.directive.Foreach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/10/14 15:20
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZDataTestMain {

    public static void main(String [] arg){

/*        System.out.println("Test--------15:49--->:"+System.nanoTime());
        ArrayList<Object> users = new ArrayList(1000000);
        for (int i = 0; i < 1000000; ++i) {
            users.add(i);
        }
        System.out.println("Test--------15:49--->:"+System.nanoTime());

        System.out.println("Test--------15:49--->:"+System.nanoTime());
        ArrayList<Object> users2 = new ArrayList();
        for (int i = 0; i < 1000000; ++i) {
            users2.add(i);
        }*/
        System.out.println("Test--------15:49--->:"+System.nanoTime());
        //java.util.Hashtable.Node;
        Iterator<String> iterator = (Arrays.asList("1 2 3".split(" ")).iterator());
        //iterator.next();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            System.out.println(str);
        }      }

}
