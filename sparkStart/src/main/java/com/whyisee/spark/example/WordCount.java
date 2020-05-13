package com.whyisee.spark.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/11 9:39
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class WordCount {
    public static void main(String args[]){
        SparkConf conf = new SparkConf().setAppName("WordCount");
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        System.setProperty("user.name", "hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);
        if (args.length<2){
            System.out.println("Usage:need inputPath outputPath");
            System.exit(9);
        }
        String inPath = args[0];
        String outPath = args[1];
        //inPath="hdfs://node01:9000/tmp/zoo.txt";
        JavaRDD<String> input = sc.textFile(inPath);
        JavaRDD<String> words = input.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return (Arrays.asList(s.split(" ")).iterator());
            }
        });

        JavaPairRDD<String,Integer> result = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2(s,1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });
        //Map<String,Long> result2 = words.countByValue();
        //input.flatMap(x )
        //System.out.println("Test--------9:50--->:"+result2.get("sessionless"));

        //System.out.println("Test--------9:50--->:"+result.take(10));
        result.saveAsTextFile(outPath);

    }


}
