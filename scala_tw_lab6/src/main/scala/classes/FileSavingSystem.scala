package classes

import java.io.{File, FileWriter}

class FileSavingSystem(inputFileName: String) {
  var dirName: String = "for_" + inputFileName
  val pathToDir: String = "src/main/resources/outputs/" + dirName
  var directory = new File(pathToDir)

  def initialization(diekertGraph: DiekertGraph, alphStr: String, dString: String, fnfString: String, matrixStr: String): Unit ={
    createDirectory()
    createDotOutput(diekertGraph)
    createDetailsFile(alphStr, dString, fnfString)
    createOutputFile(matrixStr)
  }

  def createDirectory(): Unit ={
    if(! directory.exists()){
      directory.mkdir()
    }
  }

  def createDotOutput(diekertGraph: DiekertGraph): Unit ={
    val path = pathToDir +"/dot_for_" + inputFileName + ".txt"
    val fw = new FileWriter(path, true)
    try {
      fw.append(diekertGraph.dotGraph)
    }
    finally fw.close()
  }

  def createDetailsFile(alphStr: String, dString: String, fnfString: String): Unit ={
    val path = pathToDir + "/details_for_" + inputFileName
    val fw = new FileWriter(path, true)
    try {
      fw.append(alphStr)
      fw.append(dString)
      fw.append(fnfString)
    }
    finally fw.close()
  }

  def createOutputFile(matrixStr: String): Unit ={
    val path = pathToDir + "/output_for_" + inputFileName
    val fw = new FileWriter(path, true)
    try {
      fw.append(matrixStr)
    }
    finally fw.close()
  }
}
