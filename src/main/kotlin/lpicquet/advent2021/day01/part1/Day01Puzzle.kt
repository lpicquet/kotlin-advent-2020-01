package lpicquet.advent2021.day01.part1

import java.io.File

class Day01Puzzle {

    fun increases(depths: List<Int>): Int {
        // this is a map reduce operation
        // first, we map to a boolean indicating if the depth has increased
        return depths.mapIndexed() { index, depth ->
            println("index: ${index}")
            println("depth: ${depth}")
            if (index == 0) {
                false
            } else {
                depth > depths[index - 1]
            }
        }
            // we then need to convert to a list of numbers that we can add up
            .map { isIncreased ->
                if (isIncreased == true) 1 else {
                    0
                }
            }
            // then, we reduce the boolean to a count
            .stream().reduce(0) { accumulated: Int, newValue: Int -> accumulated + newValue }
    }

}