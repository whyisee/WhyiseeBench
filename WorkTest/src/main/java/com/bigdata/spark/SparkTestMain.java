package com.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;
import scala.util.parsing.combinator.testing.Str;

import java.util.Arrays;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/15 14:35
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class SparkTestMain {
    public static void main(String args []){
        SparkConf conf = new SparkConf().setAppName("wordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);
        String ipath = args[0];
        String opath = args[1];

        JavaRDD<String> input = sc.textFile(ipath);
        JavaRDD<String> word = input.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public Iterator<String> call(String s) throws Exception {
                        Iterator<String> iterator = (Arrays.asList(s.split(" ")).iterator());

                        return  iterator;
                    }
                }
        );

        JavaPairRDD<String,Integer> counts = word.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        return new Tuple2(s,1);
                    }
                }
        ).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });
        counts.saveAsTextFile(opath);

    }
}
