
object Day5 {

    val puzzleInput = Array(3,225,1,225,6,6,1100,1,238,225,104,0,101,67,166,224,1001,224,-110,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,2,62,66,224,101,-406,224,224,4,224,102,8,223,223,101,3,224,224,1,224,223,223,1101,76,51,225,1101,51,29,225,1102,57,14,225,1102,64,48,224,1001,224,-3072,224,4,224,102,8,223,223,1001,224,1,224,1,224,223,223,1001,217,90,224,1001,224,-101,224,4,224,1002,223,8,223,1001,224,2,224,1,223,224,223,1101,57,55,224,1001,224,-112,224,4,224,102,8,223,223,1001,224,7,224,1,223,224,223,1102,5,62,225,1102,49,68,225,102,40,140,224,101,-2720,224,224,4,224,1002,223,8,223,1001,224,4,224,1,223,224,223,1101,92,43,225,1101,93,21,225,1002,170,31,224,101,-651,224,224,4,224,102,8,223,223,101,4,224,224,1,223,224,223,1,136,57,224,1001,224,-138,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1102,11,85,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1107,226,226,224,102,2,223,223,1006,224,329,1001,223,1,223,1007,226,677,224,1002,223,2,223,1005,224,344,101,1,223,223,108,677,677,224,1002,223,2,223,1006,224,359,101,1,223,223,1008,226,226,224,1002,223,2,223,1005,224,374,1001,223,1,223,108,677,226,224,1002,223,2,223,1006,224,389,101,1,223,223,7,226,226,224,102,2,223,223,1006,224,404,101,1,223,223,7,677,226,224,1002,223,2,223,1005,224,419,101,1,223,223,107,226,226,224,102,2,223,223,1006,224,434,1001,223,1,223,1008,677,677,224,1002,223,2,223,1005,224,449,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,464,1001,223,1,223,1108,226,677,224,1002,223,2,223,1005,224,479,1001,223,1,223,8,677,226,224,102,2,223,223,1006,224,494,1001,223,1,223,1108,677,677,224,102,2,223,223,1006,224,509,1001,223,1,223,1007,226,226,224,1002,223,2,223,1005,224,524,1001,223,1,223,7,226,677,224,1002,223,2,223,1005,224,539,1001,223,1,223,8,677,677,224,102,2,223,223,1005,224,554,1001,223,1,223,107,226,677,224,1002,223,2,223,1006,224,569,101,1,223,223,1107,226,677,224,102,2,223,223,1005,224,584,1001,223,1,223,1108,677,226,224,102,2,223,223,1006,224,599,1001,223,1,223,1008,677,226,224,102,2,223,223,1006,224,614,101,1,223,223,107,677,677,224,102,2,223,223,1006,224,629,1001,223,1,223,1107,677,226,224,1002,223,2,223,1005,224,644,101,1,223,223,8,226,677,224,102,2,223,223,1005,224,659,1001,223,1,223,1007,677,677,224,102,2,223,223,1005,224,674,1001,223,1,223,4,223,99,226)

    def main(args: Array[String]) {
/*
        assert(runProgram(Array(1002, 4, 3, 4, 33))._1.last == 99)
        assert(runProgram(Array(1101, 100, -1, 4, 0))._1.last == 99)
        assert(runProgram(Array(3, 4, 1101, 95, 99999, 6, 0), Iterator(4))._1.last == 99)

        val outputTest = runProgram(Array(1101, 100, -1, 6, 4, 6, 0))
        assert(outputTest._1.last == 99)
        assert(outputTest._2 == List(99))
*/

        println(runProgram(puzzleInput, Iterator(1))._2.reverse)
    }

    def runProgram(program: Array[Int], inputs: Iterator[Int] = Iterator()) = {

        def processOpCodes(pointer: Int = 0, outputs: List[Int] = Nil): List[Int] = {

            def getParameter(position: Int, mode: Char) = mode match {
                case '0' => program(program(position))
                case '1' => program(position)
                case _ => throw new IllegalStateException("Unknown parameter mode " + mode + " at " + program(pointer))
            }

            val (opCode, modes) = {
                val opCodeString = "0000" + program(pointer).toString
                (opCodeString.takeRight(2).toInt, opCodeString.reverse.drop(2))
            }

            opCode match {
                case 1 => {
                    program.update(program(pointer + 3), getParameter(pointer + 1, modes(0)) + getParameter(pointer + 2, modes(1)))
                    processOpCodes(pointer + 4, outputs)
                }
                case 2 => {
                    program.update(program(pointer + 3), getParameter(pointer + 1, modes(0)) * getParameter(pointer + 2, modes(1)))
                    processOpCodes(pointer + 4, outputs)
                }
                case 3 => {
                    program.update(program(pointer + 1), inputs.next)
                    processOpCodes(pointer + 2, outputs)
                }
                case 4 => {
                    processOpCodes(pointer + 2, program(program(pointer + 1)) :: outputs)
                }
                case 99 => outputs
                case _ => throw new IllegalStateException("Unknown OpCode " + program(pointer))
            }
        }

        (program, processOpCodes())
    }
}
