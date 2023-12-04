import scala.util.boundary, boundary.break
class Day1 {
    def print: Unit =
        println("First Part:")
        println(firstPart)
        println("Second Part:")
        println(secondPart)

    private val source = scala.io.Source.fromFile("Input\\1\\input.txt")
    val stringSrc =
    try source.mkString
    finally source.close()
    val lines = stringSrc.split('\n')

    val nums =  Array("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zip('1' to '9')

    val digits = lines.map(_.filter(Character.isDigit))
    val numbers = digits.map(firstAndLastDigit(_).toInt)

    val firstPart = numbers.sum
    val secondPart = lines.map(x=>firstAndLastDigit(replaceTextWithDigits(x).filter(Character.isDigit)).toInt).sum

    def firstAndLastDigit(str: String) = str.head + str.takeRight(1)
    def replaceTextWithDigits(str: String) : String = str match {
        case "" => ""
        case xs => replacetext(xs) + replaceTextWithDigits(xs.tail)
    }
    def replacetext(str:String):Char = {
        var num = str.head
        boundary: 
            for (x <- nums) if str.startsWith(x(0)) then  
                num = x(1)
                break()
        return num
    }
}
