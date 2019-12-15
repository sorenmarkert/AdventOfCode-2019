
import scala.io.Source

object Day8 {

    val imageWidth = 25
    val imageHeight = 6
    val imageSize = imageWidth * imageHeight

    def main(args: Array[String]): Unit = {

        val layers = parseImage(Source.fromFile("input/day8.txt").getLines.next)

        val checksumLayer = findFewestZeros(layers)
        println(checksum(layers(checksumLayer)))

        val image = decodeImage(layers)
        println(image)
    }

    def parseImage(input: String) = input.grouped(imageSize).toVector

    def findFewestZeros(layers: Vector[String]) = layers.zipWithIndex
        .map { case (l, i) => (l.count(_ == '0'), i) }
        .minBy(_._1)
        ._2

    def checksum(layer: String) = layer.count(_ == '1') * layer.count(_ == '2')

    def decodeImage(layers: Vector[String]) = (for {
        position <- 0 until imageSize
        layer <- 0 until layers.size
    } yield {
        layers(layer)(position)
    })
        .grouped(layers.size)
        .map(_.find(_ != '2'))
        .flatten
        .map(c => Map('0' -> ' ', '1' -> '#')(c))
        .grouped(imageWidth)
        .map(_.mkString)
        .mkString("\n")

}
