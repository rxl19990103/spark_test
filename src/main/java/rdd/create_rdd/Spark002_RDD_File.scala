package rdd.create_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark002_RDD_File {
  def main(args: Array[String]): Unit = {

      //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
      //TODO 创建RDD

    val rdd = sc.textFile("input/1.txt")
    rdd.collect().foreach(print)
      //TODO 关闭环境
    sc.stop()

  }
}
