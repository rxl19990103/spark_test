package operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark002_Operator_transform_Test {
  def main(args: Array[String]): Unit = {

      //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
   // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)
      //TODO 创建RDD
    val rdd = sc.makeRDD(List(List(1,2),3, List(4,5)))

    val flatRDD = rdd.flatMap(
      data => {
        data match {
          case list:List[_] => list
          case dat => List(dat)
        }
      }
    )
    flatRDD.collect().foreach(println)
      //TODO 关闭环境
    sc.stop()
  }
}
