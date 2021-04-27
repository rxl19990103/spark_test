package etl2.domain



import java.sql.{Date, Timestamp}
import java.text.SimpleDateFormat

import com.alibaba.fastjson.JSON
import etl2.accumulator.com.jgw.bigdata.spark.AnjiWhiteTea.accumulator.IotDeviceDataAccumulator
import etl2.util.{HttpUtils, SparkApplication, SparkUtils}
import org.apache.spark.internal.Logging
import org.apache.spark.sql.{SaveMode, SparkSession}

import scala.collection.JavaConversions._

object Spark2Hive extends Logging with SparkApplication {
  //    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("RDD")



  def etl(spark: SparkSession): Unit = {
    //    val spark = SparkSession.builder().appName("sort").getOrCreate()
    //    var datas = spark.read.format("json").load("file:\\E:\\workspace\\scala\\spark_test\\json_test\\test0001")
    //
    //    val data2Json = datas.toJSON
    //    data2Json

    import spark.implicits._

    //开启累加器
    val accumulator = new IotDeviceDataAccumulator
    //注册累加器
    spark.sparkContext.register(accumulator, "accumulator")

    val request = HttpUtils().getRequest("https://interface.app315.net/DataService.aspx?Function=GetThelatestTraceNodeData&token=9cd9a8ba-9aad-4902-af3b-0776ec866df9&_=1618906137721")
    // println(request)
    val requestObject = JSON.parseObject(request)

    val resultString = requestObject.getString("Data")
    //print(resultString)
    val resultObject = JSON.parseObject(resultString)
    // println(resultObject)
    val datas = resultObject.getJSONArray("row")
    val dataStr = datas.toString

    //解析json数据
    val elementList = JSON.parseArray(dataStr, classOf[ELementSummary])

    // print(elementList)

    elementList.foreach(element => {

      val iotData: collection.mutable.ListBuffer[IotDeviceData] = new collection.mutable.ListBuffer[IotDeviceData]()


      val operatetime :Timestamp = Timestamp.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(element.getOperateTime).toLocaleString())

      val data = IotDeviceData(element.getNodeID, operatetime, element.getNodeName,element.getBlockNo, element.getNodeHash,element.getModifyTime, element.getBlockHash, element.getSourceHash, element.getTransactionTime, element.getCreateTime)

      iotData.add(data)
      accumulator.add(iotData)
    })

    //将数据写进hive
    val data_url = accumulator.value

    val outputRDD = spark.sparkContext.makeRDD(data_url)
    val outputDF = outputRDD.toDF()
    outputDF.write.format("hive").mode(SaveMode.Overwrite).insertInto("testdb.testetl")


  }

  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "hadoop")
    job() {
      val spark = SparkUtils.get()
      etl(spark)
    }
  }
}

case class IotDeviceData(
                          nodeID: String,
                          operateTime: java.sql.Timestamp,
                          nodeName: String,
                          blockNo: String,
                          nodeHash: String,
                          modifyTime: String,
                          blockHash: String,
                          sourceHash: String,
                          transactionTime: String,
                          createTime: String


                        )
