import lpicquet.advent2021.day06.Day06Puzzle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day06PuzzleTest {

    val day06Puzzle = Day06Puzzle()

    companion object {
        @JvmStatic
        fun puzzleInputPart1(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day06/sampleInput.txt"), 18, 26L),
                Arguments.of(fileUtil.readTextFile("/day06/sampleInput.txt"), 80, 5934L),
                Arguments.of(fileUtil.readTextFile("/day06/input.txt"), 80, 359344L),
                Arguments.of(fileUtil.readTextFile("/day06/input_metapack.txt"), 80, 386536L),
            )
        }

        @JvmStatic
        fun puzzleInputPart2(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day06/sampleInput.txt"), 256, 26984457539L),
                Arguments.of(fileUtil.readTextFile("/day06/input.txt"), 256, 1629570219571L),
                Arguments.of(fileUtil.readTextFile("/day06/input_metapack.txt"), 256, 1732821262171L)
            )
        }
    }


    /**
     * solve the puzzle
     */
    @ParameterizedTest
    @MethodSource("puzzleInputPart1")
    fun itShouldSolveThePuzzlePart1(inputs: List<String>, numberOfDays: Int, expected: Long){
        assertThat(day06Puzzle.schoolSize(inputs, numberOfDays)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("puzzleInputPart2")
    fun itShouldSolveThePuzzlePart2(inputs: List<String>, numberOfDays: Int,  expected: Long){
        assertThat(day06Puzzle.schoolSize(inputs, numberOfDays)).isEqualTo(expected)
    }

}