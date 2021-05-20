package acc

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark01_Bc {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("Acc")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(
      ("a",1),("b",2),("c",3))
    )
//    val rdd2 = sc.makeRDD(List(
//      ("a",4),("b",5),("c",6)
//    ))
    val map = mutable.Map(("a",4),("b",5),("c",6))

    rdd1.map{
      case (w,c) => {
        map.getOrElse(w,0L)
          val l = map.getOrElse(w,0)
        (w,(c,1))
      }
    }.collect().foreach(println)

      sc.stop()
  }
}
