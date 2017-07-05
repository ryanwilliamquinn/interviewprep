import java.util

/**
  * Created by rquinn on 9/5/16.
  * implement a queue as two stacks
  * a queue has first in first out
  * has add, pop, peek
  */
object MyQueue {
  // a single stack will be first in last out
  // put one element in a stack, it is essentially a queue
  val oldest = new util.Stack[Int]
  val newest = new util.Stack[Int]()

  def add(elem: Int): Unit = {
    oldest.add(elem)
  }

  def shiftElements(): Unit = {
    if (newest.empty) {
      while (!oldest.empty) {
        newest.add(oldest.pop())
      }
    }
  }

  def peek(): Unit = {
    shiftElements()
    val peeked = newest.peek
    println(peeked)
    peeked

  }

  def pop(): Unit = {
    shiftElements()
    val popped = newest.pop
    println(popped)
    popped
  }

  def main(args: Array[String]): Unit = {
    add(1)
    add(2)
    add(3)
    peek()
    peek()
    pop()
    peek()
    pop()
    peek()
    pop()
  }

}
