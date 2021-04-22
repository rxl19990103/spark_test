package rdd.create_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark001_RDD_Memory {
  def main(args: Array[String]): Unit = {

    //TODO 创建环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //TODO 创建RDD

    //从内存中创建RDD，讲内存中的集合的数据作为处理的数据
    val seq = Seq[Int](1,2,3,4)

    //parallelize：并行
    val rdd = sc.parallelize(seq)
    //val rdd = sc.makeRDD(seq)
    rdd.foreach(println)
//    rdd.collect().foreach(println)
    //TODO 关闭环境
    sc.stop()
  }

}
