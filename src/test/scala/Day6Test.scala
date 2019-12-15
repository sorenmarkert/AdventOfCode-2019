import Day6._
import org.scalatest.matchers.must.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class Day6Test extends AnyWordSpec {

    "The map checksum calculator" should {

        "calculate the correct sum of orbits" in {

            val testMap =
                """COM)B
                  |B)C
                  |C)D
                  |D)E
                  |E)F
                  |B)G
                  |G)H
                  |D)I
                  |E)J
                  |J)K
                  |K)L""".stripMargin.linesIterator.toList

            orbitCount(testMap) mustEqual 42
        }

        "calculate the correct number of orbital transfers" in {

            val testMap =
                """COM)B
                  |B)C
                  |C)D
                  |D)E
                  |E)F
                  |B)G
                  |G)H
                  |D)I
                  |E)J
                  |J)K
                  |K)L
                  |K)YOU
                  |I)SAN""".stripMargin.linesIterator.toList

            orbitalTransfers(testMap) mustEqual 4
        }
    }
}
