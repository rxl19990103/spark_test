package persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Persist {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val list = List("Hello Scala", "Hello Spark")

    val rdd = sc.makeRDD(list)

    val flatRDD = rdd.flatMap(_.split(" "))

    val mapRDD = flatRDD.map((_,1))

    val reduceRDD: RDD[(String,Int)] = mapRDD.reduceByKey(_+_)
    reduceRDD.collect().foreach(println)
    println("=========================================")

    val groupRDD = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)


  }
}
