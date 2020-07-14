package com.base.utils;

import org.apache.hadoop.util.StringUtils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 15:39
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ToolsTestMain {
    public static void main(String args[]){
        CommandFormat cf = new CommandFormat(0,200,"t","b","c");
        List<String> slist= new LinkedList<String>();
        slist.add("--a");
        slist.add("111");

        slist.add("-b");
        slist.add("111");
        String[] argv={"put","--","-b","-c" };

        String args2 = StringUtils.join(" ", argv);
        System.out.println("Test--------15:55--->:"+args2);

        String[] args3=Arrays.copyOfRange(argv, 1, argv.length);
        System.out.println("Test--------15:55--->:"+Arrays.toString(args3));
        LinkedList<String> args4 = new LinkedList<String>(Arrays.asList(args3));
        System.out.println("Test--------15:55--->:"+args4.toString());

        cf.parse(args4);
        cf.displayAll();
        System.out.println("Test--------15:47--->:"+cf.getOptValue("t"));
        System.out.println("===test===>"+MyEnum.WEEK.toString());
        System.out.println("===test===>"+ TimeUnit.DAYS.toHours(10000L));

        MyEnum2 enum2 = new MyEnum2();
        enum2.applyStyles(EnumSet.of(MyEnum2.MyEnum3.BOLED,MyEnum2.MyEnum3.IITALIC));
        AuthManager authManager = new AuthManager();
        authManager.addAuth("ALTER",EnumSet.of(AuthManager.AuthType.INSERT,AuthManager.AuthType.DELETE));
        authManager.removeAuth("INSERT",EnumSet.of(AuthManager.AuthType.INSERT,AuthManager.AuthType.DELETE));

    }
}
