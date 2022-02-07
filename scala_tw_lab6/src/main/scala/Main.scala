import classes.{Alphabet, Dependency, DiekertGraph, EquationsSolver, FileSavingSystem, FoataNormalForm, Matrix, Scheduler}

import java.io.{File, FileWriter}
import scala.io.Source
import scala.language.postfixOps

object Main {
  def main(args: Array[String]) :Unit = {
    // Najpierw podajemy ścieżkę do pliku, z którego odczytamy dane wejściowe
    val inputFilePath = "input4.txt"

    // otwieramy plik i wyciągamy z niego zawartość linijka po linijce
    val source = Source.fromResource(inputFilePath)
    val lines: List[String] = source.getLines().toList // lista linijek z pliku

    // stworzenie nowej macierzy z odczytanego pliku
    var matrix: Matrix = new Matrix(lines.head.split(" ").toList.map(str => str.toInt).head)

    // wypełnienie macierzy danymi
    matrix.createMatrix(between(lines), lines.last)

    // wypisanie gotowej macierzy wejściowej
    println("Input matrix:")
    matrix.printMatrix()
    print("\n")

    /*
    wyznaczenie alfabetu korzystając z algorytmu sekwencyjnego na otrzymanie ciągu
    niezależnych zadań obliczeniowych
     */
    val alphabet: Alphabet = new Alphabet // pusta lista w sigma
    alphabet.tasksSequence(matrix) // wypełnianie listy stanowiącej alfabet

    // wypisanie alfabetu:
    print(alphabet.toString)

    // relacja zależności - bez relacji wynikających z przechodniości
    val depGroup: Dependency = new Dependency
    depGroup.generateDependencyList(alphabet)
    // wypisanie relacji zależności
    print(depGroup.toString)


    //postać normalna foaty
    val fnf: FoataNormalForm = new FoataNormalForm
    fnf.generateFNF(alphabet, matrix.N)
    print(fnf.toString)

    // nadanie kolorów wierzchołkom na podstawie fnf
    fnf.fnfList.foreach(list => {
      val color = "#%06x".format(scala.util.Random.nextInt(0xffffff + 1))
      list.foreach(task => task.setVertexColor(color))
    })

    var diekertGraph: DiekertGraph = new DiekertGraph
    diekertGraph.initGraph(alphabet)

    println("\nOutput matrix before solving equations: ")
    val scheduler: Scheduler = new Scheduler(matrix, fnf.fnfList)
    scheduler.run()
    matrix.printMatrix()
    print("\n")

    /* mając macierz w postaci macierzy trójkątnej dolnej możemy rozwiązać układ równań
    *  doprowadzając macierz do postaci macierzy jednostkowej oraz poprawnego wektora wynikowego*/
    val equationsSolver: EquationsSolver = new EquationsSolver
    equationsSolver.solveTheSysytemOfEquations(matrix)
    println("Output (after solving equations):")
    matrix.printMatrix()


    // zapisywanie do plików
    val fileSaving: FileSavingSystem = new FileSavingSystem(inputFilePath.substring(0, inputFilePath.lastIndexOf(".")))
    fileSaving.initialization(diekertGraph, alphabet.toString, depGroup.toString, fnf.toString, matrix.getMatrixAsString)

    source.close()
  }

  //podaj podlistę podanej listy składającą się z elementów podanej listy bez pierwszego i ostatniego elementu
  def between(list:List[String]): List[String] = list.takeRight(list.length - 1).take(list.length - 2)

}