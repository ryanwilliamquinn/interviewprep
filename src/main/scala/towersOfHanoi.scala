import java.util

/**
  * Created by rquinn on 9/4/16.
  */
object towersOfHanoi {

  import java.util

  var moveCounter = 0
  val numDisks = 3
  val maxOperations = 100
  var operations = 0


  type Towers = Array[util.Stack[Int]]


  def main(args: Array[String]): Unit = {
    val tower1 = new util.Stack[Int]()
    val tower2 = new util.Stack[Int]()
    val tower3 = new util.Stack[Int]()

    tower1.push(3)
    tower1.push(2)
    tower1.push(1)

    val towers = Array(tower1, tower2, tower3)
    println("starting")
    move(towers, 0, 'R', 'R', 0)
  }

  def showTowers(towers: Towers): Unit = {
    val tz: Array[(util.Stack[Int], Int)] = towers.zipWithIndex
    tz.foreach { case (tower: util.Stack[Int], index: Int) => {
      if (tower.empty) {
        println(s"Tower $index is Empty")
      } else {
        println(s"Tower $index contains ${tower.toArray.mkString(" ")}")
      }
    }}
  }

  def move(towers: Towers, currentLocation: Int, direction: Char, lastMoveDirection: Char, lastMoveDestination: Int): Towers = {
    //println(s"Current location $currentLocation, direction: $direction, last move direction: $lastMoveDirection, last move destination: $lastMoveDestination, towers: ${showTowers(towers)}")
    operations += 1
    // check if in final state
    if (completed(towers) || operations > maxOperations) {
      println(s"towers of hanoi completed for $numDisks disks: ${showTowers(towers)}, in only $moveCounter moves")
      towers
    }
    else {
      // see if the current element can move lastMoveDirection again,
      // if not, see if a previous element can move lastModeDirection,
      // otherwise move the next available element in the opposite direction
      direction match {

        case 'L' => {
          val canKeepMoving = validateMove(towers, currentLocation, 'L', lastMoveDirection, lastMoveDestination)
          if (canKeepMoving) {
            move(update(towers, currentLocation, direction), currentLocation - 1, 'L', 'L', currentLocation - 1)
          } else {
            if (currentLocation < (towers.length - 1)) {
              move(towers, currentLocation + 1, 'L', lastMoveDirection, lastMoveDestination)
            } else {
              if (lastMoveDirection == 'R') {
                move(towers, currentLocation - 1, 'L', lastMoveDirection, lastMoveDestination)
              } else {
                move(towers, 0, 'R', lastMoveDirection, lastMoveDestination)
              }
            }
          }
        }
        case 'R' => {
          val canKeepMoving = validateMove(towers, currentLocation, 'R', lastMoveDirection, lastMoveDestination)
          if (canKeepMoving) {
            val newLocation = currentLocation + 1
            move(update(towers, currentLocation, direction), newLocation, 'R', 'R', newLocation)
          } else {
            if (currentLocation > 0) {
              move(towers, currentLocation - 1, 'R', lastMoveDirection, lastMoveDestination)
            } else {
              if (lastMoveDirection == 'L') {
                move(towers, currentLocation + 1, 'R', lastMoveDirection, lastMoveDestination)
              } else {
                move(towers, towers.length - 1, 'L', lastMoveDirection, lastMoveDestination)
              }
            }
          }
        }
      }
    }
  }

  def validateMove(towers: Towers, towerIndex: Int, direction: Char, lastMoveDirection: Char, lastMoveDestination: Int): Boolean = {
    //println("validate move", towerIndex, direction, lastMoveDirection, lastMoveDestination)

    if (towers(towerIndex).empty()) false
    else {
      val movingValue = towers(towerIndex).peek()
      direction match {
        case 'L' => {
          if (towerIndex < 1) false
          else if (lastMoveDirection == 'R' && lastMoveDestination == towerIndex) false
          else {
            val previousTower = towers(towerIndex - 1)
            previousTower.empty || (movingValue < previousTower.peek)
          }
        }
        case 'R' => {
          if (towerIndex > (towers.length - 2)) false
          else if (lastMoveDirection == 'L' && lastMoveDestination == towerIndex) false
          else {
            val nextTower = towers(towerIndex + 1)
            nextTower.empty || (movingValue < nextTower.peek)
          }
        }
      }
    }
  }

  def update(towers: Towers, startingTower: Int, direction: Char): Towers = {
    moveCounter += 1
    //println(s"update, startingTower: $startingTower, direction: $direction")
    val disk = towers(startingTower).pop()
    direction match {
      case 'L' => {
        towers(startingTower - 1).add(disk)
        towers
      }
      case 'R' => {
        towers(startingTower + 1).add(disk)
        towers
      }
    }
  }

  def completed(towers: Towers): Boolean = {
    // all of the elements are in the last stack
    towers(towers.length - 1).size() == numDisks
  }
}
