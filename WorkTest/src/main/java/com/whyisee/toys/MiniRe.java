package com.whyisee.toys;

/**
 * use for : 最简单的正则匹配功能
 *
 * @author zoukh
 * Created in:  2020/5/22 14:10
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MiniRe {

    public int match(String regexp,String text){
        if (regexp.substring(0,1).equals("^")){
            return matchhere(regexp.substring(1,regexp.length()),text);
        }
        do {
            if (matchhere(regexp,text)==1){
                return 1;
            }
            text=text.substring(1,text.length());
        } while (!text.isEmpty());

        return 0;
    }

    public int matchhere(String regexp,String text){
        StringBuffer sb = new StringBuffer();
        //sb.
        //System.out.println("Test--------14:40--->:"+regexp+text+"==="+regexp.substring(1,1));

        if (regexp.isEmpty()){
            return 1;
        }
        if (regexp.length()>1&&regexp.substring(1,2).equals("*")){
            //System.out.println("Test--------14:30--->:"+regexp+text);
            return matchstar(regexp.substring(0,1),regexp.substring(2,regexp.length()),text);
        }
        if (regexp.substring(0,1).equals("$")&&regexp.length()==1){
            return text.length()==0 ? 1 : 0;
        }
        if (!text.isEmpty()&&(regexp.substring(0,1).equals(".")||regexp.substring(0,1).equals(text.substring(0,1)))){
            return matchhere(regexp.substring(1,regexp.length()),text.substring(1,text.length()));
        }
        return 0;
    }

    public int matchstar (String c,String regexp,String text){
        //System.out.println("Test--------14:45--->:"+c+regexp+text);
        do {
            if(matchhere(regexp,text)==1){
                return 1;
            }
            text=text.substring(1,text.length());
        }while (!text.isEmpty()&&(c.equals(text.substring(0,1))||c.equals(".")));
        return 0;
    }


    public static void main(String args[]){
        MiniRe mre = new MiniRe();
        //System.out.println("Test--------14:40--->:"+"1$23".substring(1,1));
        System.out.println("Test--------14:37--->:"+mre.match("12*2","11131222"));
    }


}
