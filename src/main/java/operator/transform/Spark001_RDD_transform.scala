package operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark001_RDD_transform {
  def main(args: Array[String]): Unit = {

      //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
   // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)
      //TODO 创建RDD
    val rdd = sc.makeRDD(List(1,2,3,4,5,6),1)

    val mapRDD = rdd.map(
      num => {
        println(">>>>>>" + num)
      }
    )

    val mapRDD1 = rdd.map(
      num => {
        println("++++++" + num)
      }
    )

      //TODO 关闭环境
    sc.stop()
  }
}
