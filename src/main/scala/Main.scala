@main def Main(num:Int): Unit =
    // println(num)
	num match {
        case 1 =>  (new Day1()).print
        case 2 =>  (new Day2()).print
        case 3 =>  (new Day3()).print
        case 4 =>  (new Day4()).print
        case 5 =>  (new Day5()).print
        case 6 =>  (new Day6()).print
        case x => println(":(")
    }

