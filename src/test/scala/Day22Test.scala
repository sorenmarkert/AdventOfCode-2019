
import Day22.{dealDeck, doTechnique}
import org.scalatest.matchers.must.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class Day22Test extends AnyWordSpec {

    "The dealer" should {

        "know single techniques" in {

            doTechnique("deal into new stack", List(0, 1, 2)) mustEqual List(2, 1, 0)

            doTechnique("cut 2", (0 until 4).toList) mustEqual List(2, 3, 0, 1)
            doTechnique("cut -3", (0 until 4).toList) mustEqual List(1, 2, 3, 0)
            doTechnique("cut -13", (0 until 20).toList) mustEqual ((7 until 20) ++ (0 until 7))

            doTechnique("deal with increment 2", (0 until 4).toList) mustEqual List(0, 2, 1, 3)
            doTechnique("deal with increment 3", (0 until 4).toList) mustEqual List(0, 3, 2, 1)
            doTechnique("deal with increment 5", (0 until 4).toList) mustEqual List(0, 1, 2, 3)
        }

        "deal correctly" in {

            dealDeck(
                """deal with increment 7
                  |deal into new stack
                  |deal into new stack""".stripMargin.linesIterator.toList, (0 until 10).toList) mustEqual List(0, 3, 6, 9, 2, 5, 8, 1, 4, 7)

            dealDeck(
                """cut 6
                  |deal with increment 7
                  |deal into new stack""".stripMargin.linesIterator.toList, (0 until 10).toList) mustEqual List(3, 0, 7, 4, 1, 8, 5, 2, 9, 6)

            dealDeck(
                """deal with increment 7
                  |deal with increment 9
                  |cut -2""".stripMargin.linesIterator.toList, (0 until 10).toList) mustEqual List(6, 3, 0, 7, 4, 1, 8, 5, 2, 9)

            dealDeck(
                """deal into new stack
                  |cut -2
                  |deal with increment 7
                  |cut 8
                  |cut -4
                  |deal with increment 7
                  |cut 3
                  |deal with increment 9
                  |deal with increment 3
                  |cut -1""".stripMargin.linesIterator.toList, (0 until 10).toList) mustEqual List(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)
        }
    }
}
