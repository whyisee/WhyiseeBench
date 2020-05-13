package com.whyisee.spark.start;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/11 9:11
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class PairRDDDeal {
    public static void main(String args[]){
        SparkConf conf = new SparkConf().setMaster("local")
                .setAppName("PairRDDDeal");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> ls= Arrays.asList("123 1","231 2");
        JavaRDD<String> rdd = sc.parallelize(ls);
       // JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1,2,3,4));

        PairFunction<String,String,String> keyData =
        new PairFunction<String, String, String>() {
            @Override
            public Tuple2<String, String> call(String s) throws Exception {
                return new Tuple2(s.split(" ")[0],s.split(" ")[1]);
            }
        };

        JavaPairRDD<String,String> pairRDD = rdd.mapToPair(keyData);
        System.out.println("Test--------9:26--->:"+pairRDD.take(3));

        //JavaPairRDD<String,String> pairRDD2 = new JavaPairRDD<>();
    }
}
