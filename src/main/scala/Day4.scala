
object Day4 {

    def hasSixDigits(pw: Int) = 100000 to 999999 contains pw

    def hasPair(pw: List[Char]) = pw.groupBy(identity).values.count(_.length == 2) > 0

    def increases(pw: String): Boolean = pw.length == 1 || (pw(0) <= pw(1) && increases(pw.tail))

    def isValid(pw: Int) =
        hasSixDigits(pw) &&
            hasPair(pw.toString.toCharArray.toList) &&
            increases(pw.toString)

    def bruteIt(start: Int, end: Int) = start to end count isValid

    def main(args: Array[String]): Unit = {

        assert(isValid(112233))
        assert(! isValid(123444))
        assert(isValid(111122))
        println(bruteIt(246540, 787419))
    }
}
