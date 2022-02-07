package classes

class Task(val taskType:Char, val id_i:Integer, val id_j:Integer, val id_k:Integer){
  val name: Char = taskType
  val i:Integer = id_i
  val j:Integer = id_j
  val k:Integer = id_k
  var value: Float = -1
  var color: String = "#000000"

  def setVertexColor(color: String): Unit ={
    this.color = color
  }

  override def toString: String = {
    if(name == 'A') name + "_{" + (i+1) + "," + (k+1) + "}"
    else "" + name + "_{" + (i+1) +","+ (j+1) + "," + (k+1) + "}"
  }

  // sprawdza bezpośrednie relacje - nie uwzględnia relacji przechodnich
  def isRelative(secondTask: Task): Boolean = {
    var t1: Task = this
    var t2: Task = secondTask

    if(name > secondTask.name){
      t1 = secondTask
      t2 = this
    }

    if((t1.name == 'A' && t2.name == 'A') || (t1.name == 'A' && t2.name == 'B')){
      return t1.i == t2.i && t1.k == t2.k
    }
    else if(t1.name == 'A' && t2.name == 'C'){
      return t1.i == t2.j && (t1.k == t2.k || t1.i == t2.k)
    }
    else if(t1.name == 'B' && t2.name == 'B'){
      return t1.i == t2.i && t1.j == t2.j && t1.k == t2.k
    }
    else if(t1.name == 'B' && t2.name == 'C' ){
      return (t1.i == t2.i && t1.j == t2.j && t1.k == t2.k) || (t1.i == t2.k && t1.j == t2.j)
    }
    else if(t1.name == 'C' && t2.name == 'C'){
      return t1.j == t2.j && t1.k == t2.k
    }
    false
  }
}