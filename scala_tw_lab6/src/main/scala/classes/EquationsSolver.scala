package classes

class EquationsSolver {
  def solveTheSysytemOfEquations(matrix: Matrix): Unit ={
    val N = matrix.N

    matrix.Y = matrix.Y.updated(N-1, matrix.Y(N-1) / matrix.M(N-1)(N-1))

    // doprowadzenie wektora wynikowego do rozwiązania
    for (i <- N-2 until -1 by -1){
      for(j <- i+1 until N){
        matrix.Y = matrix.Y.updated(i, matrix.Y(i) - matrix.M(i)(j) * matrix.Y(j))
      }
      matrix.Y = matrix.Y.updated(i, matrix.Y(i) / matrix.M(i)(i))
    }

    // doprowadzenie macierzy M do macierzy jednostkowej
    for(i <- 0 until N){
      for(j <- 0 until N){
        var tmpList = matrix.M(i)
        tmpList = tmpList.updated(j, if(i == j) 1 else 0)
        matrix.M = matrix.M.updated(i, tmpList)
      }
    }
  }
}
