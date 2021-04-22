package wc

import org.apache.spark.{SparkConf, SparkContext}

object Test01 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("demo")
    val sc = new SparkContext(sparkConf)
    val fileRDD = sc.textFile("input/1.txt")
    val wordRDD = fileRDD.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    val result = wordRDD.collect()
    result.foreach(println)
    sc.stop()
  }
}
