package rdd.create_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark001_RDD_Memory {
  def main(args: Array[String]): Unit = {

    //TODO 创建环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD

    val data = sc.makeRDD("\"nodeID\":\"7b48474b-2c51-4189-9488-502f874ab7c0\",\"operateTime\":\"2021/3/24 18:34:20\",\"nodeName\":\"采摘\",\"blockNo\":\"2632562\",\"nodeHash\":\"F134FBF4674B5055D3827053538FF5ADDBDB2AC1\",\"modifyTime\":\"2021-04-20T18:51:50Z\",\"blockHash\":\"0x50976373d7ed7b148e06cdadada99f8c50f95e1ff5f94967d0261eeea60e26b2\",\"sourceHash\":\"3DA30F39D1A4D3894164DA99EA197C1F5C7CA410\",\"transactionTime\":\"1618915909904400848\",\"createTime\":\"2021-04-20T18:51:51Z\"")

  //  val fen = data.map(


   // data.collect().foreach(print)
    //TODO 关闭环境
   // sc.stop()
  }

}
