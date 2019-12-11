
object Day4 {

    def hasSixDigits(pw: Int) = 100000 to 999999 contains pw

    def hasPair(pw: String): Boolean =
        pw.length > 1 &&
            (pw(0) == pw(1) || hasPair(pw.tail))

    def increases(pw: String): Boolean = pw.length == 1 || (pw(0) <= pw(1) && increases(pw.tail))

    def isValid(pw: Int) = hasSixDigits(pw) && hasPair(pw.toString) && increases(pw.toString)

    def bruteIt(start: Int, end: Int) = start to end count isValid

    def main(args: Array[String]): Unit = {

        println(bruteIt(246540, 787419))
    }
}
