package lpicquet.advent2021.day05

data class Point(val x:Int, val y:Int) : Comparable<Point> {

    override fun compareTo(other: Point): Int {
        val compareX = this.x.compareTo(other.x)
        return if (compareX == 0) compareX else y.compareTo(other.y)
    }
}
