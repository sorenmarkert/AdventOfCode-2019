import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers._
import Day2._

class Day2Test extends AnyFlatSpec {

    "The Intcode computer" should "return the correct results" in {

        processOpCodes(Array(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50))(0) mustEqual 3500
        processOpCodes(Array(1, 0, 0, 0, 99))(0) mustEqual 2
        processOpCodes(Array(2, 3, 0, 3, 99))(3) mustEqual 6
        processOpCodes(Array(2, 4, 4, 5, 99, 0))(5) mustEqual 9801
        processOpCodes(Array(1, 1, 1, 4, 99, 5, 6, 0, 99))(0) mustEqual 30
    }
}
