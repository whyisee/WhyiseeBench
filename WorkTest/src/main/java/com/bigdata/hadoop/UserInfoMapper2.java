package com.bigdata.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.HashMap;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/12/3 15:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class UserInfoMapper2  extends MapReduceBase implements Mapper<LongWritable,Text,Text,Text> {
    @Override
    public void map(LongWritable longWritable, Text text2, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
        String line = text2.toString();

        String col1 = line.split(",")[0];
        String col2 = line.split(",")[1];
       // HashMap data=new HashMap<String, String>();
        //data.put("2",col2);
        outputCollector.collect(new Text(col1),new Text(1+"|"+col2));

    }

}
