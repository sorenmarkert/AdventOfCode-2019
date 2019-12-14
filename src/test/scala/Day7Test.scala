import Day7._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers._

class Day7Test extends AnyFlatSpec {

    "The amplifier software" should "calculate the correct output" in {

        val amplifierControllerSoftware1 = List(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
        amplifySignal(List(4,3,2,1,0), amplifierControllerSoftware1) mustEqual 43210

        val amplifierControllerSoftware2 = List(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0)
        amplifySignal(List(0,1,2,3,4), amplifierControllerSoftware2) mustEqual 54321

        val amplifierControllerSoftware3 = List(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0)
        amplifySignal(List(1,0,4,3,2), amplifierControllerSoftware3) mustEqual 65210
    }
}
