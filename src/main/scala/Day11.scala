
import Day9.runWirableProgram
import scala.collection.mutable.{Queue => MQueue, Map => MMap}
import scalaz._, Scalaz._

object Day11 {

    val puzzleInput = List(3, 8, 1005, 8, 318, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 101, 0, 8, 29, 1, 107, 12, 10, 2, 1003, 8, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 59, 1, 108, 18, 10, 2, 6, 7, 10, 2, 1006, 3, 10, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1002, 8, 1, 93, 1, 1102, 11, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 101, 0, 8, 118, 2, 1102, 10, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 101, 0, 8, 145, 1006, 0, 17, 1006, 0, 67, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 101, 0, 8, 173, 2, 1109, 4, 10, 1006, 0, 20, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8, 201, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 224, 1006, 0, 6, 1, 1008, 17, 10, 2, 101, 5, 10, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1001, 8, 0, 256, 2, 1107, 7, 10, 1, 2, 4, 10, 2, 2, 12, 10, 1006, 0, 82, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1002, 8, 1, 294, 2, 1107, 2, 10, 101, 1, 9, 9, 1007, 9, 988, 10, 1005, 10, 15, 99, 109, 640, 104, 0, 104, 1, 21102, 1, 837548352256L, 1, 21102, 335, 1, 0, 1105, 1, 439, 21102, 1, 47677543180L, 1, 21102, 346, 1, 0, 1106, 0, 439, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21102, 1, 235190374592L, 1, 21101, 393, 0, 0, 1105, 1, 439, 21102, 3451060455L, 1, 1, 21102, 404, 1, 0, 1105, 1, 439, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21102, 837896909668L, 1, 1, 21102, 1, 427, 0, 1105, 1, 439, 21102, 1, 709580555020L, 1, 21102, 438, 1, 0, 1105, 1, 439, 99, 109, 2, 21201, -1, 0, 1, 21102, 1, 40, 2, 21102, 1, 470, 3, 21102, 460, 1, 0, 1106, 0, 503, 109, -2, 2105, 1, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204, -1, 1001, 465, 466, 481, 4, 0, 1001, 465, 1, 465, 108, 4, 465, 10, 1006, 10, 497, 1101, 0, 0, 465, 109, -2, 2105, 1, 0, 0, 109, 4, 1201, -1, 0, 502, 1207, -3, 0, 10, 1006, 10, 520, 21101, 0, 0, -3, 21202, -3, 1, 1, 22101, 0, -2, 2, 21101, 1, 0, 3, 21101, 0, 539, 0, 1106, 0, 544, 109, -4, 2105, 1, 0, 109, 5, 1207, -3, 1, 10, 1006, 10, 567, 2207, -4, -2, 10, 1006, 10, 567, 21202, -4, 1, -4, 1105, 1, 635, 22101, 0, -4, 1, 21201, -3, -1, 2, 21202, -2, 2, 3, 21101, 0, 586, 0, 1105, 1, 544, 22102, 1, 1, -4, 21102, 1, 1, -1, 2207, -4, -2, 10, 1006, 10, 605, 21102, 1, 0, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 627, 21202, -1, 1, 1, 21101, 627, 0, 0, 105, 1, 502, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2105, 1, 0)

    def main(args: Array[String]) = {

        val paintedPanels = paintHull(puzzleInput)
        println(paintedPanels.size)
        println(createImage(paintedPanels))
    }

    def paintHull(initialProgram: List[Long]) = {

        val paintedHullPanels = MMap((0, 0) -> 1)
        var robotPosition = (0, 0)
        var robotDirection = 0

        def moveRobot(colour: Long, turn: Long) {

            paintedHullPanels.update(robotPosition, colour.toInt)

            robotDirection = if (turn == 0L) (robotDirection + 4 - 1) % 4 else (robotDirection + 1) % 4

            robotPosition = robotPosition |+| (robotDirection match {
                case 0 => (0, 1)
                case 1 => (1, 0)
                case 2 => (0, -1)
                case 3 => (-1, 0)
            })
        }

        def takeStep(program: MMap[Int, Long] = MMap(initialProgram.zipWithIndex.map(_.swap): _*), pointer: Int = 0) {

            val initialRun = runWirableProgram(program, MQueue(paintedHullPanels.getOrElse(robotPosition, 0).toLong), pointer)
            moveRobot(initialRun.outputs.dequeue, initialRun.outputs.dequeue)
            if (!initialRun.isCompleted) {
                takeStep(initialRun.program, initialRun.pointer)
            }
        }

        takeStep()

        paintedHullPanels
    }

    def createImage(panels: MMap[(Int, Int), Int]) = {

        val whitePanels = panels.filter { case (_, colour) => colour == 1 }.keys.toList

        val (minX, minY) = (whitePanels.minBy(_._1)._1, whitePanels.minBy(_._2)._2)
        val (maxX, maxY) = (whitePanels.maxBy(_._1)._1, whitePanels.maxBy(_._2)._2)

        val result = new StringBuilder()
        for {
            y <- 0 to (maxY - minY)
            x <- 0 to (maxX - minX)
        } yield {
            result.addOne(if (whitePanels contains(x + minX, y + minY)) '#' else ' ')
            if (x == (maxX - minX)) result.addAll("|\n")
        }
        result.toString
    }
}
