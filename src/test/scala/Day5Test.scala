import Day5._
import org.scalatest.matchers.must.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class Day5Test extends AnyWordSpec {

    "The Intcode computer" should {

        "produce the correct output" in {

            runProgram(Array(3, 0, 4, 0, 99), Iterator(4)) mustBe List(4)
            runProgram(Array(3, 0, 4, 0, 99), Iterator(-99)) mustBe List(-99)
        }

        "support parameter modes" in {

            runProgram(Array(1002, 4, 3, 4, 33), Iterator(4)) mustBe List()
            runProgram(Array(1101, 100, -1, 4, 0), Iterator(4)) mustBe List()
            runProgram(Array(101, 100, 4, 4, -1), Iterator(4)) mustBe List()
        }

        "support conditional opcodes in position mode" in {

            // equal to 8
            runProgram(Array(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(8)) mustBe List(1)
            runProgram(Array(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(7)) mustBe List(0)
            runProgram(Array(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(9)) mustBe List(0)
            runProgram(Array(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(-9)) mustBe List(0)

            // less than 8
            runProgram(Array(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(118)) mustBe List(0)
            runProgram(Array(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(8)) mustBe List(0)
            runProgram(Array(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(7)) mustBe List(1)
            runProgram(Array(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), Iterator(-7)) mustBe List(1)

            // non-zero
            runProgram(Array(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), Iterator(0)) mustBe List(0)
            runProgram(Array(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), Iterator(17)) mustBe List(1)
            runProgram(Array(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), Iterator(-17)) mustBe List(1)
        }

        "support conditional opcodes in immediate mode" in {

            // equal to 8
            runProgram(Array(3, 3, 1108, -1, 8, 3, 4, 3, 99), Iterator(8)) mustBe List(1)
            runProgram(Array(3, 3, 1108, -1, 8, 3, 4, 3, 99), Iterator(7)) mustBe List(0)
            runProgram(Array(3, 3, 1108, -1, 8, 3, 4, 3, 99), Iterator(9)) mustBe List(0)
            runProgram(Array(3, 3, 1108, -1, 8, 3, 4, 3, 99), Iterator(-9)) mustBe List(0)

            // less than 8
            runProgram(Array(3, 3, 1107, -1, 8, 3, 4, 3, 99), Iterator(118)) mustBe List(0)
            runProgram(Array(3, 3, 1107, -1, 8, 3, 4, 3, 99), Iterator(8)) mustBe List(0)
            runProgram(Array(3, 3, 1107, -1, 8, 3, 4, 3, 99), Iterator(7)) mustBe List(1)
            runProgram(Array(3, 3, 1107, -1, 8, 3, 4, 3, 99), Iterator(-7)) mustBe List(1)

            // non-zero
            runProgram(Array(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), Iterator(0)) mustBe List(0)
            runProgram(Array(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), Iterator(17)) mustBe List(1)
            runProgram(Array(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), Iterator(-17)) mustBe List(1)
        }

        "support a larger program with conditional opcodes" in {

            val largerProgram = Array(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0,
                1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)

            runProgram(largerProgram, Iterator(-7)) mustBe List(999)
            runProgram(largerProgram, Iterator(7)) mustBe List(999)
            runProgram(largerProgram, Iterator(8)) mustBe List(1000)
            runProgram(largerProgram, Iterator(9)) mustBe List(1001)
            runProgram(largerProgram, Iterator(119)) mustBe List(1001)
        }
    }
}
