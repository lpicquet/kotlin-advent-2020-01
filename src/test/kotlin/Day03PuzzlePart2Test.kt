import lpicquet.advent2021.day03.part2.Day03Puzzle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day03PuzzlePart2Test {
    val day03Puzzle = Day03Puzzle()
    val fileUtil = FileUtil()
    val sampleDiagnostics = fileUtil.readTextFile("/day03/sampleInput.txt")

    companion object {
        @JvmStatic
        fun puzzleInput(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day03/input.txt")),
                Arguments.of(fileUtil.readTextFile("/day03/input_metapack.txt"))
            )
        }
    }

    @Test
    fun itShouldSortTheDiagnosticsPerPosition(){
        val mappedDiagnostic = day03Puzzle.mapDiagnostics(sampleDiagnostics)
        assertThat(mappedDiagnostic).containsExactly(
            "011110011100",
            "010001010101",
            "111111110000",
            "011101100011",
            "000111100100"
        )
    }

    @Test
    fun itShouldCalculateTheGammaDigitFromAString(){
        assertThat(day03Puzzle.gammaDigit("011110011100")).isEqualTo('1')
        assertThat(day03Puzzle.gammaDigit("010001010101")).isEqualTo('0')
        assertThat(day03Puzzle.gammaDigit("111111110000")).isEqualTo('1')
        assertThat(day03Puzzle.gammaDigit("011101100011")).isEqualTo('1')
        assertThat(day03Puzzle.gammaDigit("000111100100")).isEqualTo('0')
    }

    @Test
    fun itShouldCalculateTheValueFromABinaryString(){
        assertThat(day03Puzzle.fromBinary("0")).isEqualTo(0)
        assertThat(day03Puzzle.fromBinary("1")).isEqualTo(1)
        assertThat(day03Puzzle.fromBinary("10")).isEqualTo(2)
        assertThat(day03Puzzle.fromBinary("110001010101010001")).isEqualTo(202065)
    }

    @Test
    fun itShouldCalculateTheGammaDigits(){
        val gammaDigits = day03Puzzle.gammaDigits(sampleDiagnostics)
        println("gammaDigits: $gammaDigits")
        assertThat(gammaDigits).isEqualTo(listOf('1','0','1','1', '0'))
    }

    @Test
    fun itShouldCalculateTheEpsilonDigits(){
        val epsilonDigits = day03Puzzle.epsilonDigits("10110")
        println("epsilon digits: ${epsilonDigits}")
        assertThat(epsilonDigits).isEqualTo(listOf('0','1','0','0', '1'))
    }

    @Test
    fun itShouldCalculateTheGammaRate(){
        assertThat(day03Puzzle.gammaRate(sampleDiagnostics)).isEqualTo(22)
    }

    @Test
    fun itShouldCalculateTheEpsilonRateFromTheGammaDigits(){
        assertThat(day03Puzzle.epsilonRate("10110")).isEqualTo(9)
    }

    @Test
    fun itShouldCalculatesThePowerConsumption(){
        assertThat(day03Puzzle.powerConsumption(sampleDiagnostics)).isEqualTo(198)
    }

    @Test
    fun itShouldCalculateTheOxygenGeneratorRating(){
        assertThat(day03Puzzle.oxygenGeneratorRating(sampleDiagnostics)).isEqualTo(23)
    }

    @Test
    fun itShouldCalculateTheCO2ScrubberRating(){
        assertThat(day03Puzzle.co2ScrubberRating(sampleDiagnostics)).isEqualTo(10)
    }

    @Test
    fun itShouldCalculateTheLifeSupportRating(){
        assertThat(day03Puzzle.lifeSupportRating(sampleDiagnostics)).isEqualTo(230)
    }

    /***
     * solve the puzzle
     */
    @ParameterizedTest
    @MethodSource("puzzleInput")
    fun itShouldSolveThePuzzlePart1(diagnostics: List<String>){
        println(day03Puzzle.diagnose(diagnostics))
    }

    @ParameterizedTest
    @MethodSource("puzzleInput")
    fun itShouldSolveThePuzzlePart2(diagnostics: List<String>){
        println(day03Puzzle.diagnose(diagnostics))
    }



}