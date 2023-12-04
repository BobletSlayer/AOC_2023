import scala.util.boundary, boundary.break
class Day2 {
  def print: Unit =
    println("First Part:")
  println(firstPart)
  println("Second Part:")
  println(secondPart)

  private val source = scala.io.Source.fromFile("Input\\2\\input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines = stringSrc.split('\n')

  val firstPart = "TODO"
  val secondPart = "TODO"
}
