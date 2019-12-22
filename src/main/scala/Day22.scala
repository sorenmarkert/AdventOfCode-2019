
import scala.io.Source

object Day22 {

    val newStackPattern = "deal into new stack".r
    val cutPattern = "cut (\\d+)".r
    val cutNegativePattern = "cut -(\\d+)".r
    val incrementPattern = "deal with increment (\\d+)".r

    def main(args: Array[String]): Unit = {

        val input = Source.fromFile("input/day22.txt").getLines.toList

        println(dealDeck(input, (0 until 10007).toList).indexWhere(_ == 2019))
    }

    def dealDeck(techniques: List[String], deck: List[Int]): List[Int] =
        techniques.foldLeft(deck) { (d, tech) => doTechnique(tech, d) }

    def doTechnique(technique: String, deck: List[Int]) = technique match {

        case newStackPattern() => deck.reverse
        case cutPattern(n) => (deck drop n.toInt) ++ (deck take n.toInt)
        case cutNegativePattern(n) => (deck takeRight n.toInt) ++ (deck dropRight n.toInt)
        case incrementPattern(n) =>
            (0 until deck.size zip deck map { case (pos, card) => (pos * n.toInt % deck.size, card) }
                sortBy (_._1) map { case (_, card) => card }).toList
        case _ => throw new Exception("Unknown dealing technique")
    }

}
