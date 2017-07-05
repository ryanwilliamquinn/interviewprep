import java.util.Stack

/**
  * Sort a stack in ascending order (with biggest items on top).
  * You may use at most one additional stack to hold items, but you may not copy the elements into any other data
  * structure (such as an array). The stack supports the following operations: push, pop, peek, isEmpty
  */
object sortedStack {

  def main(args: Array[String]): Unit = {
    val s: Stack[Int] = new java.util.Stack
    s.push(5)
    s.push(2)
    s.push(9)
    s.push(7)
    s.push(1)
    s.push(3)
    s.push(4)
    val sorted = sortStack(s)
    println(sorted)
    println(sorted.peek())
  }

  def sortStack(input: Stack[Int]): Stack[Int] = {
    val output = new Stack[Int]

    while (!input.isEmpty) {
      val tmp = input.pop
      while (!output.isEmpty && (output.peek > tmp)) {
        input.push(output.pop)
      }
      output.push(tmp)
    }
    output
  }

}
