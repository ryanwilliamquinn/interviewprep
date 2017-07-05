/**
  * Implement a function to check if a binary tree is balanced. For the purposes of this question,
  * a balanced tree is defined to be a tree such that the heights of the two subtrees of any node
  * never differ by more than 1
  */
object isTreeBalanced {

  sealed class Tree()
  case class Node(left: Tree, right: Tree) extends Tree
  case class Empty() extends Tree

  def max(n1: Int, n2: Int): Int = if (n1 > n2) n1 else n2

  def checkHeight(tree: Tree): Int = {
    tree match {
      case Empty() => 0
      case Node(l, r) => {
        val leftHeight = checkHeight(l)
        if (leftHeight == -1) -1
        else {
          val rightHeight = checkHeight(r)
          if (rightHeight == -1) -1
          else {
            val heightDiff = math.abs(leftHeight - rightHeight)
            if (heightDiff > 1) -1
            else {
              max(leftHeight, rightHeight) + 1
            }
          }
        }
      }
    }
  }

  def isBalanced(tree: Tree): Boolean =
    tree match {
      case Empty() => true
      case Node(l, r) => {
        if (math.abs(treeDepth(l) - treeDepth(r)) > 1) {
          false
        } else {
          isBalanced(l) && isBalanced(r)
        }
      }
    }

  def treeDepth(tree: Tree): Int = {
    println("calling tree depth")
    tree match {
      case Empty() => 1
      case Node(l, r) => 1 + max(treeDepth(l), treeDepth(r))
    }
  }

  def main(args: Array[String]): Unit = {
    val unbalanced = Node(Node(Node(Empty(), Empty()), Node(Empty(), Empty())), Empty())
    val balanced = Node(Empty(), Empty())
//    println(tree)
//    println(treeDepth(tree))
//    println(treeDepth(tree.left))
//    println(treeDepth(tree.right))
    //println(isBalanced(tree))
    println(checkHeight(balanced))
  }

}
