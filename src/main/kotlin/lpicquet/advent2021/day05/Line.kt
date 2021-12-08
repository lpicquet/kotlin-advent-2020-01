package lpicquet.advent2021.day05

import kotlin.math.max
import kotlin.math.min

data class Line(val origin: Point, val end: Point) {

    val points = ArrayList<Point>()
    private val vertical = origin.x == end.x
    private val horizontal = origin.y == end.y
    val diagonal = !(horizontal || vertical)
    val slope = if (!vertical) (end.y - origin.y) / (end.x - origin.x) else null
    val yIntercept = if (!vertical) origin.y - (slope!! * origin.x) else null

    // extract the points on the line
    init {
        // for each x between origin and destination
        val minX = min(origin.x, end.x)
        val maxX = max(origin.x, end.x)
        val minY = min(origin.y, end.y)
        val maxY = max(origin.y, end.y)


        // for each x and y between origin and destination
        for (x in minX..maxX) {
            for (y in minY..maxY) {
                if (diagonal) {
                    if (((slope!! * x) + yIntercept!!) == y) {
                        points.add(Point(x, y))
                    }
                } else {
                    points.add(Point(x, y))
                }
            }
        }
    }

}