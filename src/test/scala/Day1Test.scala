import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec

class Day1Test extends AnyFlatSpec with GivenWhenThen {

    "A thing" should "return the correct result" in {

        Given("something")
        val v = 1
        assert(v == 1)
    }
}
