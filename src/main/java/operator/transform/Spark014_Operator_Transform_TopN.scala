package operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark014_Operator_Transform_TopN {
  def main(args: Array[String]): Unit = {

    //1516609143867   6     7     64      16
    //时间戳，      省份， 城市， 用户，  广告

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
    // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)

    val dataRDD = sc.textFile("datas/agent.log")
    val reduceRDD = dataRDD.map(
      line => {
        val datas = line.split(" ")
        ((datas(1), datas(4)), 1)
      }
    )

    //将聚合的结果进行结构转换,使用样例匹配
    //((省份，广告)，sum) => (省份，(广告，sum))
    val newMapRDD = reduceRDD.map{
      case ((prv, ad), sum ) => {
        (prv, (ad, sum))
      }
    }



  }
}
