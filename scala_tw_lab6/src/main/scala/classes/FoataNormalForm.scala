package classes

class FoataNormalForm {
  var fnfList: List[List[Task]] = List()

  def generateFNF(alphabet: Alphabet, matrixSize: Integer): Unit ={
    for(i <- 1 until matrixSize){
      fnfList :+= alphabet.list.filter(task => task.name == 'A' && task.i == i-1)

      val tmpList: List[Task] = alphabet.list.filter(task => task.i == i-1)
      fnfList :+= tmpList.filter(task => task.name == 'B')
      fnfList :+= tmpList.filter(task => task.name == 'C')
    }
  }

  override def toString: String = {
    "FNF = \\{ " + fnfList.map(list =>
      "[" + list.map(task =>
        task.toString + "; ").mkString + "]").mkString +" \\}\n"

  }
}
