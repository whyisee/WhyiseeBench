package com.bigdata.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class MaxTemperatureWithCombiner {
    public static void mian(String args[]){

        JobConf jobConf = new JobConf();
        jobConf.setJarByClass(MaxTemperatureWithCombiner.class);
        jobConf.setJobName("Max Temperature ");
        FileInputFormat.addInputPath(jobConf,new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf,new Path(args[1]));
        jobConf.setMapperClass(MaxTemperatureMapper.class);
        jobConf.setReducerClass(MaxTemperatureReducer.class);
        jobConf.setCombinerClass(MaxTemperatureReducer.class);
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);
        System.exit(0);
    }
}
