import java.lang.String
@main def Day1: Unit =
	println("First Part:")
	println(firstPart)
	println("Second Part:")
	println(secondPart)

val source = scala.io.Source.fromFile("Input\\1.txt")
val stringSrc =
  try source.mkString
  finally source.close()
val lines = stringSrc.split('\n')

val digits = lines.map(_.filter(Character.isDigit))
val numbers = digits.map(firstAndLastDigit(_).toInt)

val firstPart = numbers.sum
val secondPart = lines.map(x=>firstAndLastDigit(replaceTextWithDigits(x).filter(Character.isDigit)).toInt).sum

def firstAndLastDigit(str: String) = str.head + str.takeRight(1)
def replaceTextWithDigits(str: String) : String = str match {
	case "" => ""
	case xs if xs.startsWith("one") => '1' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("two") => '2' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("three") => '3' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("four") => '4' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("five") => '5' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("six") => '6' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("seven") => '7' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("eight") => '8' + replaceTextWithDigits(xs.tail)
	case xs if xs.startsWith("nine") => '9' + replaceTextWithDigits(xs.tail)
	case xs => xs.head + replaceTextWithDigits(xs.tail)

}
