package com.bigdate.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * use for :
 *
 * @author zoukh
 *         Created in:  2020/2/16 15:46
 * @Modified By:
 * @version 1.0
 * @used in: WorkTest
 */
object WordCount {
  def main (args:Array[String]):Unit={
    val config:SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    val sc = new SparkContext(config)
    //println(sc)
    val lines:RDD[String] = sc.textFile("logs\\busi\\busi.log")

    val words:RDD[String] = lines.flatMap(_.split(" "))
    val wordOne:RDD[(String,Int)] = words.map((_,1))
    val wordToSum:RDD[(String,Int)] = wordOne.reduceByKey(_+_)
    val result:Array[(String,Int)]=wordToSum.collect()
    //println(result)
    result.foreach(println)
  }
}
