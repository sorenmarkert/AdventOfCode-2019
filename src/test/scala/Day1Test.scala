import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers._
import Day1._

class Day1Test extends AnyFlatSpec {

    "The fuel calculator" should "calculate the correct fuel" in {

        calculateFuel(12 :: Nil) mustEqual 2
        calculateFuel(14 :: Nil) mustEqual 2
        calculateFuel(1969 :: Nil) mustEqual 654
        calculateFuel(100756 :: Nil) mustEqual 33583
    }

    "The total fuel calculator" should "calculate the correct fuel" in {

        calculateTotalFuel(12 :: Nil) mustEqual 2
        calculateTotalFuel(14 :: Nil) mustEqual 2
        calculateTotalFuel(1969 :: Nil) mustEqual 966
        calculateTotalFuel(100756 :: Nil) mustEqual 50346
    }
}
