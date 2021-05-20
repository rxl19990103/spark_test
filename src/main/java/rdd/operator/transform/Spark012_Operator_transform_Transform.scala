package rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark012_Operator_transform_Transform {
  def main(args: Array[String]): Unit = {

    //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
    // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)
    //TODO 创建RDD

    //TODO 双value类型
    val rdd1 = sc.makeRDD(List(1,2,3,4))
    val rdd2 = sc.makeRDD(List(3,4,5,6))
    //TODO 交集
    val rdd3 = rdd1.intersection(rdd2)
    print(rdd3.collect().mkString(","))
    //TODO 并集
    val rdd4 = rdd1.union(rdd2)
    print(rdd4.collect().mkString(","))
    //TODO 差集
    val rdd5 = rdd1.subtract(rdd2)
    print(rdd5.collect().mkString(","))
    //TODO 拉链
    val rdd6 = rdd1.zip(rdd2)
    print(rdd6.collect().mkString(","))

    //TODO 关闭环境
    sc.stop()
  }
}
