package lpicquet.advent2021.day02.part2

import java.io.File

class Day02Puzzle (val position: Position) {

    private val instructionRegex = """(forward|back|down|up) (\d)""".toRegex()

    fun readTextFile(classPathResource: String): List<String> {
        val uri = Day02Puzzle::class.java.getResource(classPathResource)!!.toURI()
        val file = File(uri)
        return file.readLines()
    }

    private fun forward(thrust: Int = 1) {
        position.x += thrust
        position.z += getDepthModifier(thrust)
    }

    private fun back(thrust:Int =1){
        position.x -= thrust
    }

    private fun down(thrust:Int =1){
        position.aim += thrust
    }

    private fun up(thrust:Int =1){
        position.aim -= thrust
    }

    fun steerFromFile(classPathResource: String) : Position {
        readTextFile(classPathResource).forEach { instruction -> steer(instruction) }
        return position
    }

    private fun steer(instructions: List<String>) : Position {
        instructions.forEach { instruction -> steer(instruction) }
        return position
    }

    /** give an instruction to the submarine. */
    private fun steer(instruction:String) : Position {
        if (instructionRegex.matches(instruction)){
            val (direction, amount) = instructionRegex.find(instruction)!!.destructured
            when(direction) {
                "forward" -> forward(amount.toInt())
                "back" -> back(amount.toInt())
                "down" -> down(amount.toInt())
                "up" -> up(amount.toInt())
            }
        }
        println("current position: ${position}")
        return position
    }


    /**
     * Calculate the amount to modify the depth by proportion of the amount of forward thrust
     */
    private fun getDepthModifier(amount: Int): Int {
        println("aim is ${position.aim}")
        var depthAmount = 0
        if (position.aim != 0) {
            depthAmount = amount * position.aim
        }
        return depthAmount
    }
}

class Position(var x: Int =0, var y: Int =0, var z: Int=0, var aim :Int =0){
    override fun toString(): String {
        return "position {x : ${x}, y: ${y}, z: ${z}, aim: ${aim}}"
    }
}
