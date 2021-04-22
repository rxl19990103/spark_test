package test_socket

class Task extends Serializable {
    val datas = List(1,2,3,4,5)
    val logic :(Int) => Int =_ * 2

  //计算
    def comoute() ={
      datas.map(logic)
    }

}
