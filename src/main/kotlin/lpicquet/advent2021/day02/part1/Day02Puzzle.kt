package lpicquet.advent2021.day02.part1

import lpicquet.advent2021.day01.part1.Day01Puzzle
import java.io.File

class Day02Puzzle (val position: Position) {

    val instructionRegex = """(forward|back|down|up) (\d)""".toRegex()

    fun readTextFile(classPathResource: String): List<String> {
        val uri = Day01Puzzle::class.java.getResource(classPathResource)!!.toURI()
        val file = File(uri)
        return file.readLines()
    }

    fun foward(x:Int =1){
        position.x +=x
    }

    fun back(x:Int =1){
        position.x -=x
    }

    fun dive(z:Int =1){
        position.z +=z
    }

    fun rise(z:Int =1){
        position.z -=z
    }

    fun instructFromFile(classPathResource: String) : Position {
        readTextFile(classPathResource).forEach { instruction -> instruct(instruction) }
        return position
    }

    fun instruct(instructions: List<String>) : Position {
        instructions.forEach { instruction -> instruct(instruction) }
        return position
    }

    /** give an instruction to the submarine. */
    fun instruct(instruction:String) : Position {
        if (instructionRegex.matches(instruction)){
            val (direction, amount) = instructionRegex.find(instruction)!!.destructured
            when(direction) {
                "forward" -> foward(amount.toInt())
                "back" -> back(amount.toInt())
                "down" -> dive(amount.toInt())
                "up" -> rise(amount.toInt())
            }
        }
        println("current position: ${position}")
        return position
    }
}

class Position(var x: Int =0, var y: Int =0, var z: Int=0)
