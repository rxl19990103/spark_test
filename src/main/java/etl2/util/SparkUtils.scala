package etl2.util

import org.apache.spark.sql.SparkSession

/**
 * 得到以及放回 SparkSession
 */
object SparkUtils {

  private val sparkThread = new ThreadLocal[SparkSession]()

  def set(spark: SparkSession): Unit = {
    sparkThread.set(spark)
  }

  def get(): SparkSession = {
    val spark = sparkThread.get()
    spark
  }

  def remove(): Unit = {
    sparkThread.remove()
  }
}
