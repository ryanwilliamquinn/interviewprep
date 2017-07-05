import java.util

import scala.collection.mutable

/**
  * shelter holds only dogs and cats, and operates on a strictly "first in, first out basis"
  * People must adopt either the "oldest" (based on time of arrival) of all animals at the shelter,
  * or they can select whether they would prefer a dog or a cat (and will receive the oldest of that type).
  * They cannot select which specific animal they would like. Create the data structures to maintain this system
  * and implement operations such as enqueue, dequeueAny, dequeueDog, dequeueCat. You may use a linked list.
  */
object dogsAndCatsQueue {


  class Animal
  case class Dog(val name: String) extends Animal
  case class Cat(val name: String) extends Animal

  val dogs: mutable.Queue[Dog] = new mutable.Queue[Dog]
  val cats = new mutable.Queue[Cat]
  val animals = new util.LinkedList[Animal]

  val oldestCritter = null

  def main(args: Array[String]): Unit = {
    enqueue(new Dog("Woody"))
    enqueue(new Dog("Winnie"))
    enqueue(new Cat("Milo"))
    println(dequeueAny())
    println(dequeueCat())
    enqueue(new Dog("Turbo"))
    println(dequeueDog())
    println(dequeueDog())
    println(dequeueCat())
  }

  def enqueue(critter: Animal): Unit = {
    // add the critter to the appropriate queue
    critter match {
      case doggy @ Dog(_) => {
        dogs += doggy
      }
      case kitty @ Cat(_)  => {
        cats += kitty
      }
      case _ => throw new Exception(s"unhandled species: $critter")
    }
    // keep a total listing of the order of the animals
    animals.add(critter)
  }

  def dequeueAny(): Animal = {
    val critter = animals.remove()
    critter match {
      case doggy @ Dog(_) => dogs.dequeue()
      case kitty @ Cat(_) => cats.dequeue()
      case _ => throw new Exception(s"unhandled species: $critter")
    }
    critter
  }

  def dequeueDog(): Dog = {
    if (dogs.isEmpty) null
    else {
      val critter = dogs.dequeue()
      animals.remove(critter)
      critter
    }
  }

  def dequeueCat(): Cat = {
    if (cats.isEmpty) null
    else {
      val critter = cats.dequeue()
      animals.remove(critter)
      critter
    }
  }

}
