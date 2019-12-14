import Day5.runProgram

import scala.collection.mutable.{ Set => MSet }

object Day7 {

    val puzzleInput = List(3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 47, 64, 85, 106, 187, 268, 349, 430, 99999, 3, 9, 1002, 9, 4, 9, 1001, 9, 4, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 1001, 9, 3, 9, 102, 5, 9, 9, 1001, 9, 5, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 102, 5, 9, 9, 1001, 9, 4, 9, 102, 4, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 3, 9, 101, 2, 9, 9, 102, 4, 9, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99)

    def main(args: Array[String]) {

        val permutations = MSet.empty[List[Int]]
        permute(permutations)
        println(permutations.map(phaseSettings => amplifySignal(phaseSettings, puzzleInput)).max)
    }

    def permute(results: MSet[List[Int]], phaseSettings: List[Int] = List(0, 1, 2, 3, 4), result: List[Int] = Nil): Unit =
        phaseSettings match {
            case Nil => results += result
            case _ => phaseSettings.foreach(x => permute(results, phaseSettings diff List(x), x :: result))
        }

    def amplifySignal(phaseSettings: List[Int], program: List[Int]) = {

        def runIt(phaseSetting: Int, input: Int) = runProgram(program.toArray, Iterator(phaseSetting, input))

        val result1 = runIt(phaseSettings(0), 0)
        val result2 = runIt(phaseSettings(1), result1.head)
        val result3 = runIt(phaseSettings(2), result2.head)
        val result4 = runIt(phaseSettings(3), result3.head)
        val result5 = runIt(phaseSettings(4), result4.head)
        result5.head
    }
}
