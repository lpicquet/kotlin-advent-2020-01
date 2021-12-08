import lpicquet.advent2021.day02.part2.Day02Puzzle
import lpicquet.advent2021.day02.part2.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day02PuzzlePart2Test {

    val day02Puzzle = Day02Puzzle(Position())

    companion object {
        @JvmStatic
        fun puzzleInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of("/day02/input.txt"),
                Arguments.of("/day02/input_metapack.txt")
            )
    }

    @Test
    fun itShouldFindThePosition(){
        val position = day02Puzzle.steerFromFile("/day02/sampleInput.txt")
        assertThat(position.x).isEqualTo(15)
        assertThat(position.y).isEqualTo(0,)
        assertThat(position.z).isEqualTo(60)

        assertThat(position.x * position.z).isEqualTo(900)
    }

    @ParameterizedTest
    @MethodSource("puzzleInput")
    fun itShouldSolveThePuzzle(resourcePath: String){
        val position = day02Puzzle.steerFromFile(resourcePath)
        println("multiplied: ${position.x * position.z}")
    }

}