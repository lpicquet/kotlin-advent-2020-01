package lpicquet.advent2021.day01.part2

import java.io.File

class Day01Puzzle {

    fun increases(depths: List<Int>, windowSize: Int): Int {
        // this is a map reduce operation
        // first, we map to a boolean indicating if the depth has increased compared to 3 numbers ago
        return depths.mapIndexed() { index, depth ->
            if ( index < windowSize){
                false
//            } else if (index > depths.size - windowSize) {
//                false
            } else {
                val previousValueIndex = index - windowSize
                depth > depths[previousValueIndex]
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