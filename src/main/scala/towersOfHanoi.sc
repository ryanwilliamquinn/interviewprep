import java.util

val numDisks = 3
val tower1 = new util.Stack[Int]()
val tower2 = new util.Stack[Int]()
val tower3 = new util.Stack[Int]()

tower1.push(3)
tower1.push(2)
tower1.push(1)

tower1
tower1.peek

type Towers = Array[util.Stack[Int]]

val towers = Array(tower1, tower2, tower3)

move(towers, 0, 'R')

def move(towers: Towers, currentLocation: Int, lastMoveDirection: Char): Towers = {
  // check if in final state
  if (completed(towers)) {
    println("towers completed", towers)
    towers
  }
  else {
    // see if the current element can move lastMoveDirection again,
    // if not, see if a previous element can move lastModeDirection,
    // otherwise move the next available element in the opposite direction
    lastMoveDirection match {

      case 'L' => {
        val canKeepMoving = validateMove(towers, currentLocation, lastMoveDirection)
        if (canKeepMoving) {
          update(towers, currentLocation, lastMoveDirection)
        } else {
          if (currentLocation < (towers.length - 2)) {
            move(towers, currentLocation + 1, lastMoveDirection)
          } else {
            move(towers, 0, 'R')
          }
        }
      }
      case 'R' => {
        val canKeepMoving = validateMove(towers, currentLocation, lastMoveDirection)
        if (canKeepMoving) {
          update(towers, currentLocation, lastMoveDirection)
        } else {
          if (currentLocation > 0) {
            move(towers, currentLocation - 1, lastMoveDirection)
          } else {
            move(towers, towers.length - 1, 'L')
          }
        }
      }
    }
  }
}

def validateMove(towers: Towers, towerIndex: Int, direction: Char): Boolean = {
  println("validate move", towerIndex)

  if (towers(towerIndex).empty()) false
  else {
    val movingValue = towers(towerIndex).peek()
    direction match {
      case 'L' => {
        if (towerIndex < 1) false
        else {
          if (towers(towerIndex - 1).empty) false
          else {
            movingValue < towers(towerIndex - 1).peek()
          }
        }
      }
      case 'R' => {
        if (towerIndex > (towers.length - 2)) false
        else {
          if (towers(towerIndex +1).empty) false
          else {
            movingValue < towers(towerIndex + 1).peek()
          }
        }
      }
    }
  }
}

def update(towers: Towers, startingTower: Int, direction: Char): Towers = {
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

