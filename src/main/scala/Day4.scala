
object Day4 {

    def hasSixDigits(pw: Int) = 100000 to 999999 contains pw

    def hasPair(tripleAllowed: Boolean, pw: List[Char]) =
        0 < pw.groupBy(identity)
            .values
            .count(chars => (tripleAllowed && chars.length >= 2) || chars.length == 2)

    def increases(pw: String): Boolean = pw.length == 1 || (pw(0) <= pw(1) && increases(pw.tail))

    def isValid(tripleAllowed: Boolean)(pw: Int) =
        hasSixDigits(pw) &&
            hasPair(tripleAllowed, pw.toString.toList) &&
            increases(pw.toString)

    def bruteIt(start: Int, end: Int, tripleAllowed: Boolean) = start to end count isValid(tripleAllowed)

    def main(args: Array[String]): Unit = {

        println(bruteIt(272091, 815432, true))
        println(bruteIt(272091, 815432, false))
    }
}
