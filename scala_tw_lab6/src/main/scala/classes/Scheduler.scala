package classes

class Scheduler(matrix: Matrix, fnf: List[List[Task]]){
  def run(): Unit ={
    // dla każdej klasy Foaty uruchamiamy równolegle operacje z jednej klasy
    fnf.foreach(taskList => {
      var taskThreadList = List[Thread]()
      taskList.foreach(task => taskThreadList +:= myThread(matrix, task))
      taskThreadList.foreach(taskThread => taskThread.run())
      taskThreadList.foreach(taskThread => taskThread.join())
    })
  }
}

case class myThread(matrix: Matrix, task: Task) extends Thread{
  override def run(): Unit ={
    matrix.calculateTask(task)
  }
}