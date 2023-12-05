import scala.util.boundary, boundary.break
import java.awt.Color
class Day2 {
  def print: Unit = {

    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)
    // games.foreach(x=> println(x.mkString("\n")))
    // println(gameStrings.map(_.mkString(" + ")).mkString("\n"))
    // println(gamesPossible.filter(_(0)).mkString("\n"))
    // println(splitRounds("2green"))
    
  }

  private val source = scala.io.Source.fromFile("Input\\2\\input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines = stringSrc.split('\n')

  val gameStrings = lines.map(_.filter(_!=' ').split(':').last.split(';'))
  val games = gameStrings.map(y=>y.map(x => splitRounds(x)))
  val gamesPossible = games.map(x=> x.forall(roundPossible)).zip(LazyList.from(1))
  val maxColors = games.map(x => x.reduce(maxColor))

  val firstPart = gamesPossible.filter(_(0)).map(_(1)).sum
  val secondPart = maxColors.map(calcPower).sum

  def splitRounds(str: String) : Colors =
    var color = new Colors()
    str.trim().split(',').foreach(xs=> 
      if xs.endsWith("red") then color.red += xs.takeWhile(Character.isDigit).toInt
      else if xs.endsWith("green") then color.green += xs.takeWhile(Character.isDigit).toInt
      else if xs.endsWith("blue") then color.blue += xs.takeWhile(Character.isDigit).toInt
    )
    color

  def roundPossible(color:Colors) : Boolean = 
    color.red <= 12 && color.green <= 13 && color.blue <= 14

  def maxColor(a:Colors, b:Colors):Colors = 
    if (b.red > a.red) then a.red = b.red
    if (b.blue > a.blue) then a.blue = b.blue
    if (b.green > a.green) then a.green = b.green
    a
  
  def calcPower(color : Colors) : Int = 
    color.red * color.green * color.blue
  
  class Colors {
    var red:Int = 0;
    var blue:Int = 0;
    var green:Int = 0;
    override def toString(): String = red.toString() + blue.toString() + green.toString()
  }
}
