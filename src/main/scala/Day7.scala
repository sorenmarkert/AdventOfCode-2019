import Day5.runProgram

import scala.collection.mutable.{Queue => MQueue}
import scala.collection.mutable.{Set => MSet}

object Day7 {

    val puzzleInput = List(3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 47, 64, 85, 106, 187, 268, 349, 430, 99999, 3, 9, 1002, 9, 4, 9, 1001, 9, 4, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 1001, 9, 3, 9, 102, 5, 9, 9, 1001, 9, 5, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 102, 5, 9, 9, 1001, 9, 4, 9, 102, 4, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 3, 9, 101, 2, 9, 9, 102, 4, 9, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99)

    def main(args: Array[String]) {

        val permutations = MSet.empty[List[Int]]
        permute(permutations)
        println(permutations.map(phaseSettings => amplifySignal(phaseSettings, puzzleInput)).max)

        val feedbackPermutations = MSet.empty[List[Int]]
        permute(feedbackPermutations, (5 to 9).toList)
        println(feedbackPermutations.map(phaseSettings => feedbackAmplify(phaseSettings, puzzleInput)).max)
    }

    def permute(results: MSet[List[Int]], phaseSettings: List[Int] = (0 to 4).toList, result: List[Int] = Nil): Unit =
        phaseSettings match {
            case Nil => results += result
            case _ => phaseSettings.foreach(x => permute(results, phaseSettings diff List(x), x :: result))
        }

    def amplifySignal(phaseSettings: List[Int], program: List[Int]) = {

        def runAmplifier(input: Int, amplifier: Int) =
            runProgram(program.toArray, Iterator(phaseSettings(amplifier), input)).head

        def runAmplifiers(input: Int = 0, amplifier: Int = 0): Int =
            if (amplifier >= 4) runAmplifier(input, amplifier)
            else runAmplifiers(runAmplifier(input, amplifier), amplifier + 1)

        runAmplifiers()
    }

    def feedbackAmplify(phaseSettings: List[Int], program: List[Int]) = {

        val runningAmplifiers = phaseSettings
            .map(x => PausedProgram(program.toArray, 0, MQueue(x), null, false))
            .toArray

        def restartPrograms(amplifier: Int, inputs: MQueue[Int]): MQueue[Int] = {
            val x = runningAmplifiers(amplifier)
            if (x.isCompleted) {
                runningAmplifiers(4).outputs
            }
            else {
                val res = runWirableProgram(x.program, x.pointer, x.inputs appendAll inputs)
                runningAmplifiers.update(amplifier, res)
                restartPrograms((amplifier + 1) % 5, res.outputs)
            }
        }

        restartPrograms(0, MQueue(0)).dequeue
    }

    def runWirableProgram(program: Array[Int], pointer: Int, inputs: MQueue[Int]) = {

        val outputs = MQueue.empty[Int]

        def processOpCodes(pointer: Int): PausedProgram = {

            def getParameter(position: Int, mode: Char) = mode match {
                case '0' => program(program(position))
                case '1' => program(position)
                case _ => throw new IllegalStateException("Unknown parameter mode " + mode + " at " + program(pointer))
            }

            val (opCode, modes) = {
                val opCodeString = "000" + program(pointer).toString
                (opCodeString.takeRight(2).toInt, opCodeString.reverse.drop(2))
            }

            opCode match {
                case 1 => { // addition
                    program.update(program(pointer + 3), getParameter(pointer + 1, modes(0)) + getParameter(pointer + 2, modes(1)))
                    processOpCodes(pointer + 4)
                }
                case 2 => { // multiplication
                    program.update(program(pointer + 3), getParameter(pointer + 1, modes(0)) * getParameter(pointer + 2, modes(1)))
                    processOpCodes(pointer + 4)
                }
                case 3 => { // input
                    if (inputs.isEmpty) {
                        PausedProgram(program, pointer, inputs, outputs, false)
                    } else {
                        program.update(program(pointer + 1), inputs.dequeue)
                        processOpCodes(pointer + 2)
                    }
                }
                case 4 => { // output
                    outputs.enqueue(getParameter(pointer + 1, modes(0)))
                    processOpCodes(pointer + 2)
                }
                case 5 => { // jump if true
                    processOpCodes(
                        if (0 != getParameter(pointer + 1, modes(0))) getParameter(pointer + 2, modes(1))
                        else pointer + 3)
                }
                case 6 => { // jump if false
                    processOpCodes(
                        if (0 == getParameter(pointer + 1, modes(0))) getParameter(pointer + 2, modes(1))
                        else pointer + 3)
                }
                case 7 => { // less than
                    program.update(program(pointer + 3),
                        if (getParameter(pointer + 1, modes(0)) < getParameter(pointer + 2, modes(1))) 1 else 0)
                    processOpCodes(pointer + 4)
                }
                case 8 => { // equals
                    program.update(program(pointer + 3),
                        if (getParameter(pointer + 1, modes(0)) == getParameter(pointer + 2, modes(1))) 1 else 0)
                    processOpCodes(pointer + 4)
                }
                case 99 => PausedProgram(program, pointer, inputs, outputs, true)
                case _ => throw new IllegalStateException("Unknown OpCode at " + pointer + ":" + program(pointer))
            }
        }

        processOpCodes(pointer)
    }

    case class PausedProgram(program: Array[Int], pointer: Int, inputs: MQueue[Int], outputs: MQueue[Int], isCompleted: Boolean)

}
