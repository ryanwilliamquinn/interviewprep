import java.util

import scala.collection.mutable

/**
  * Created by rquinn on 9/7/16.
  */
object bfs {

  case class Node(value: Int, neighbors: Array[Node], visited: Boolean = false)

  type Graph = util.LinkedList[Node]

  def main(args: Array[String]): Unit = {
    val n3 = Node(1, null)
    val n2 = Node(2, null)
    val n1 = Node(3, Array(n2, n3))
    val nodes = new util.LinkedList[Node]()
    nodes.add(n1)
    nodes.add(n2)
    nodes.add(n3)
    //println(doesPathExist(nodes, n1, n3))
    println(doesPathExist(nodes, n2, n3))
  }

  def doesPathExist(nodes: Graph, start: Node, target: Node): Boolean = {
    var visited = false
    if (nodes.isEmpty) false
    else {
      val neighbors = new mutable.Queue[Node]
      neighbors.enqueue(start)
      neighbors.takeWhile((_) => (!neighbors.isEmpty && visited == false)).foreach((node: Node) => {
        println("node", node)
        if (!node.neighbors.isEmpty) {
          node.neighbors.takeWhile((_) => (!node.neighbors.isEmpty && visited == false))
            .foreach((neighbor: Node) => {
              println("neighbor?", neighbor)
              if (neighbor == target) visited = true
              else {
                if (!neighbor.visited) {
                  neighbors.enqueue(neighbor)
                }
              }
            })
        }
      })
    }
    visited
  }



}
