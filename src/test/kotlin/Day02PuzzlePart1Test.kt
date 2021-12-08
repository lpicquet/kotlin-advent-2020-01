import lpicquet.advent2021.day02.part1.Day02Puzzle
import lpicquet.advent2021.day02.part1.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day02PuzzlePart1Test {

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
        val position = day02Puzzle.instructFromFile("/day02/sampleInput.txt")
        assertThat(position.x).isEqualTo(15)
        assertThat(position.y).isEqualTo(0,)
        assertThat(position.z).isEqualTo(10)

        assertThat(position.x * position.z).isEqualTo(150)
    }

    @ParameterizedTest
    @MethodSource("puzzleInput")
    fun itShouldSolveThePuzzle(resourcePath: String){
        val position = day02Puzzle.instructFromFile(resourcePath)
        println("multiplied: ${position.x * position.z}")
    }

}