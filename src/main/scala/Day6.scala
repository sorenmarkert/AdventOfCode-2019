
import scala.io.Source

object Day6 {

    def main(args: Array[String]): Unit = {

        val input = Source.fromFile("input/day6.txt").getLines.toList
        println(orbitCount(input))
        println(orbitalTransfers(input))
    }

    def orbitalTransfers(mapStrings: List[String]) = {

        def getOrbitalLine(obj: String, map: Map[String, String]): List[String] = map(obj) match {
            case "COM" => "COM" :: Nil
            case x => x :: getOrbitalLine(x, map)
        }

        val map = parseMap(mapStrings)
        val santa = getOrbitalLine("SAN", map).reverse
        val you = getOrbitalLine("YOU", map).reverse

        val sharedOrbitLength = you.zip(santa).filter(x => x._1 == x._2).length
        you.length + santa.length - (2 * sharedOrbitLength)
    }

    def orbitCount(mapStrings: List[String]) = {

        def countOrbits(obj: String, map: Map[String, String]): Int = obj match {
            case "COM" => 0
            case _ => 1 + countOrbits(map(obj), map)
        }

        val map = parseMap(mapStrings)
        map.map{ case (k, _) => countOrbits(k, map) }.sum
    }

    private def parseMap(mapString: List[String]) =
        mapString.map { line =>
            val parts = line.split("\\)")
            parts(1) -> parts(0)
        }.toMap
}
