package com.bigdata.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MaxTemperatureMapperTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void map() throws IOException {
        Text value= new Text("1,222");
        new MapDriver<LongWritable ,Text,Text, IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0),value)
                .withOutput(new Text("1"),new IntWritable(222))
                .runTest();
    }
}