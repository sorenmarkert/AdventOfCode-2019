import Day11._

import org.scalatest.matchers.must.Matchers._
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable.{Map => MMap}

class Day11Test extends AnyWordSpec {

    "The painter robot" should {

        "paint panels correctly" in {
            val testProgram = List(
                3, 52,
                4, 52,
                104, 1,
                3, 52,
                4, 52,
                104, 1,
                3, 52,
                4, 52,
                104, 1,
                3, 52,
                4, 52,
                104, 1,
                99L
            )
            paintHull(testProgram) mustEqual MMap((0, 0) -> 1, (1, 0) -> 0, (1, -1) -> 0, (0, -1) -> 0)
        }

        "display panels correctly" in {

            createImage(MMap(
                (-1, -1) -> 1,
                (-1, 1) -> 1,
                (-1, 2) -> 1,
                (1, 0) -> 0,
                (1, 1) -> 0,
                (1, 2) -> 1
            )) mustEqual
                """#  |
                  |   |
                  |#  |
                  |# #|
                  |""".stripMargin

            createImage(MMap(
                (-5, -5) -> 1,
                (-5, -4) -> 1,
                (-5, -6) -> 1,
                (-4, -5) -> 1,
                (-6, -5) -> 1,
                (1, 2) -> 0
            )) mustEqual
                """ # |
                  |###|
                  | # |
                  |""".stripMargin
        }
    }
}
