package com.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 11:32
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ControlConf {
    public static String log_print="true";
    public static String error_print="true";
    public static int max_process =10;
    public static int max_thread =10;
    public static String reload_flowinfo="true";
    public static int flow_redotimes=1;
    public static int node_redotimes=1;
    public static int main_sleep=3;
    public static String alarm_phones="";
    public static String flowinfo_print="false";

    public static String udf_path="";
    public static String hive_db="HIVE";
    public static String queue_name="";
    public static String queue_name1="";
    public static String queue_name2="";
    public static String queue_name3="";
    public static String queue_name0="";

    public static String DRIVER="";
    public static String URL="";
    public static String USERNAME="";
    public static String PASSWORD="";


    public static String warehouse="";

    private static String application = "/conf/application.prop";


    private static Properties prop = new Properties();

    public ControlConf(){
        readConf();
    }

    public static int readConf() {
        int state =0;
        try {
            //这个方法可以从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
            //读取配置信息：
            InfoPrint.log("配置文件路径--->: "+ControlConf.class.getResource(application).getPath().toString());
            InputStream in = ControlConf.class.getResourceAsStream(application); //获取当前类下的地址
//			  InfoPrint.log("读取配置信息--->:"+application);
            prop.load(in); //把读取配置文件的信息存放起来
            in.close();
        } catch (FileNotFoundException e) {
            InfoPrint.error("读取配置信息错误！--->");
            InfoPrint.error(e.toString());
            InfoPrint.error(e.getStackTrace());
            state=-1;
        } catch (IOException e) {
            InfoPrint.error("读取配置信息错误！--->");
            InfoPrint.error(e.toString());
            InfoPrint.error(e.getStackTrace());
            state=-1;
        }
        log_print =prop.getProperty("log_print");
        error_print =prop.getProperty("error_print");
        max_process =Integer.parseInt(prop.getProperty("max_process"));
        max_thread =Integer.parseInt(prop.getProperty("max_thread"));
        reload_flowinfo =prop.getProperty("reload_flowinfo");
        flow_redotimes =Integer.parseInt(prop.getProperty("flow_redotimes"));
        node_redotimes =Integer.parseInt(prop.getProperty("node_redotimes"));
        main_sleep =Integer.parseInt(prop.getProperty("main_sleep"));
        alarm_phones=prop.getProperty("alarm_phones");
        flowinfo_print =prop.getProperty("flowinfo_print");

        udf_path =prop.getProperty("udf_path");
        warehouse=prop.getProperty("warehouse");

        hive_db =prop.getProperty("hive_db");
        queue_name =prop.getProperty("queue_name");
        //#队列1，2，3从大到小
        queue_name1 =prop.getProperty("queue_name1");
        queue_name2 =prop.getProperty("queue_name2");
        queue_name3 =prop.getProperty("queue_name3");
        //#队列0，CreateGroupId.java根据group_id取模用到
        queue_name0 =prop.getProperty("queue_name0");



        DRIVER =prop.getProperty("DRIVER");
        URL =prop.getProperty("URL");
        USERNAME =prop.getProperty("USERNAME");
        PASSWORD =prop.getProperty("PASSWORD");

        return state;
    }
}
