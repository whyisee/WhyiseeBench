package com.wytalk.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * use for : 返回两个日期的间隔,可以返回类型: 天数,天数区间,月数,月数区间,年数,年数区间...
 * 输入参数:1.第一个日期,2.第二个日期,3.返回类型
 * 日期格式自动匹配:
 * 2020-12-15
 * 2020-12-15 11:49:12
 * 20201215
 * 20201215114912
 * 2020/12/15
 * 2020/12/15 11:49:12
 * 2020-12
 * 2020/12
 * 202012
 * 处理规则:
 * 1.首先替换掉这些常见的分隔符
 * 2.最终生成 20201215114912 格式的字符串
 * 3.如果不足补充01
 * 4.计算年数取前4位,计算月数取前6位,计算天数取前8位
 *
 * 返回类型:
 * 空-天数
 * d-天数
 * D-天数区间
 * m-月数
 * M-月数区间
 * y-年数
 * Y-年数区间
 * 区间定义规则程序中固定
 * 异常返回ERR
 * 有null或者空返回空
 * @author zoukehui
 * Created in:  2020/12/15 15:07
 * @version 1.0
 * @Modified By:
 * @used in: udfProject
 */
public class DateInterval extends UDF {
    public static String evaluate(String dateStart,String dateEnd ,String resultType) {
        String result="";

        // 处理入参日期
        // 为空
        if(null==dateStart||dateStart.length()==0||null==dateEnd||dateEnd.length()==0){
            return result;
        }
        dateStart=dateStart.replace("-","")
                .replace("/","")
                .replace(":","")
                .replace(" ","");
        dateEnd=dateEnd.replace("-","")
                .replace("/","")
                .replace(":","")
                .replace(" ","");

        int dateStartLength=dateStart.length();
        int dateEndLength=dateEnd.length();
        int startYear=1970;
        int startMonth=01;
        int startDay=01;
        int startHour=00;
        int endYear=1970;
        int endMonth=01;
        int endDay=01;
        int endHour=00;


        // 为奇数/小于4位
        if(dateStartLength%2==1||dateEndLength%2==1||dateStartLength<4||dateEndLength<4){
            result="ERR";
            return result;
        }
        // 补齐/截取, 暂时不处理分秒,只截取到小时-----可能的情况不多呀,直接switch
        switch (dateStartLength){
            case 4:
                startYear=Integer.parseInt(dateStart);
                dateStart=dateStart+"010100";
                break;
            case 6:
                startYear=Integer.parseInt(dateStart.substring(0,4));
                startMonth=Integer.parseInt(dateStart.substring(5,6));
                dateStart=dateStart+"0100";
                break;
            case 8:
                startYear=Integer.parseInt(dateStart.substring(0,4));
                startMonth=Integer.parseInt(dateStart.substring(5,6));
                startDay=Integer.parseInt(dateStart.substring(7,8));
                dateStart=dateStart+"00";
                break;
            case 10:
                startYear=Integer.parseInt(dateStart.substring(0,4));
                startMonth=Integer.parseInt(dateStart.substring(5,6));
                startDay=Integer.parseInt(dateStart.substring(7,8));
                startHour=Integer.parseInt(dateStart.substring(9,10));
                break;
            default:
        }

        switch (dateEndLength){
            case 4:
                endYear=Integer.parseInt(dateEnd);
                dateEnd=dateEnd+"010100";
                break;
            case 6:
                endYear=Integer.parseInt(dateEnd.substring(0,4));
                endMonth=Integer.parseInt(dateEnd.substring(5,6));
                dateEnd=dateEnd+"0100";
                break;
            case 8:
                endYear=Integer.parseInt(dateEnd.substring(0,4));
                endMonth=Integer.parseInt(dateEnd.substring(5,6));
                endDay=Integer.parseInt(dateEnd.substring(7,8));
                dateEnd=dateEnd+"00";
                break;
            case 10:
                endYear=Integer.parseInt(dateEnd.substring(0,4));
                endMonth=Integer.parseInt(dateEnd.substring(5,6));
                endDay=Integer.parseInt(dateEnd.substring(7,8));
                endHour=Integer.parseInt(dateEnd.substring(9,10));
                break;
            default:
        }

        // 处理返回类型 resultType
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        int dYear=endYear-startYear;
        int dMonth=dYear*12+endMonth-startMonth;
        long dDay= 0;
        try {
            dDay = ((dateFormat.parse(dateEnd.substring(0,8)).getTime()-dateFormat.parse(dateStart.substring(0,8)).getTime())/(1000*60*60*24));
        } catch (ParseException e) {
            e.printStackTrace();
            dDay = -1;
        }
        int dHour=endHour-startHour;

        if(resultType.equals("y")){
            result=dYear+"";
        }else if(resultType.equals("Y")){
            //0，1-5，6-10，11-14，15及以上
            if(dYear==0){
                result="0";
            }else if(dYear>0&&dYear<=5){
                result="1-5";
            }else if(dYear>6&&dYear<=10){
                result="6-10";
            }else if(dYear>11&&dYear<=14){
                result="11-14";
            }else if(dYear>14){
                result="15及以上";
            }
        }else if(resultType.equals("m")){
            result=dMonth+"";
        }else if(resultType.equals("M")){
            //0M,1M,2M,3M,4M-6M,6M-12M,12M+
            if(dMonth==0){
                result="0M";
            }else if(dMonth==1){
                result="1M";
            }else if(dMonth==2){
                result="2M";
            }else if(dMonth==3){
                result="3M";
            }else if(dMonth>=4&&dMonth<6){
                result="4M-6M";
            }else if(dMonth>=6&&dMonth<12){
                result="6M-12M";
            }else if(dMonth>=12){
                result="12M+";
            }
        }else if(resultType.equals("d")){
            result=dDay+"";
        }else if(resultType.equals("D")){
            result=dDay+"";
        }else {
            result="ERR";
        }
        return result;
    }

    public static String evaluate(String dateStart,String dateEnd )  {
        return evaluate(dateStart,dateEnd,"d");
    }
    public static void main (String args[]){
        System.out.println("Test--------15:51--->:"+evaluate("2020-12-11","20201215"));
        System.out.println("123456".substring(2,4));

    }

}
