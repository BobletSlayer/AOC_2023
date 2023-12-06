import scala.util.boundary, boundary.break
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
class Day3 {
  def print: Unit = {
    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)
    // println(findNum(0, 0))
    // println(lines(0)(0))
    // println(lines(0)(0).isDigit)
    // println(lines.mkString("\n"))
    // println(generateAdjacentPositions(0, 0).filter(positionInArray))
  }

  private val source = scala.io.Source.fromFile("Input\\3\\input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines = stringSrc.split("\r\n")

  type PartNumber = (Int, Boolean)

  val width = lines(0).length
  val height = lines.length

  val firstPart =
    lines.zipWithIndex.map(x => lineToNumbers(x(0), x(1))).flatten.sum
  val secondPart =
    lines.zipWithIndex.map(findGears).flatten.map(processGear).sum
  // val asd =
  //   lines.drop(1).zipWithIndex.map(findGears).flatten.map(processGear).sum

  def lineToNumbers(
      line: String,
      row: Int
  ): List[Int] =
    var i = 0
    var positions = ListBuffer[Int]()
    while i < line.length do
      var adjacentSymbol = false
      var sNum = ""
      while i < line.length && Character.isDigit(line(i)) do
        adjacentSymbol = adjacentSymbol || checkPosition(row, i)
        sNum += (line(i))
        i += 1
      if !sNum.isEmpty() then
        val num = sNum.toInt
        if adjacentSymbol then positions += num
      i += 1
    positions.toList

  def checkPosition(position: (Int, Int)): Boolean =
    generateAdjacentPositions(position)
      .filter(positionInArray)
      .exists(isSymbol)

  def generateAdjacentPositions(pos: (Int, Int)): Seq[(Int, Int)] =
    for
      i <- -1 to 1
      j <- -1 to 1
    yield (pos(0) + i, pos(1) + j)

  def isSymbol(x: Int, y: Int): Boolean =
    lines(x)(y) != '.' && !Character.isDigit(lines(x)(y))

  def positionInArray(x: (Int, Int)): Boolean = x match
    case (x, y) => !(x < 0 || x >= height || y < 0 || y >= width)

  def processGear(pos: (Int, Int)): Int =
    // does not work if adjacent numbers are the same :(
    val nums = generateAdjacentPositions(pos).map(findNum).flatten.distinct
    if nums.length > 1 then nums.product else 0

  def findNum(pos: (Int, Int)): Option[Int] = pos match
    case (row, col) =>
      var x = row
      var y = col
      if lines(x)(y).isDigit
      then
        while y > 0 && lines(x)(y - 1).isDigit
        do y -= 1
        Some(lines(x).drop(y).takeWhile(_.isDigit).toInt)
      else None

  def findGears(line: (String, Int)): Seq[(Int, Int)] = line match
    case (str, row) =>
      str.zipWithIndex.filter((x, y) => x == '*').map((x, y) => (row, y))
  // lazy val findGears: ((String, Int)) => Seq[(Int, Int)] = 
  //     (str, row) =>
  //       str.zipWithIndex.filter((x, y) => x == '*').map((x, y) => (row, y))
  
}
