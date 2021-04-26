package operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark011_Operator_transform_sortby {
  def main(args: Array[String]): Unit = {

    //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
    // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)
    //TODO 创建RDD

    val rdd = sc.makeRDD(List(("1", 1), ("11", 2), ("2", 3)),2)
    val sortRDD = rdd.sortBy(
      t => t._1
    )
    sortRDD.collect().foreach(println)
    //TODO 关闭环境
    sc.stop()
  }
}
