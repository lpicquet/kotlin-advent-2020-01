package lpicquet.advent2021.day06

import kotlin.collections.HashMap

class Day06Puzzle {

    fun schoolSize(inputs: List<String>, numberOfDays: Int): Long {
        val schoolTimers = inputs.flatMap { line -> line.split(",") }.map { it.toInt() }
        var fishPerTimer = schoolTimers.groupBy { it }.mapValues { (_, fishes) -> fishes.size.toLong() }

        for (day in 0..numberOfDays - 1){
            fishPerTimer = passTheTime(fishPerTimer)
        }

        return fishPerTimer.values.sum()
    }

    fun passTheTime(fishPerTimer: Map<Int,Long>): HashMap<Int, Long> {
        val aNewDay: HashMap<Int,Long> = HashMap()
        // shift all the values by 1 towards 'the left'
        val fishToReproduce = fishPerTimer.getOrDefault(0, 0)
        for ((timer, numberOfFishes) in fishPerTimer) {
            if (timer == 0) continue
            aNewDay[timer-1] = numberOfFishes
        }
        aNewDay[8] = fishToReproduce
        aNewDay[6] = aNewDay.getOrDefault(6, 0) + fishToReproduce
        return aNewDay
    }
}