
import scala.io.Source

object Day8 {

    val imageSize = 25 * 6

    def main(args: Array[String]): Unit = {

        val inputString = Source.fromFile("input/day8.txt").getLines.next

        val layers = parseImage(inputString)

        val layer = findFewestZeros(layers)
        println(checksum(layers(layer)))
    }

    def parseImage(input: String) = input.grouped(imageSize).toVector

    def findFewestZeros(layers: Vector[String]) = layers.zipWithIndex
        .map { case (l, i) => (l.count(_ == '0'), i) }
        .minBy(_._1)
        ._2

    def checksum(layer: String) = layer.count(_ == '1') * layer.count(_ == '2')
}
