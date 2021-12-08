import lpicquet.advent2021.day03.part1.Day03Puzzle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03PuzzlePart1Test {

    val day03Puzzle = Day03Puzzle()
    val fileUtil = FileUtil()
    val sampleDiagnostics = fileUtil.readTextFile("/day03/sampleInput.txt")
    val metapackDiagnostics = fileUtil.readTextFile("/day03/input_metapack.txt")

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
        assertThat(day03Puzzle.consumption(sampleDiagnostics)).isEqualTo(198)
    }

    @Test
    fun itShouldSolveThePuzzleWhenIAmLoggedInWithMyGmailAddressPart1(){
        println(day03Puzzle.diagnose(fileUtil.readTextFile("/day03/input.txt")))
    }

    @Test
    fun itShouldSolveThePuzzleWhenIAmLoggedInWithMyGmailAddressPart2(){
        println(day03Puzzle.diagnose(fileUtil.readTextFile("/day03/input.txt")))
    }

    @Test
    fun itShouldSolveThePuzzleWhenIAmLoggedInWithMyMetapackAddressPart1(){
        println(day03Puzzle.diagnose(metapackDiagnostics))
    }

    @Test
    fun itShouldSolveThePuzzleWhenIAmLoggedInWithMyMetapackAddressPart2(){
        println(day03Puzzle.diagnose(metapackDiagnostics))
    }

}