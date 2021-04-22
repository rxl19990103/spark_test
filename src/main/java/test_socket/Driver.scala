package test_socket

import java.io.ObjectOutputStream
import java.net.Socket

object Driver {
  def main(args: Array[String]): Unit = {
    //连接服务器
    val client = new Socket("localhost", 9999)
    val out = client.getOutputStream
    val outObj = new ObjectOutputStream(out)
    val task = new Task()
    outObj.writeObject(task)
    outObj.flush()
    outObj.close()
    client.close()
    print("客户端数据发送完毕！！！")
  }

}
