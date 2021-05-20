package persist

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Spark03_RDD_Partition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(
      ("nba", "xxxxxxxxxx"),
      ("cba", "xxxxxxxxxx"),
      ("wnba", "xxxxxxxxxx"),
      ("nba", "xxxxxxxxxx")
    ), 3)

    val partRDD = rdd.partitionBy(new MyPartitioner)
    partRDD.saveAsTextFile("file:\\E:\\workspace\\scala\\spark_test\\datas\\output")
    sc.stop()
  }

  /*自定义分区器
  * 1.继承Partitioner
  * 2.重写方法
  **/
  class MyPartitioner extends Partitioner {

    //分区数量
    override def numPartitions: Int = 3

    //根据数据的key值返回数据的分区索引（从0开始）
    override def getPartition(key: Any): Int = {

      key match {
        case "nba" => 0
        case "wnba" => 1
        case _ => 2
      }
    }
  }
}