package com.whyisee.spark.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/11 11:27
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class AvgCount implements Serializable {
    public int total;
    public int num;
    public AvgCount(int total,int num){
        this.num = num;
        this.total = total;
    }
    public float avg(){
        return total/(float)num;
    }
    public String toString(){
        return "avg:"+avg()+" total:"+total+" num:"+num;
    }
    public static void main(String args[]){
        Function<Integer,AvgCount> createAcc = new Function<Integer, AvgCount>() {
            @Override
            public AvgCount call(Integer integer) {
                return new AvgCount(integer,1);
            }
        };

        Function2<AvgCount,Integer,AvgCount> addAndCount = new Function2<AvgCount, Integer, AvgCount>() {
            @Override
            public AvgCount call(AvgCount avgCount, Integer integer) throws Exception {
                avgCount.num += 1;
                avgCount.total += integer;
                return avgCount;
            }
        };

        Function2<AvgCount,AvgCount,AvgCount> combine = new Function2<AvgCount, AvgCount, AvgCount>() {
            @Override
            public AvgCount call(AvgCount avgCount, AvgCount avgCount2) throws Exception {
                avgCount.total += avgCount2.total;
                avgCount.num += avgCount2.num;
                return avgCount;
            }
        };

        SparkConf conf = new SparkConf().setAppName("AvgCount");
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        System.setProperty("user.name", "hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);
        if (args.length<2){
            System.out.println("Usage:need inputPath outputPath");
            System.exit(9);
        }
        String inPath = args[0];
        String outPath = args[1];
        JavaRDD<String> input = sc.textFile(inPath);

        //inPath="hdfs://node01:9000/tmp/zoo.txt";
        // List<String> ls= Arrays.asList("a,1","a,2","a,10","a,11","b,111","b,10","c,20","c,10");
        // JavaRDD<String> input = sc.parallelize(ls);



        PairFunction<String,String,Integer> keyData =
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        return new Tuple2(s.split(",")[0],Integer.parseInt(s.split(",")[1]));
                    }
                };
        JavaPairRDD<String,Integer> pairRDD = input.mapToPair(keyData);
        //System.out.println("Test--------17:36--->:"+pairRDD.take(3));
        //JavaPairRDD<String,AvgCount> pairRDD = null;
        //AvgCount initial = new AvgCount(0,0);
        JavaPairRDD<String,AvgCount> avgCounts = pairRDD.combineByKey(createAcc,addAndCount,combine);
        //avgCounts.
        // Map<String,AvgCount> countMap = avgCounts.collectAsMap();
        // for (Map.Entry<String,AvgCount> entry:countMap.entrySet()){
        //     System.out.println("Test--------17:54--->:"+entry.getKey()+":"+entry.getValue().avg());
        // }
        // System.out.println("Test--------17:48--->:"+avgCounts.take(3));
        avgCounts.saveAsTextFile(outPath);

    }
}
