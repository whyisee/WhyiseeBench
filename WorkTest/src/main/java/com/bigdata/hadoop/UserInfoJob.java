package com.bigdata.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;

import java.io.IOException;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/12/3 15:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class UserInfoJob {
    public static void main(String args[]) throws IOException {
        JobConf jobConf=new JobConf();
        jobConf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        jobConf.setJarByClass(UserInfoJob.class);
        MultipleInputs.addInputPath(jobConf,new Path(args[0]),TextInputFormat.class,UserInfoMapper1.class);
        MultipleInputs.addInputPath(jobConf,new Path(args[1]),TextInputFormat.class,UserInfoMapper2.class);
        FileOutputFormat.setOutputPath(jobConf,new Path(args[2]));

        jobConf.setReducerClass(UserInfoReducer.class);
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(Text.class);
        // jobConf.wait();
        JobClient.runJob(jobConf);
        System.exit(0);
    }
}
