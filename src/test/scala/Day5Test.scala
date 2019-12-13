import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers._
import Day5._

class Day5Test extends AnyFlatSpec {

    "The Intcode computer" should "produce the correct output" in {

        runProgram(Array(3,0,4,0,99), Iterator(4)) mustBe List(4)
        runProgram(Array(3,0,4,0,99), Iterator(-99)) mustBe List(-99)
    }

    "The Intcode computer" should "support parameter modes" in {

        runProgram(Array(1002,4,3,4,33), Iterator(4)) mustBe List()
        runProgram(Array(1101,100,-1,4,0), Iterator(4)) mustBe List()
    }
}
