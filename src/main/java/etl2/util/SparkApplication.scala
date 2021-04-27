package etl2.util

import org.apache.spark.sql.SparkSession

/**
 * Spark SQL 基础接口，如果需要执行一个Spark SQL job 只需要继承 SparkApplication ，然后并将执行的函数传入到job里面就可以了
 * DEMO:
 * object Demo extends SparkApplication{
 *
 * def main(args: Array[String]): Unit = {
 *
 * job(getClass.getName, "local[4]") {
 * val spark = SparkUtils.get()
 * println("应用程序名称:" + spark.sparkContext.getConf.get("spark.app.name"))
 * println("应用程序master:" + spark.sparkContext.getConf.get("spark.master"))
 * }
 *
 * }
 * }
 */
trait SparkApplication {

  def job(appName: String = null,
          master: String = null)(biz: => Unit): Unit = {
    var spark: SparkSession = null
    if (null == appName) {
      spark = SparkSession.builder.enableHiveSupport.getOrCreate
      SparkUtils.set(spark)
    } else {
      System.setProperty("HADOOP_USER_NAME", "work")
      spark = SparkSession.builder.master(master).appName(appName).enableHiveSupport.getOrCreate
      SparkUtils.set(spark)
    }
    biz
    spark.stop()
    SparkUtils.remove()
  }
}
