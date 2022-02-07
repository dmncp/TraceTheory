package classes

class Alphabet {
  var list: List[Task] = List()

  // implementacja algorytmu sekwencyjnego - ciąg zadań obliczeniowych
  def tasksSequence(matrix: Matrix): Unit ={
    for(i <- 1 until matrix.N){
      for(k <- i+1 until matrix.N+1){
        val A: Task = new Task('A', i-1, -1, k-1)
        list :+= A

        for(j <- i until matrix.N+2){
          val B: Task = new Task('B', i-1, j-1, k-1)
          val C: Task = new Task('C', i-1, j-1, k-1)

          list :+= B
          list :+= C
        }
      }
    }
  }

  override def toString: String = {
    "A = \\{ " + list.map(task => task.toString + "; ").mkString + " \\}" + "\n"
  }
}
