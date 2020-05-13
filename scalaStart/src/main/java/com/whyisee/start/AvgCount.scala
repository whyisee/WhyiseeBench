package com.whyisee.start

import org.apache.spark.{SparkConf, SparkContext}

/**
 * use for :
 *
 * @author zoukh
 *         Created in:  2020/5/13 15:53
 * @Modified By:
 * @version 1.0
 * @used in: WhyiseeBench
 */
object AvgCount {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext (new SparkConf().setMaster("local").setAppName("AvgCount"))
    val input = sc.wholeTextFiles("file:///F:\\git\\WhyiseeBench\\scalaStart\\data\\text")
    val result = input.mapValues { y =>
      val nums = y.split(" ").map(x => x.toDouble)
      nums.sum/nums.size.toDouble
    }
    println(result)
    result.foreach(println)

  }

}
