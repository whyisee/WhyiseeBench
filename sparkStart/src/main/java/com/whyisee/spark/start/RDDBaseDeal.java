package com.whyisee.spark.start;

import org.apache.hadoop.util.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.Arrays;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/9 13:52
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class RDDBaseDeal {




    public static void main(String args[]){
/*        SparkConf conf = new SparkConf().setMaster("spark://172.16.210.211:7077")
        //SparkConf conf = new SparkConf().setMaster("local")
                .setAppName("RDDBaseDeal")
                // .setJars(new String[]{"sparkStart/target/sparkStart-0.0.1.jar"})
                 .setIfMissing("spark.driver.host", "192.168.100.21")
                ;
        conf.setExecutorEnv("spark.driver.bindAddress","192.168.100.21");*/
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        System.setProperty("user.name", "hadoop");
        //JavaSparkContext sc = new JavaSparkContext(conf);


        SparkSession sparkSession = SparkSession.builder()
                .master("local")
                //.config("spark.driver.host","192.168.100.21")
                .appName("RDDBaseDeal")
                .getOrCreate();
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        //SparkSession spark = SparkSession.builder();
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1,2,3,4));

        JavaRDD<Integer> result = rdd.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) throws Exception {
                return integer*integer;
            }
        });

        Integer sum = rdd.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        class AvgCount implements Serializable {
            public int total;
            public int num;

            public AvgCount(int total,int num){
                this.num = num;
                this.total = total;
            }
            public double avg(){
                return total/(double)num;
            }

        }
        Function2<AvgCount,Integer ,AvgCount> addAndCount = new Function2<AvgCount, Integer, AvgCount>() {
            @Override
            public AvgCount call(AvgCount avgCount, Integer integer) throws Exception {
                avgCount.total += integer;
                avgCount.num += 1;
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

        AvgCount initAvg = new AvgCount(0,0);
        AvgCount result2 = rdd.aggregate(initAvg,addAndCount,combine);
        System.out.println("Test--------18:13--->:"+result2.avg());

        System.out.println("Test--------17:39--->:"+sum);

        System.out.println("Test--------13:58--->:"+ StringUtils.join(",", result.collect()));
    }

            
}
