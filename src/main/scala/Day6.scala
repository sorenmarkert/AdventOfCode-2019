import java.io.{File, PrintWriter}

import scala.io.Source

object Day6 {

    def main(args: Array[String]): Unit = {

        val input = Source.fromFile("input/day6.txt").getLines
        println(orbitCount(input))
    }

    def orbitCount(mapStrings: Iterator[String]) = {

        def parseMap(mapString: Iterator[String]): Map[String, String] =
            mapString.map { line =>
                val parts = line.split("\\)")
                parts(1) -> parts(0)
            }.toMap

        def countOrbits(obj: String, map: Map[String, String]): Int = obj match {
            case "COM" => 0
            case _ => 1 + countOrbits(map(obj), map)
        }

        val map = parseMap(mapStrings)
        map.map{ case (k, _) => countOrbits(k, map) }.sum
    }
}
