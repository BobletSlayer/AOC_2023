import scala.collection.immutable.NumericRange
class Day5 {
  def print: Unit = {
    println("First Part:")
    println(firstPart)
    println("Second Part:")
    println(secondPart)

  }

  private val source = scala.io.Source.fromFile("Input\\5\\input.txt")
  val stringSrc =
    try source.mkString
    finally source.close()
  val input = stringSrc.split("\r\n\r\n")
  val seeds = input.head.split(": ").last.split(' ').map(BigInt(_))
  val locationMaps =
    input.tail.map(x => locationMapTofarmMaps(x.split("\r\n").toIndexedSeq))

  type farmMap = (BigInt, BigInt, BigInt => BigInt)
  val firstPart =
    seeds.map(seed => calcSeed(locationMaps.toIndexedSeq, seed)).min
  val secondPart =
    seedsToRanges(seeds.toList).map(minFromRange).min

  def locationMapTofarmMaps(strs: Seq[String]) =
    strs.tail.map(lineToFarmMap)

  def lineToFarmMap(str: String): farmMap =
    val data = str.split(' ').map(BigInt(_))
    ((data(1), data(1) + data(2), ((x: BigInt) => x + (data(0) - data(1)))))

  def calcSeed(maps: Seq[Seq[farmMap]], seed: BigInt) =
    var x = seed
    maps.foreach(m => x = toDestinationNumber(m, x))
    x

  def toDestinationNumber(map: Seq[farmMap], seed: BigInt): BigInt =
    map.find((x, y, f) => seed >= x && seed < y) match
      case None            => seed
      case Some((_, _, f)) => f(seed)

  def minFromRange(x: (BigInt, BigInt)) = x match
    case (start, end) =>
      var i = start
      var min = calcSeed(locationMaps.toIndexedSeq, i)
      while i < end
      do
        val n = calcSeed(locationMaps.toIndexedSeq, i)
        i += 1
        if n < min then min = n
      min

  def seedsToRanges(seq: List[BigInt]): List[(BigInt, BigInt)] = seq match
    case x1 :: x2 :: xs => (x1, x1 + x2) +: seedsToRanges(xs)
    case _              => Nil

}
