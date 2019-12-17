
import scala.collection.mutable.{Queue => MQueue, Map => MMap}

object Day9 {

    val puzzleInput = List(1102L, 34463338, 34463338, 63, 1007, 63, 34463338, 63, 1005, 63, 53, 1101, 0, 3, 1000, 109, 988, 209, 12, 9, 1000, 209, 6, 209, 3, 203, 0, 1008, 1000, 1, 63, 1005, 63, 65, 1008, 1000, 2, 63, 1005, 63, 904, 1008, 1000, 0, 63, 1005, 63, 58, 4, 25, 104, 0, 99, 4, 0, 104, 0, 99, 4, 17, 104, 0, 99, 0, 0, 1101, 0, 1, 1021, 1101, 28, 0, 1010, 1101, 36, 0, 1002, 1101, 0, 39, 1014, 1101, 34, 0, 1018, 1101, 0, 32, 1001, 1102, 22, 1, 1017, 1102, 1, 26, 1000, 1102, 1, 27, 1013, 1101, 829, 0, 1022, 1102, 29, 1, 1005, 1102, 1, 681, 1024, 1102, 1, 510, 1029, 1101, 0, 676, 1025, 1101, 31, 0, 1016, 1101, 0, 716, 1027, 1101, 0, 38, 1019, 1102, 21, 1, 1009, 1102, 1, 0, 1020, 1102, 1, 33, 1012, 1102, 1, 723, 1026, 1101, 826, 0, 1023, 1101, 0, 23, 1003, 1101, 0, 37, 1008, 1101, 35, 0, 1007, 1102, 24, 1, 1015, 1101, 25, 0, 1011, 1101, 0, 30, 1004, 1101, 20, 0, 1006, 1102, 519, 1, 1028, 109, 19, 21102, 40, 1, -4, 1008, 1015, 40, 63, 1005, 63, 203, 4, 187, 1106, 0, 207, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -12, 21108, 41, 41, 8, 1005, 1015, 229, 4, 213, 1001, 64, 1, 64, 1105, 1, 229, 1002, 64, 2, 64, 109, 6, 21107, 42, 43, 4, 1005, 1017, 247, 4, 235, 1105, 1, 251, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -8, 1201, 2, 0, 63, 1008, 63, 37, 63, 1005, 63, 271, 1105, 1, 277, 4, 257, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -4, 2102, 1, 0, 63, 1008, 63, 32, 63, 1005, 63, 299, 4, 283, 1105, 1, 303, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 2, 1208, 2, 29, 63, 1005, 63, 325, 4, 309, 1001, 64, 1, 64, 1106, 0, 325, 1002, 64, 2, 64, 109, 18, 1206, 0, 341, 1001, 64, 1, 64, 1106, 0, 343, 4, 331, 1002, 64, 2, 64, 109, -19, 2101, 0, 4, 63, 1008, 63, 20, 63, 1005, 63, 365, 4, 349, 1105, 1, 369, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 10, 1207, -4, 38, 63, 1005, 63, 391, 4, 375, 1001, 64, 1, 64, 1106, 0, 391, 1002, 64, 2, 64, 109, -5, 21107, 43, 42, 5, 1005, 1012, 407, 1106, 0, 413, 4, 397, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 1, 2102, 1, -2, 63, 1008, 63, 19, 63, 1005, 63, 433, 1106, 0, 439, 4, 419, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 12, 1205, 0, 455, 1001, 64, 1, 64, 1105, 1, 457, 4, 445, 1002, 64, 2, 64, 109, -9, 1206, 9, 475, 4, 463, 1001, 64, 1, 64, 1105, 1, 475, 1002, 64, 2, 64, 109, 7, 21102, 44, 1, 1, 1008, 1019, 43, 63, 1005, 63, 495, 1106, 0, 501, 4, 481, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 11, 2106, 0, -1, 4, 507, 1001, 64, 1, 64, 1106, 0, 519, 1002, 64, 2, 64, 109, -27, 21101, 45, 0, 9, 1008, 1011, 47, 63, 1005, 63, 543, 1001, 64, 1, 64, 1106, 0, 545, 4, 525, 1002, 64, 2, 64, 109, -7, 1202, 5, 1, 63, 1008, 63, 25, 63, 1005, 63, 569, 1001, 64, 1, 64, 1105, 1, 571, 4, 551, 1002, 64, 2, 64, 109, 15, 2107, 22, -1, 63, 1005, 63, 591, 1001, 64, 1, 64, 1105, 1, 593, 4, 577, 1002, 64, 2, 64, 109, 4, 2108, 33, -7, 63, 1005, 63, 609, 1105, 1, 615, 4, 599, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 2, 21101, 46, 0, 0, 1008, 1016, 46, 63, 1005, 63, 637, 4, 621, 1106, 0, 641, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -6, 2101, 0, -2, 63, 1008, 63, 40, 63, 1005, 63, 661, 1106, 0, 667, 4, 647, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 14, 2105, 1, 0, 4, 673, 1105, 1, 685, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -16, 1207, -5, 22, 63, 1005, 63, 701, 1106, 0, 707, 4, 691, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 15, 2106, 0, 4, 1001, 64, 1, 64, 1105, 1, 725, 4, 713, 1002, 64, 2, 64, 109, -21, 1202, 3, 1, 63, 1008, 63, 29, 63, 1005, 63, 751, 4, 731, 1001, 64, 1, 64, 1106, 0, 751, 1002, 64, 2, 64, 109, 7, 1201, -5, 0, 63, 1008, 63, 30, 63, 1005, 63, 773, 4, 757, 1105, 1, 777, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -10, 2107, 25, 1, 63, 1005, 63, 799, 4, 783, 1001, 64, 1, 64, 1105, 1, 799, 1002, 64, 2, 64, 109, 15, 1205, 7, 817, 4, 805, 1001, 64, 1, 64, 1106, 0, 817, 1002, 64, 2, 64, 109, 6, 2105, 1, 3, 1106, 0, 835, 4, 823, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -16, 21108, 47, 45, 8, 1005, 1012, 851, 1106, 0, 857, 4, 841, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 1, 1208, 4, 18, 63, 1005, 63, 877, 1001, 64, 1, 64, 1106, 0, 879, 4, 863, 1002, 64, 2, 64, 109, -1, 2108, 21, 5, 63, 1005, 63, 901, 4, 885, 1001, 64, 1, 64, 1106, 0, 901, 4, 64, 99, 21101, 27, 0, 1, 21101, 915, 0, 0, 1105, 1, 922, 21201, 1, 37229, 1, 204, 1, 99, 109, 3, 1207, -2, 3, 63, 1005, 63, 964, 21201, -2, -1, 1, 21101, 942, 0, 0, 1105, 1, 922, 21201, 1, 0, -1, 21201, -2, -3, 1, 21101, 0, 957, 0, 1105, 1, 922, 22201, 1, -1, -2, 1105, 1, 968, 22101, 0, -2, -2, 109, -3, 2105, 1, 0)

    def main(args: Array[String]) {

        val boostCode = runWirableProgram(puzzleInput, MQueue(1))
        println(boostCode)

        val coordinates = runWirableProgram(puzzleInput, MQueue(2))
        println(coordinates)

    }

    def runWirableProgram(programInput: List[Long], inputs: MQueue[Long] = MQueue.empty[Long], pointer: Int = 0): MQueue[Long] = {
        runWirableProgram(MMap(programInput.zipWithIndex.map(_.swap): _*), inputs, pointer).outputs
    }

    def runWirableProgram(program: MMap[Int, Long], inputs: MQueue[Long], pointer: Int): PausedProgram = {

        var relativeBase = 0

        val outputs = MQueue.empty[Long]

        def processOpCodes(pointer: Int): PausedProgram = {

            def getParameter(position: Int, mode: Char) = mode match {
                case '0' => program.get(program(position).toInt).getOrElse(0L)
                case '1' => program.get(position).getOrElse(0L)
                case '2' => program.get(program(position).toInt + relativeBase).getOrElse(0L)
                case _ => throw new IllegalStateException(s"Unknown parameter mode $mode at $pointer:${program(pointer)}")
            }

            def updateAddress(position: Int, mode: Char, value: Long) = mode match {
                case '0' => program.update(program(position).toInt, value)
                case '1' => throw new IllegalStateException(s"Parameter mode 1 not supported for writing at $pointer:${program(pointer)}")
                case '2' => program.update(program(position).toInt + relativeBase, value)
                case _ => throw new IllegalStateException(s"Unknown parameter mode $mode at $pointer:${program(pointer)}")
            }

            val (opCode, modes) = {
                val opCodeString = "0000" + program(pointer).toString
                (opCodeString.takeRight(2).toInt, opCodeString.reverse.drop(2))
            }

            opCode match {
                case 1 => { // addition
                    val res = getParameter(pointer + 1, modes(0)) + getParameter(pointer + 2, modes(1))
                    updateAddress(pointer + 3, modes(2), res)
                    processOpCodes(pointer + 4)
                }
                case 2 => { // multiplication
                    val res = getParameter(pointer + 1, modes(0)) * getParameter(pointer + 2, modes(1))
                    updateAddress(pointer + 3, modes(2), res)
                    processOpCodes(pointer + 4)
                }
                case 3 => { // input
                    if (inputs.isEmpty) {
                        PausedProgram(program, pointer, inputs, outputs, false)
                    } else {
                        updateAddress(pointer + 1, modes(0), inputs.dequeue)
                        processOpCodes(pointer + 2)
                    }
                }
                case 4 => { // output
                    outputs.enqueue(getParameter(pointer + 1, modes(0)))
                    processOpCodes(pointer + 2)
                }
                case 5 => { // jump if true
                    processOpCodes(
                        (if (0 != getParameter(pointer + 1, modes(0))) getParameter(pointer + 2, modes(1))
                        else pointer + 3).toInt)
                }
                case 6 => { // jump if false
                    processOpCodes(
                        (if (0 == getParameter(pointer + 1, modes(0))) getParameter(pointer + 2, modes(1))
                        else pointer + 3).toInt)
                }
                case 7 => { // less than
                    val res = if (getParameter(pointer + 1, modes(0)) < getParameter(pointer + 2, modes(1))) 1 else 0
                    updateAddress(pointer + 3, modes(2), res)
                    processOpCodes(pointer + 4)
                }
                case 8 => { // equals
                    val res = if (getParameter(pointer + 1, modes(0)) == getParameter(pointer + 2, modes(1))) 1 else 0
                    updateAddress(pointer + 3, modes(2), res)
                    processOpCodes(pointer + 4)
                }
                case 9 => { // relative base offset
                    relativeBase += getParameter(pointer + 1, modes(0)).toInt
                    processOpCodes(pointer + 2)
                }
                case 99 => PausedProgram(program, pointer, inputs, outputs, true)
                case _ => throw new IllegalStateException("Unknown OpCode at " + pointer + ":" + program(pointer))
            }
        }

        processOpCodes(pointer)
    }

    case class PausedProgram(program: MMap[Int, Long], pointer: Int, inputs: MQueue[Long], outputs: MQueue[Long], isCompleted: Boolean)

}
