package operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark013_Operator_transform_AggregateByKey {
  def main(args: Array[String]): Unit = {

    //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
    // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)
    //TODO 创建RDD

    val rdd = sc.makeRDD(List(
      ("a", 1),("a", 2),("a", 3),("a", 4)
    ),2)

    //aggregateByKey存在函数柯里化，有两个参数列表
    //第一个参数列表，需要传递一个参数，表示为初始值
    //                主要用于当碰见第一个key时，和value进行分区内计算
    //第二个参数列表需要传递两个参数
    //                第一个参数表示分区内计算规则
    //                第二个参数表示分区间计算规则


    rdd.aggregateByKey(0) (
      (x,y) => math.max(x,y),
      (x,y) => x + y
    ).collect.foreach(println)
    //TODO 关闭环境
    sc.stop()
  }
}
