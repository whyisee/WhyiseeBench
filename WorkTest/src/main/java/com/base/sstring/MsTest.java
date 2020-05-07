package com.base.sstring;

import com.data.ZStackArray;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/29 13:20
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MsTest {
    public static void main(String args[]){
        String ss="{[{}]}";
        ZStackArray zStackArray = new ZStackArray();
        for (int i=0;i<ss.length();i++){
            String thisString=ss.substring(i,i+1);
            if (zStackArray.isEmpty()){
                zStackArray.push(thisString);
            }else{
                String lastString=zStackArray.getHead().toString();
                String lastStringR="" ;
                switch (lastString) {
                    case "{":
                        lastStringR = "}";
                        break;
                    case "(":
                        lastStringR = ")";
                        break;
                    case "[":
                        lastStringR = "]";
                        break;
                }

                if (lastStringR.equals(thisString)){
                    zStackArray.get();
                }else {
                    zStackArray.push(thisString);
                }
            }

        }
        if(zStackArray.isEmpty()){
            System.out.println("Test--------10:17--->:"+"Yes");
        }else {
            System.out.println("Test--------10:17--->:"+"No");

        }
        //System.out.println("Test--------10:02--->:"+zStackArray.pop());


    }

}
