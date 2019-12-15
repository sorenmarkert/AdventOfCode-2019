import java.lang.Math.{abs, max, min}

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

    def main(args: Array[String]) = {

        println(highestVisible(puzzleInput))
    }

    def highestVisible(mapString: String) = {

        def parseMap(mapString: String) =
            for {
                (line, y) <- mapString.linesIterator.zipWithIndex
                (field, x) <- line.zipWithIndex
                if field == '#'
            } yield (x, y)

        def visibleCount(coords: (Int, Int), asteroids: List[(Int, Int)]) = {

            def isVisible(asteroid1: (Int, Int), asteroid2: (Int, Int)) = {

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

                asteroids.filter(blockerPositions.contains).size == 0
            }

            asteroids.filter(isVisible(_, coords)).size
        }

        val asteroidCoordinates = parseMap(mapString).toList
        asteroidCoordinates.map(a => (a, visibleCount(a, asteroidCoordinates diff List(a)))).maxBy(_._2)
    }

}
