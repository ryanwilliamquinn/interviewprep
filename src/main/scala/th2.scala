import scala.collection.mutable
/**
  * Created by rquinn on 9/5/16.
  */
object th2 {

def main(args: Array[String]): Unit = {
  hanoi(4)
}

def hanoi(height: Int): Unit = {
    var x = 0
    val s1 = mutable.Stack.fill[Int](height) {
      x += 1; x
    }
    val s2 = mutable.Stack[Int]()
    val s3 = mutable.Stack[Int]()

    def recur(a1: mutable.Stack[Int], a2: mutable.Stack[Int], a3: mutable.Stack[Int], h: Int): Unit = {
      if (h == 1) {
        a2.push(a1.pop)
        println("h == 1")
        println(s1, s2, s3)
      }
      else {
        recur(a1, a3, a2, h - 1)
        a2.push(a1.pop)
        println("h > 1")
        println(s1, s2, s3)
        recur(a3, a2, a1, h - 1)
      }
    }


    println(s1, s2, s3)

    if (s1.nonEmpty) {
      recur(s1, s2, s3, s1.length)
    }
  }
}
