package com.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/6/18 11:21
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZDateTest {
    public static  void  main (String args[]){
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        Calendar cal = Calendar.getInstance();
        Date nowdate=new Date();
        System.out.println("Test--------11:24--->:"+nowdate);
        System.out.println("Test--------11:24--->:"+sdf.format(nowdate));
        System.out.println("Test--------11:24--->:"+sdf.format(nowdate).toString()+(Integer.parseInt("1906251001".substring(6,10))+1));

    }
}
