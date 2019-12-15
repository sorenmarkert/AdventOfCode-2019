import java.lang.Math.{PI, abs, atan2, max, min}

object Day10 {

    val puzzleInput = """##.#..#..###.####...######
                        |#..#####...###.###..#.###.
                        |..#.#####....####.#.#...##
                        |.##..#.#....##..##.#.#....
                        |#.####...#.###..#.##.#..#.
                        |..#..#.#######.####...#.##
                        |#...####.#...#.#####..#.#.
                        |.#..#.##.#....########..##
                        |......##.####.#.##....####
                        |.##.#....#####.####.#.####
                        |..#.#.#.#....#....##.#....
                        |....#######..#.##.#.##.###
                        |###.#######.#..#########..
                        |###.#.#..#....#..#.##..##.
                        |#####.#..#.#..###.#.##.###
                        |.#####.#####....#..###...#
                        |##.#.......###.##.#.##....
                        |...#.#.#.###.#.#..##..####
                        |#....#####.##.###...####.#
                        |#.##.#.######.##..#####.##
                        |#.###.##..##.##.#.###..###
                        |#.####..######...#...#####
                        |#..#..########.#.#...#..##
                        |.##..#.####....#..#..#....
                        |.###.##..#####...###.#.#.#
                        |.##..######...###..#####.#
                        |""".stripMargin

    val stationPosition = (20, 19)

    def main(args: Array[String]) = {

        println(highestVisible(puzzleInput))
        val result = shootLaser(puzzleInput, stationPosition)
        println(result(199))
    }

    def highestVisible(mapString: String) = {

        def visibleCount(coords: (Int, Int), asteroids: List[(Int, Int)]) =
            asteroids.filter(isVisible(_, coords, asteroids)).size

        val allAsteroids = parseMap(mapString).toList
        allAsteroids.map(a => (a, visibleCount(a, allAsteroids diff List(a)))).maxBy(_._2)
    }

    def shootLaser(mapString: String, laser: (Int, Int)) = {

        val allAsteroids = parseMap(mapString).toList

        def shootRemaining(asteroidsLeft: List[(Int, Int)]): List[(Int, Int)] = asteroidsLeft match {
            case Nil => Nil
            case _ => {
                val visibleAsteroids = asteroidsLeft.filter(isVisible(_, laser, asteroidsLeft))
                val visibleWithRadians = visibleAsteroids.map { case (x, y) =>
                    val radians = -atan2(x - laser._1, y - laser._2)
                    ((x, y), if (radians < 0) radians - PI else radians)
                }
                visibleWithRadians.sortBy(_._2).map(_._1) ++ shootRemaining(asteroidsLeft diff visibleAsteroids)
            }
        }

        shootRemaining(allAsteroids diff List(laser))
    }

    private def isVisible(asteroid1: (Int, Int), asteroid2: (Int, Int), asteroids: List[(Int, Int)]) = {

        val blockerPositions = (asteroid1, asteroid2) match {

            case ((x1, y1), (x2, y2)) if x1 == x2 =>
                min(y1, y2) until max(y1, y2) map ((x1, _)) drop 1

            case ((x1, y1), (x2, y2)) if y1 == y2 =>
                min(x1, x2) until max(x1, x2) map ((_, y1)) drop 1

            case ((x1, y1), (x2, y2)) => {

                def gcd(a: Int, b: Int): Int =
                    if (b == 0) a else gcd(b, a % b)

                val deltaX = abs(x2 - x1)
                val deltaY = abs(y2 - y1)
                val vectorDivisor = gcd(deltaX, deltaY)

                val hasPositiveA = (x2 - x1) * (y2 - y1) > 0

                val stepsX = min(x1, x2).until(max(x1, x2), deltaX / vectorDivisor) drop 1
                val stepsY = min(y1, y2).until(max(y1, y2), deltaY / vectorDivisor) drop 1

                (if (hasPositiveA) stepsX else stepsX.reverse) zip stepsY
            }
        }

        asteroids.intersect(blockerPositions).size == 0
    }

    private def parseMap(mapString: String) =
        for {
            (line, y) <- mapString.linesIterator.zipWithIndex
            (field, x) <- line.zipWithIndex
            if Set('X', '#') contains field
        } yield (x, y)
}
