package operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark08_Operator_transform_Sample {
  def main(args: Array[String]): Unit = {

    //TODO 准备环境

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")
    // sparkConf.set("spark.default.parallelism", "5")
    val sc = new SparkContext(sparkConf)
    //TODO 创建RDD
    val rdd = sc.makeRDD(List(1,2,3,4,5,6,7,8,9,10))
    // sample算子需要传递三个参数
    // 1. 第一个参数表示：抽取数据后是否数据返回， ture(返回)， false(丢失)
    // 2. 第二个参数表示：数据源中每条数据被抽取的概率
    // 3. 第三个参数表示：抽取数据时随机算法的种子
    //TODO 关闭环境
    sc.stop()
  }
}
