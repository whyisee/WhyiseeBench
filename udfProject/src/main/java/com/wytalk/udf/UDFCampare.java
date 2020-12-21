package com.wytalk.udf;

import org.apache.hadoop.hive.ql.exec.UDF;


/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/7/9 15:07
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class UDFCampare extends UDF {
    public static int evaluate(String str1,String str2 ,String separete){
        if(null==str1||str1.length()==0||null==str2||str2.length()==0){
            return 0;
        }
        String[] arr1=str1.split(separete);
        String[] arr2=str2.split(separete);

        for (int i=0;i<arr1.length;i++){
            for (int j=0;j<arr2.length;j++){
                if(arr1[i].equals(arr2[j])){
                    return 1;
                }
            }
        }
        return 0;
    }
    public static int evaluate(String str1,String str2 ) {
        return evaluate(str1, str2, ",");
    }
    public static void main (String args[]){
        System.out.println("Test--------15:51--->:"+evaluate("111:2,222:3","111:3,111:2"));
    }


}
