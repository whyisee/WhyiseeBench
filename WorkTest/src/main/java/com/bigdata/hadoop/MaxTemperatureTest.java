package com.bigdata.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MaxTemperatureTest extends Configured implements Tool {


    public int run(String args []) throws InterruptedException, IOException {
        if (args.length<2){
            System.err.printf("Usage: %s [generic options] <input> <output>",getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        JobConf jobConf = new JobConf(getConf());
        //jobConf.addResource("conf/core-site.xml");
        jobConf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        jobConf.setJarByClass(getClass());
        FileInputFormat.addInputPath(jobConf,new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf,new Path(args[1]));

        jobConf.setMapperClass(MaxTemperatureMapper.class);
        jobConf.setCombinerClass(MaxTemperatureReducer.class);
        jobConf.setReducerClass(MaxTemperatureReducer.class);

        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);
        System.out.println("log==================>"+jobConf);

        JobClient.runJob(jobConf);
        return 0;
    }

    public static void main(String args[]) throws Exception{
        int exitCode = ToolRunner.run(new MaxTemperatureTest(),args);
        System.exit(exitCode);

    }
}