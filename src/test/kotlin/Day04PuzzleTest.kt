import lpicquet.advent2021.day04.Day04Puzzle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day04PuzzleTest {

    val day04Puzzle = Day04Puzzle()
    val winner = true

    companion object {
        @JvmStatic
        fun puzzleInput(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day04/sampleInput.txt"), 4512),
                Arguments.of(fileUtil.readTextFile("/day04/input.txt"), 11774),
                Arguments.of(fileUtil.readTextFile("/day04/input_metapack.txt"), 49686)
            )
        }

        @JvmStatic
        fun puzzleInputPart2(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day04/sampleInput.txt"), 1924),
                Arguments.of(fileUtil.readTextFile("/day04/input.txt"), 4495),
                Arguments.of(fileUtil.readTextFile("/day04/input_metapack.txt"), 26878)
            )
        }
    }

    /***
     * solve the puzzle
     */
    @ParameterizedTest
    @MethodSource("puzzleInput")
    fun itShouldSolveThePuzzlePart1(inputs: List<String>, expected: Int){
        assertThat(day04Puzzle.bingo(inputs, winner)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("puzzleInputPart2")
    fun itShouldSolveThePuzzlePart2(inputs: List<String>, expected: Int){
        assertThat(day04Puzzle.bingo(inputs, !winner)).isEqualTo(expected)

    }

}