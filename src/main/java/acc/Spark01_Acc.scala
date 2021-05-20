package acc

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Acc {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("Acc")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    val sumAcc = sc.longAccumulator("sum")

    rdd.foreach(
      num => {
        sumAcc.add(num)
      })

    println(sumAcc.value)
    sc.stop()
  }
}
