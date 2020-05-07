package com.bigdata.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/12/3 15:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class UserInfoReducer implements Reducer<Text,Text,Text,Text> {
    @Override
    public void reduce(Text text, Iterator<Text> iterator, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
        int num=0;
        String col1="";
        String col2="";
        while (iterator.hasNext()){
            Text hashMap=iterator.next();
            if (num==0){
                //col1=hashMap.get("1")+hashMap.get("2");
                col1=hashMap.toString();
                num++;
            }else{
                col2=hashMap.toString();
                outputCollector.collect(text,new Text(col1+","+col2));
            }
        }
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}
