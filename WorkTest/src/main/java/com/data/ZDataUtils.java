package com.data;

/**
 * use for : 一些和数据结构相关的小工具
 *
 * @author zoukh
 * Created in:  2020/3/26 10:21
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZDataUtils {
    
    /**
     * use for : 括号匹配
     *@author zoukh
     *@Created in:  2020/3/26 10:24
     *@Modified By:
     *@version 1.0
     *@used in: ZDataUtils
     */
    public void bracketMatch(String ss){
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
    }
}
