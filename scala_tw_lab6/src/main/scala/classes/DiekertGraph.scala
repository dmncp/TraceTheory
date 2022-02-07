package classes

import scalax.collection.GraphEdge.DiEdge
import scalax.collection.GraphPredef._
import scalax.collection.io.dot.{DotAttr, DotEdgeStmt, DotGraph, DotNodeStmt, DotRootGraph, Id, NodeId, graph2DotExport}
import scalax.collection.{Graph, mutable}

class DiekertGraph {
  val graph: mutable.Graph[Task, DiEdge] = mutable.Graph[Task, DiEdge]()

  val root: DotRootGraph = DotRootGraph(
    directed = true,
    id = Some(Id("Res"))
  )

  var dotGraph: String = ""

  def initGraph(alphabet: Alphabet): Unit ={
    addVertexes(alphabet)
    addEdgesToGraph(alphabet)
    dotGraph =  graph.toDot(root, edgeTransformer = edgeTransformer, cNodeTransformer = Some(nodeTransformer))
  }

  def addVertexes(alphabet: Alphabet): Unit ={
    alphabet.list.foreach(i => graph += i)
  }

  def addEdgesToGraph(alphabet: Alphabet): Unit ={
    alphabet.list.reverse.flatMap(t1 =>
      alphabet.list.map(t2 => (t1,t2)))
      .filter(pair => {
        alphabet.list.indexOf(pair._1) < alphabet.list.indexOf(pair._2) && pair._1.isRelative(pair._2)
      })
      .foreach(pair => {
        if(graph.get(pair._1).pathTo(graph.get(pair._2)).isEmpty){
          graph += pair._1 ~> pair._2
        }
      })
  }

  def edgeTransformer(innerEdge: Graph[Task, DiEdge]#EdgeT): Option[(DotGraph, DotEdgeStmt)] = {
    val edge  = innerEdge.edge
    Some(
      root,
      DotEdgeStmt(
        NodeId(edge.from.toString),
        NodeId(edge.to.toString),
      ))
  }
  def nodeTransformer(innerNode: Graph[Task, DiEdge]#NodeT): Option[(DotGraph, DotNodeStmt)] =
    Some(
      root,
      DotNodeStmt(
        NodeId(innerNode.toString()),
        List(DotAttr(Id("label"), Id(innerNode.value.toString)), DotAttr(Id("fillcolor"), Id(innerNode.value.color)), DotAttr(Id("style"), Id("filled")))))

}
