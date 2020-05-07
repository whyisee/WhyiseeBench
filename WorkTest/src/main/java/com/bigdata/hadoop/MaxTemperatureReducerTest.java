package com.bigdata.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MaxTemperatureReducerTest {

    @Test
    public void reduce() throws IOException {
        new ReduceDriver<Text , IntWritable,Text,IntWritable>()
                .withReducer(new MaxTemperatureReducer())
                .withInput(new Text("1"), Arrays.asList(new IntWritable(1),new IntWritable(2)))
                .withOutput(new Text("1"),new IntWritable(2))
                .runTest();
    }

    @Test
    public void close() {
    }

    @Test
    public void configure() {
    }
}