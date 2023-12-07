class Day6 {
  def print: Unit = {
    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)

  }

  private val source = scala.io.Source.fromFile("Input\\6\\input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines = stringSrc.split("\r\n")
  val parsedFst = parseInputFstPart(lines)
  val parsedSnd = parseInputSndPart(lines)

  val firstPart = parsedFst.map(higherThanDistance).product
  val secondPart = higherThanDistance(parsedSnd(0), parsedSnd(1))

  def parseInputFstPart(lst: Array[String]): Array[(Double, Double)] =
    def lineToInts(x: String) = x
      .replaceAll("\t", " ")
      .split(':')
      .last
      .trim
      .split(' ')
      .filter(!_.isEmpty)
      .map(_.trim.toDouble)
    lineToInts(lst(0)).zip(lineToInts(lst(1)))

  def parseInputSndPart(lst: Array[String]): (Double, Double) =
    (lst(0).filter(_.isDigit).toDouble, lst(1).filter(_.isDigit).toDouble)

  def higherThanDistance(t: Double, d: Double): Int =
    var dmt = Math.sqrt(t * t - 4 * d)
    (Math.abs(((t - dmt) / 2 + 1).floor - ((t + dmt) / 2 - 1).ceil) + 1).toInt

  // def higherThanDistance(timeAndDistance: (Double, Double)) = timeAndDistance match
  //   case (t, d) => (0 until t.toInt).map(x => x * (t - x)).filter(_ > d).length
}
