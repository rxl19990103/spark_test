package etl2.domain

import org.apache.spark.sql.SparkSession

object Test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("sort").getOrCreate()
    val datas = spark.read.format("json").load("file:\\E:\\workspace\\scala\\spark_test\\json_test\\test0001")

    val data2Json = datas.toJSON
    data2Json.show()

  }
}