package com.base.sstring;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/7/9 14:46
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class STestMain {
    public  static void main(String[] args){
        StringUtils stringUtils = new StringUtils();
        System.out.println("Test--------14:48--->:"
                +stringUtils.compareStringBySeparate("",""));

        String str = "echo 123";
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i])+ " ";
        }
        System.out.println(str.getBytes());
    }
}
