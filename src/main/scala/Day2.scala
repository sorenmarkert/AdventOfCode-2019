
object Day2 {

    val originalProgram = List(1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 13, 19, 23, 2, 23, 9,
        27, 1, 6, 27, 31, 2, 10, 31, 35, 1, 6, 35, 39, 2, 9, 39, 43, 1, 5, 43, 47, 2, 47, 13, 51, 2, 51, 10, 55, 1, 55,
        5, 59, 1, 59, 9, 63, 1, 63, 9, 67, 2, 6, 67, 71, 1, 5, 71, 75, 1, 75, 6, 79, 1, 6, 79, 83, 1, 83, 9, 87, 2, 87,
        10, 91, 2, 91, 10, 95, 1, 95, 5, 99, 1, 99, 13, 103, 2, 103, 9, 107, 1, 6, 107, 111, 1, 111, 5, 115, 1, 115, 2,
        119, 1, 5, 119, 0, 99, 2, 0, 14, 0)

    def main(args: Array[String]): Unit = {

        val program1202alarm = originalProgram.toArray
        program1202alarm.update(1, 12)
        program1202alarm.update(2, 2)
        println("" + processOpCodes(program1202alarm)(0))

        val results = for {
            x <- 0 to 99
            y <- 0 to 99
        } yield {
            val program = originalProgram.toArray
            program.update(1, x)
            program.update(2, y)
            (x, y, processOpCodes(program))
        }
        println(results.find(_._3(0) == 19690720))
    }

    def processOpCodes(program: Array[Int], pointer: Int = 0): Array[Int] = {
        program(pointer) match {
            case 1 => {
                program.update(program(pointer + 3), program(program(pointer + 1)) + program(program(pointer + 2)))
                processOpCodes(program, pointer + 4)
            }
            case 2 => {
                program.update(program(pointer + 3), program(program(pointer + 1)) * program(program(pointer + 2)))
                processOpCodes(program, pointer + 4)
            }
            case 99 => program
            case _ => throw new IllegalStateException("Unknown OpCode" + program(pointer))
        }
    }
}
