package com.bigdata.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class MaxTemperatureReducer implements Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text text, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int maxValue = Integer.MIN_VALUE;
      //  Iterator iterator=values.
        while ( values.hasNext()) {
            IntWritable value = values.next();
            System.out.println("==============>"+value);
            maxValue=Math.max(maxValue,value.get());

        }
        outputCollector.collect(text,new IntWritable(maxValue));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}
