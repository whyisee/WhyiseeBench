package com.whyisee.start

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * use for :
 *
 * @author zoukh
 *         Created in:  2020/5/13 15:13
 * @Modified By:
 * @version 1.0
 * @used in: WhyiseeBench
 */
object PageRank {
  def main(args: Array[String]): Unit = {
    val config:SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)
    val links = sc.objectFile[(String ,Seq[String])]("scalaStart/data/text/links").partitionBy(new HashPartitioner(100)).persist()
    var ranks = links.mapValues(v => 1.0)
    for (i <- 0 until 10){
      val contributions = links.join(ranks).flatMap{
        case (pageId ,(links, rank))=>
          links.map(dest => (dest,rank/links.size))
      }
        ranks = contributions.reduceByKey((x,y)=>x+y).mapValues(v => 0.15+0.85*v)
    }
    ranks.foreach(println)
  }
  //val links = sc

}
