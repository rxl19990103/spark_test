package test_socket

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}

object Executor {
  def main(args: Array[String]): Unit = {
    //启动服务器，接收数据
    val server = new ServerSocket(9999)
    print("服务器启动，接收数据!!!")
    //等待客户端的连接
    val client = server.accept()
    val in = client.getInputStream
    val objIn = new ObjectInputStream(in)
    val task = objIn.readObject().asInstanceOf[Task]
    val ints = task.comoute()
    println("计算节点计算结果:  " + ints)
    objIn.close()
    client.close()
    server.close()
  }
}
