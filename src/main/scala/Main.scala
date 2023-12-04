@main def Main(num:Int): Unit =
    // println(num)
	num match {
        case 1 =>  (new Day1()).print
        case 2 =>  (new Day2()).print
    }

