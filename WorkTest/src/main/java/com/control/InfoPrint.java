package com.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 11:31
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class InfoPrint {


    public static void log(String info) {
        if (ControlConf.log_print.equals("true")){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("【"+dateFormat.format(new Date())+"】: "+info);
        }
    }

    public static void error(String info) {
        if (ControlConf.error_print.equals("true")){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("【ERROR】【"+dateFormat.format(new Date())+"】: "+info);
        }
    }

    public static void error(StackTraceElement[] stackArray) {
        if (ControlConf.error_print.equals("true")){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < stackArray.length; i++) {
                StackTraceElement element = stackArray[i];
                sb.append(element.toString() + "\n");
            }
            System.out.println(sb);
        }
    }



}
