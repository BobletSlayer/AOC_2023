import scala.util.boundary, boundary.break
class Day3 {
  def print: Unit = {
    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)
    println(lines.mkString("\n"))
    println(generatePositions(0,0).filter(positionInArray))
  }

  private val source = scala.io.Source.fromFile("Input\\3\\test1.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines = stringSrc.split("\r\n")

  type PartNumber = (Int, Boolean)

  val width = lines(0).length
  val height = lines.length

  val firstPart = "TODO"
  val secondPart = "TODO"

  // def getNumbers(line: Array[String], row:Int, col:Int = 0) = line match{
  //   case x :: xs if Character.isDigit(x) => {
  //     var num = (x ++ xs.takeWhile(Character.isDigit)).toInt
  //     // checkNumber(num,) 
  //   }
  //   }
  


  def checkNumber(num:Int, positions:Array[(Int,Int)]) =
    (num, checkPositions(positions))
    
  def checkPositions (positions:Array[(Int,Int)]) =
    positions.map(generatePositions).flatten.filter(positionInArray).exists(isSymbol)

  def generatePositions (pos:(Int,Int)) = 
    for i <- -1 to 1
        j <- -1 to 1
    yield (pos(0)+i, pos(1)+j) 

  def isSymbol (x:Int, y:Int) = 
    lines(x)(y) != '.' && !Character.isDigit(lines(x)(y))

  def positionInArray(x:(Int, Int)) = x match
    case (x,y) => !(x < 0 || x >= height || y < 0 || y >= width)

}
