import lpicquet.advent2021.day01.part1.Day01Puzzle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day01PuzzlePart1Test {

    private val day01Puzzle = Day01Puzzle()
    private val fileUtil = FileUtil()

    companion object {
        @JvmStatic
        fun provideTestArguments(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1, 2),    1),
                Arguments.of(listOf(1, 1),    0),
                Arguments.of(listOf(2, 1),    0),
                Arguments.of(listOf(1, 2, 3), 2),
                Arguments.of(listOf(1, 2, 2), 1),
                Arguments.of(listOf(1, 2, 1), 1),
                Arguments.of(listOf(1, 1, 1), 0)
            )
    }

    @ParameterizedTest
    @MethodSource("provideTestArguments")
    fun itShouldCountHowManyTimesTheListIncreases(listOfNumbers: List<Int>, expectedNumberOfIncreases: Int){
        assertThat(day01Puzzle.increases(listOfNumbers)).isEqualTo(expectedNumberOfIncreases)
    }

    @Test
    fun itShouldCountHowManyTimesTheListIncreasedFromTheFile(){
        assertThat(day01Puzzle.increases(fileUtil.readIntegersFile("/day01/integersFile.txt"))).isEqualTo(4)
        assertThat(day01Puzzle.increases(fileUtil.readIntegersFile("/day01/sampleInput.txt"))).isEqualTo(7)
    }

    @Test
    fun itShouldSolveThePuzzleWhenIAmLoggedInWithMyGmailAddress(){
        println(day01Puzzle.increases(fileUtil.readIntegersFile(("/day01/input.txt"))))
    }

    @Test
    fun itShouldSolveThePuzzleWhenIAmLoggedInWithMyMetapackAddress(){
        println(day01Puzzle.increases(fileUtil.readIntegersFile("/day01/input_metapack.txt")))
    }

}