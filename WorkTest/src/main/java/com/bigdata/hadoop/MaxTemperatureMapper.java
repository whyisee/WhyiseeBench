package com.bigdata.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class MaxTemperatureMapper extends MapReduceBase implements Mapper <LongWritable,Text, Text, IntWritable> {
    @Override
    public void map(LongWritable longWritable, Text text, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        String line = text.toString();
        String year=line.split(",")[0];
        String value=line.split(",")[1];
        System.out.println(value);
        outputCollector.collect(new Text(year), new IntWritable(Integer.parseInt(value)));

    }
}
