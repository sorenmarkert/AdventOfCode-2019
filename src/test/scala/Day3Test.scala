import Day3._
import org.scalatest.matchers.must.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class Day3Test extends AnyWordSpec {

    "The wire intersection calculator" should {

        "find the correct shortest Manhattan distance of all intersections" in {

            shortestManhattanDistance("R8,U5,L5,D3", "U7,R6,D4,L4") mustEqual 6
            shortestManhattanDistance("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83") mustEqual 159
            shortestManhattanDistance("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") mustEqual 135
        }

        "find the correct shortest traversed distance of all intersections" in {

            shortestTraversedDistance("R8,U5,L5,D3", "U7,R6,D4,L4") mustEqual 30
            shortestTraversedDistance("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83") mustEqual 610
            shortestTraversedDistance("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") mustEqual 410
        }
    }
}
