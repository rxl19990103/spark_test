package etl2.util

import java.io.IOException
import com.alibaba.fastjson.JSON
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost}
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class HttpUtils {
  def getRequest(url: String): String = {

    val httpClient = HttpClientBuilder.create().build()
    val httpGet = new HttpGet(url)
    var response: CloseableHttpResponse = null
    var request = ""
    try {
      response = httpClient.execute(httpGet)
      val entity = response.getEntity
      //      EntityUtils.
      request = EntityUtils.toString(entity)
    } catch {
      case ex: Exception => {
        ex.printStackTrace()
      }
    } finally {
      try {
        if (httpClient != null) httpClient.close()
        if (response != null) response.close()
      } catch {
        case e: IOException =>
          e.printStackTrace()
      }
    }
    request
  }

  def postRequest(url: String): String = {
    val httpClient = HttpClientBuilder.create().build()
    val httpPost = new HttpPost(url)
    httpPost.setHeader("Content-Type", "application/json;charset=utf8")
    var response: CloseableHttpResponse = null
    var request = ""
    try {
      response = httpClient.execute(httpPost)
      val entity = response.getEntity
      request = EntityUtils.toString(entity)
    } catch {
      case ex: Exception => {
        ex.printStackTrace()
      }
    } finally {
      try {
        if (httpClient != null) httpClient.close()
        if (response != null) response.close()
      } catch {
        case e: IOException =>
          e.printStackTrace()
      }
    }
    request
  }

}

object HttpUtils {
  def apply(): HttpUtils = new HttpUtils()

  def main(args: Array[String]): Unit = {
    val str = HttpUtils().getRequest("https://interface.app315.net/DataService.aspx?Function=GetThelatestTraceNodeData&token=9cd9a8ba-9aad-4902-af3b-0776ec866df9&_=1618906137721")
    println(JSON.parseObject(str).getJSONArray("results"))
  }
}


