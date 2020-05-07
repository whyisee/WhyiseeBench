package com.bigdata.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class MaxTemperature {

    public static void main (String args[]) throws IOException, InterruptedException {

        //Job job = new Job();
        JobConf jobConf=new JobConf();
        jobConf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        jobConf.setJarByClass(MaxTemperature.class);
        jobConf.setJobName("Max temperature");
        FileInputFormat.addInputPath(jobConf,new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf,new Path(args[1]));
        jobConf.setMapperClass(MaxTemperatureMapper.class);
        jobConf.setReducerClass(MaxTemperatureReducer.class);
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);
       // jobConf.wait();
        JobClient.runJob(jobConf);
        System.exit(0);

    }
}
