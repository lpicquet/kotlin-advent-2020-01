package lpicquet.advent2021.day05

import java.util.stream.Collectors

class Day05Puzzle {

    val lineParseRegex = """(\d+),(\d+)\s->\s(\d+),(\d+)""".toRegex()

    fun dangerZones(inputs: List<String>, includeDiagonals: Boolean): Int {
        val lines = parseLines(inputs, includeDiagonals)
        lines.forEach { line -> println("line: $line - ${line.points}") }
        val pointOverlaps = pointOverlaps(lines)
        println("points of overlap: $pointOverlaps")
        val dangerousPointsOfOverlaps = pointOverlaps.filter { (point, count) -> count >= 2 }
        println("dangerous points of overlap: $dangerousPointsOfOverlaps")
        return dangerousPointsOfOverlaps.count();
    }

    fun parseLines(inputs: List<String>, includeDiagonals: Boolean): List<Line> {
        return inputs.stream().map { input -> parseLine(input)
        }.filter { line -> includeDiagonals || !line.diagonal  }.collect(Collectors.toList())
    }

    fun parseLine(input: String): Line {
        val (point1X, point1Y, point2X, point2Y) = lineParseRegex.find(input)!!.destructured
        return Line(Point(point1X.toInt(), point1Y.toInt()), Point(point2X.toInt(), point2Y.toInt()))
    }

    /**
     * count for each point how many lines go through it
     * @param lines list of lines
     */
    fun pointOverlaps(lines: List<Line>): Map<Point, Int> {
        return lines.flatMap { line -> line.points }.groupBy { point -> point }.mapValues { (_, points) -> points.size }

    }

}