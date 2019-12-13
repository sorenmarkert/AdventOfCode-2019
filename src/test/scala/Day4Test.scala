import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers._
import Day4._

class Day4Test extends AnyFlatSpec {

    "The password calculator" should "find the correct amount of valid passwords with any pairs" in {

        def testFun = isValid(true)_
        testFun(111111) mustBe true
        testFun(223450) mustBe false
        testFun(123789) mustBe false
    }

    "The password calculator" should "find the correct amount of valid passwords with strict pairs" in {

        def testFun = isValid(false)_
        testFun(112233) mustBe true
        testFun(123444) mustBe false
        testFun(111122) mustBe true
    }
}
