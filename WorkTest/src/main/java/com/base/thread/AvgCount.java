package com.base.thread;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AvgCount {
    static class BaseData {
        String key;
        long value;
        public BaseData(String key,long value){
            this.key=key;
            this.value=value;
        }
    }
    static class BaseAvgCount{
        String key;
        long total;
        long num;
        public BaseAvgCount(String key,long total,long num){
            this.key=key;
            this.total=total;
            this.num=num;

        }
        public String toString(){
            return "key: "+key+"avg: "+total/num;
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<BaseData> data = new ArrayList<>(11000000);
        HashMap<String,BaseAvgCount> result = new HashMap<String,BaseAvgCount>(10000000);
        long sum=0;
        String path="D:\\git\\WhyiseeBench\\zdb\\data\\data.txt";
        System.out.println("Test--------18:27--->:"+path);
        BufferedWriter out=null;
        out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true)));

        for (Integer i=0;i<10000000;i++){
            data.add(new BaseData("a"+i,i));
            if(result.containsKey("a"+i)){
                BaseAvgCount his = result.get("a"+i);
                his.total +=i;
                his.num +=1;
                result.put("a"+i,his);
            }else {
                result.put("a"+i,new BaseAvgCount("a"+i,i,1));

            }
            out.write(new BaseAvgCount("a"+i,i,1)+"\n");

            sum +=i;
        }
        for (Integer i=0;i<1000000;i++){
            data.add(new BaseData("a"+i,i));
            if(result.containsKey("a"+i)){
                BaseAvgCount his = result.get("a"+i);
                his.total +=i;
                his.num +=1;
                result.put("a"+i,his);
            }else {
                result.put("a"+i,new BaseAvgCount("a"+i,i,1));

            }
            out.write(new BaseAvgCount("a"+i,i,1)+"\n");
            sum +=i;
        }



        out.close();

        System.out.println("test==>"+sum);
        System.out.println("test==>"+result.get("a11111"));
    }
}
