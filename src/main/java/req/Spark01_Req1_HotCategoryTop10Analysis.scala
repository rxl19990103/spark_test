package req

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Req1_HotCategoryTop10Analysis {

  def main(args: Array[String]): Unit = {

    //TODO : Top10热门品类
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark01_Req1_HotCategoryTop10Analysis")
    val sc = new SparkContext(sparkConf)

    //1. 读取原始日志数据

    val actionRDD = sc.textFile("file:\\E:\\workspace\\scala\\spark_test\\datas\\user_visit_action.txt")



    //2. 统计品类的点击数量：（品类ID，点击数量）
    val clinkActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(6) != "-1"
      }
    )

    val clickCountRDD = clinkActionRDD.map(
      action => {
        val datas = action.split("_")
        (datas(6), 1)
      }
    ).reduceByKey(_ + _)


    //3. 统计品类的下单数量：（品类ID，下单数量）
    val orderActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(8) != "null"
      }
    )

    //orderid => 1,2,3
    //【(1,1), (2,1), (3,1)】
    val orderCountRDD = orderActionRDD.flatMap(
      action => {
        val datas = action.split("_")
        val cid = datas(8)
        val cids = cid.split(",")
        cids.map(id => (id, 1))
      }
    ).reduceByKey(_+_)



    //4. 统计品类的支付数量：（品类ID，支付数量）
    val payActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(10) != "null"
      }
    )

    //orderid => 1,2,3
    //【(1,1), (2,1), (3,1)】

    val payCountRDD = payActionRDD.flatMap(
      action => {
        val datas = action.split("_")
        val cid = datas(10)
        val cids = cid.split(",")
        cids.map(id => (id, 1))
      }
    ).reduceByKey(_+_)
    //5. 将品类进行排序，并且取前10名（点击数量的排序，下单数量排序，支付数量排序）

    val cogroupRDD:RDD[(String,(Iterable[Int],Iterable[Int],Iterable[Int]))] =
                    clickCountRDD.cogroup(orderCountRDD,payCountRDD)

    val analysisRDD =  cogroupRDD.mapValues{
      case(clickIter, orderIter, payIter) => {

        var clickCnt = 0
        val iter1 = clickIter.iterator
        if (iter1.hasNext) {
          clickCnt = iter1.next()
        }

        var orderCnt = 0
        val iter2 = orderIter.iterator
        if (iter2.hasNext) {
          orderCnt = iter2.next()
        }

        var payCnt = 0
        val iter3 = payIter.iterator
        if (iter3.hasNext) {
          payCnt = iter3.next()
        }
        (clickCnt,orderCnt,payCnt)
      }
    }

    val resultRDD = analysisRDD.sortBy(_._2, false).take(10)
    //6. 将结果采集到控制台，并打印出来

    resultRDD.foreach(println)


    sc.stop()
  }
}
