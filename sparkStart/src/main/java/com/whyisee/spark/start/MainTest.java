package com.whyisee.spark.start;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/7 12:49
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MainTest {

    public static void main(String args[]){
        SparkConf conf = new SparkConf().setAppName("myApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        String inputFile = args[0];
        String outputFile= args[1];
        JavaRDD<String> input = sc.textFile(inputFile);

        JavaRDD<String> errors = input.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                return s.contains("error");
            }
        });
        errors = input.filter(new ZContains("error"));

        errors = input.filter(s -> s.contains("error"));



        //切分为单词
        JavaRDD<String> words = input.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public Iterator<String> call(String s) throws Exception {
                        return  Arrays.asList(s.split(" ")).iterator();
                    }
                }
        );

        //转换为键值对
        JavaPairRDD<String,Integer> counts = words.mapToPair(
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

        errors.saveAsTextFile(outputFile);
    }
}
