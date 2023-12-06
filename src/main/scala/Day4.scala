import scala.util.boundary, boundary.break
class Day4 {
  def print: Unit = {
    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)
  }
  type Card = (Seq[Int], Seq[Int])

  private val source = scala.io.Source.fromFile("Input\\4\\input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val lines  = stringSrc.split("\r\n")
  val parsed = lines.map(parseInput)

  val firstPart =parsed.map((x,y) => 😲(1,x.intersect(y).length,_*2)).toList.sum
  val secondPart = asd(parsed.map((x,y) => x.intersect(y).length).zip(LazyList.continually(1)).toList)

  def asd (wonCards: Seq[(Int,Int)]):Int = wonCards match
    case Nil => 0
    case (x,y)::xs => y + asd(xs.take(x).map((a,b) => (a,b+y)) ++ xs.drop(x))

  def 😲(a:Int, b:Int, f:(Int => Int)):Int = b match
    case 0 => 0
    case 1 => a
    case x => f(😲(a,b-1,f))
  
  def parseInput (line:String): Card = 
    var data = line.split(':').last.split('|')
    (parseNumbers(data(0)), parseNumbers(data(1)))

  def parseNumbers (str:String) : Seq[Int] = 
    str.trim.replaceAll(" +", " ").split(' ').toIndexedSeq.map(_.trim.toInt)  


}
