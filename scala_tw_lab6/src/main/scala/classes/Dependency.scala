package classes

class Dependency {
  var D: List[(Task, Task)] = List()

  def generateDependencyList(alphabet: Alphabet): Unit ={
    D = alphabet.list.flatMap(t1 =>
      alphabet.list.map(t2 => (t1, t2)))
      .filter(pair => pair._1.isRelative(pair._2))
  }

  override def toString: String = {
    "D = \\{ " + D.map(p =>
      "(" + p._1.toString + ", " + p._2.toString + "); ").mkString + "+\\}\n"
  }
}
