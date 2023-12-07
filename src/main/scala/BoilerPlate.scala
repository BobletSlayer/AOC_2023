class DayX {
  def print: Unit = {
    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)
  }

  private val source = scala.io.Source.fromFile("Input\\X\\Input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines = stringSrc.split("\r\n")

  val firstPart = "TODO"
  val secondPart = "TODO"
}
