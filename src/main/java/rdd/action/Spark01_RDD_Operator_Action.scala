package rdd.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Operator_Action {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)


    val rdd = sc.makeRDD(List(1,2,3,4),2)

    //TODO - 行动算子
    val intToLong = rdd.countByValue()


  }
}
