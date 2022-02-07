package classes

class Matrix(val size: Integer){
  var M: List[List[Float]] = List()
  var Y: List[Float] = List()
  val N: Integer = size
  var m: Array[Array[Float]] = Array.ofDim[Float](N+1, N+1)
  var n: Array[Array[Array[Float]]] = Array.ofDim[Float](N+1, N+2, N+1)

  def createMatrix(rows: List[String], outputVertex:String): Unit ={
    rows.foreach(row => {
      M :+= row.split(" ").toList.map(str => str.toFloat)
    })
    Y = outputVertex.split(" ").toList.map(str => str.toFloat)
  }

  def printMatrix(): Unit ={
    M.foreach(row => {
      println("[" + row.mkString(", ") + " | " + Y(M.indexOf(row)) + "]")
    })
  }

  def getMatrixAsString: String = {
    var lines = ""
    lines += (this.N.toString + " \n")
    M.foreach(row => {
      lines += row.mkString(" ") + "\n"
    })
    lines += Y.mkString(" ")
    lines
  }

  def A(i: Int, k: Int): Unit ={
    if(M(i)(i) == 0) m(k)(i) = 0
    else m(k)(i) = M(k)(i) / M(i)(i)
  }
  def B(i: Int, j: Int, k: Int): Unit ={
    if(j >= N) n(k)(j)(i) = Y(i) * m(k)(i)
    else n(k)(j)(i) = M(i)(j) * m(k)(i)
  }
  def C(i: Int, j: Int, k: Int): Unit ={
    if(j >= N){
      Y = Y.updated(k, Y(k) - n(k)(j)(i))
    }
    else{
      var tmpList = M(k)
      tmpList = tmpList.updated(j, M(k)(j) - n(k)(j)(i))
      M = M.updated(k, tmpList)
    }

  }

  def calculateTask(task:Task): Unit ={
    if(task.name == 'A') A(task.i, task.k)
    else if(task.name == 'B') B(task.i, task.j, task.k)
    else if(task.name == 'C') C(task.i, task.j, task.k)
  }
}
