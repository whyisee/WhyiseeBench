package com.whyisee.start

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * use for :
 *
 * @author zoukh
 *         Created in:  2020/5/14 9:17
 * @Modified By:
 * @version 1.0
 * @used in: WhyiseeBench
 */
object StreamingErrorPrint {
  def main(args: Array[String]): Unit = {
    val ssc = new StreamingContext (new SparkConf().setAppName("StreamingErrorPrint"),Seconds(1))
    var lines = ssc.socketTextStream("node01",7777)
    val errorLines = lines.filter(_.contains("error"))

    errorLines.print()


    ssc.start()

    ssc.awaitTermination()
  }
}
