import Day9._
import org.scalatest.matchers.must.Matchers._
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable

class Day9Test extends AnyWordSpec {

    "The Intcode computer" should {

        "support relative mode and expanding memory" in {

            val testProgram = List(109L, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
            runWirableProgram(testProgram) mustEqual testProgram
        }

        "support large numbers" in {

            val testProgram1 = List(1102L, 34915192, 34915192, 7, 4, 7, 99, 0)
            runWirableProgram(testProgram1).dequeue.toString.length mustEqual 16

            val testProgram2 = List(104, 1125899906842624L, 99)
            runWirableProgram(testProgram2).dequeue mustEqual 1125899906842624L
        }

        "support relative mode for input" in {

            val testProgram = List(109, 22, 203L, -15, 4, 7, 99)
            runWirableProgram(testProgram, mutable.Queue(1)).dequeue mustEqual 1
        }
    }
}
