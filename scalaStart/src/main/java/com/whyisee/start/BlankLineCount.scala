package com.whyisee.start

import java.util.concurrent.atomic.LongAccumulator

import org.apache.spark.{SparkConf, SparkContext}

/**
 * use for :
 *
 * @author zoukh
 *         Created in:  2020/5/13 16:55
 * @Modified By:
 * @version 1.0
 * @used in: WhyiseeBench
 */
object BlankLineCount {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext (new SparkConf().setMaster("local").setAppName("AvgCount"))
    val file = sc.textFile("F:\\git\\WhyiseeBench\\scalaStart\\data\\links")
    //val blankLines = sc.collectionAccumulator
    val blankLines = sc.accumulator(0)
    //val blankLines = new LongAccumulator(0)
    val callSigns = file.flatMap(line => {
      if (line == ""){
        blankLines += 1
      }
      line.split(" ")
    })
    callSigns.saveAsTextFile("F:\\git\\WhyiseeBench\\scalaStart\\data\\result")
    println("Blank lines :"+blankLines.value)

  }

}
