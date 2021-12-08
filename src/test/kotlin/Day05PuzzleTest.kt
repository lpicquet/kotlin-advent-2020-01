import lpicquet.advent2021.day05.Day05Puzzle
import lpicquet.advent2021.day05.Line
import lpicquet.advent2021.day05.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day05PuzzleTest {

    val day05Puzzle = Day05Puzzle()
    val includeDiagonals = true

    companion object {
        @JvmStatic
        fun puzzleInputPart1(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day05/sampleInput.txt"), 5),
                Arguments.of(fileUtil.readTextFile("/day05/input.txt"), 5124),
                Arguments.of(fileUtil.readTextFile("/day05/input_metapack.txt"), 6189)
            )
        }

        @JvmStatic
        fun puzzleInputPart2(): Stream<Arguments> {
            val fileUtil = FileUtil()
            return Stream.of(
                Arguments.of(fileUtil.readTextFile("/day05/sampleInput.txt"), 12),
                Arguments.of(fileUtil.readTextFile("/day05/input.txt"), 19771),
                Arguments.of(fileUtil.readTextFile("/day05/input_metapack.txt"), 19164)
            )
        }
    }

    @Test
    fun itShouldParseTheInputAsOriginAndDestinationPoint(){
        assertThat(day05Puzzle.parseLines(listOf("0,0 -> 1,1"), includeDiagonals)).containsExactly(Line(Point(0,0), Point(1,1)))
        assertThat(day05Puzzle.parseLines(listOf("0,0 -> 1,1"), !includeDiagonals)).isEmpty()
        assertThat(day05Puzzle.parseLines(listOf("0,0 -> 1,1","2,0 -> 1,7"), includeDiagonals)).containsExactly(
            Line(Point(0,0), Point(1,1)), Line(Point(2,0), Point(1,7)))
    }

    @Test
    fun itShouldDetermineIfALineIsADiagonal(){
        assertThat(day05Puzzle.parseLine("0,0 -> 1,1").diagonal).isTrue()
        assertThat(day05Puzzle.parseLine("2,0 -> 1,7").diagonal).isTrue()
        assertThat(day05Puzzle.parseLine("2,0 -> 2,7").diagonal).isFalse()
    }

    @Test
    fun itFindsTheSlopeOfTheLine(){
        assertThat(day05Puzzle.parseLine("1,0 -> 1,1").slope).`as`("1,0 -> 1,1").isNull() // vertical
        assertThat(day05Puzzle.parseLine("0,1 -> 1,1").slope).`as`("0,1 -> 1,1").isEqualTo(0) // horizontal
        assertThat(day05Puzzle.parseLine("0,0 -> 1,1").slope).isEqualTo(1)
        assertThat(day05Puzzle.parseLine("5,5 -> 4,4").slope).`as`("5,5 -> 4,4").isEqualTo(1)
        assertThat(day05Puzzle.parseLine("5,5 -> 6,6").slope).`as`("5,5 -> 6,6").isEqualTo(1)
        assertThat(day05Puzzle.parseLine("5,5 -> 4,6").slope).`as`("5,5 -> 4,6").isEqualTo(-1)
        assertThat(day05Puzzle.parseLine("5,5 -> 6,4").slope).`as`("5,5 -> 6,4").isEqualTo(-1)
    }

    @Test
    fun itFindsTheYInterceptOfTheLine() {
        assertThat(day05Puzzle.parseLine("1,0 -> 1,1").yIntercept).`as`("1,0 -> 1,1").isNull() // vertical
        assertThat(day05Puzzle.parseLine("0,1 -> 1,1").yIntercept).`as`("0,1 -> 1,1").isEqualTo(1) // horizontal
        assertThat(day05Puzzle.parseLine("0,0 -> 1,1").yIntercept).`as`("0,0 -> 1,1").isEqualTo(0)
        assertThat(day05Puzzle.parseLine("5,5 -> 4,4").yIntercept).`as`("5,5 -> 4,4").isEqualTo(0)
        assertThat(day05Puzzle.parseLine("5,5 -> 6,6").yIntercept).`as`("5,5 -> 6,6").isEqualTo(0)
        assertThat(day05Puzzle.parseLine("5,5 -> 4,6").yIntercept).`as`("5,5 -> 4,6").isEqualTo(10)
        assertThat(day05Puzzle.parseLine("5,5 -> 6,4").yIntercept).`as`("5,5 -> 6,4").isEqualTo(10)
    }


    @Test
    fun itShouldExtractAllThePointsOnTheLine(){
        assertThat(Line(Point(1,1), Point(1, 3)).points).containsExactly(Point(1,1), Point(1,2), Point(1,3))
        assertThat(Line(Point(9,7), Point(7, 7)).points).containsExactlyInAnyOrder(Point(9,7), Point(8,7), Point(7,7))
    }

    @Test
    fun itShouldCountTheLinesOverlappingAtOnePoint(){
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("2,2 -> 2,1"))).get(Point(2,1))).isEqualTo(1)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("2,2 -> 2,1"))).get(Point(2,2))).isEqualTo(1)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("0,9 -> 5,9"), day05Puzzle.parseLine("0,9 -> 2,9"))).get(Point(0,9))).isEqualTo(2)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("0,9 -> 5,9"), day05Puzzle.parseLine("0,9 -> 2,9"))).get(Point(1,9))).isEqualTo(2)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("0,9 -> 5,9"), day05Puzzle.parseLine("0,9 -> 2,9"))).get(Point(2,9))).isEqualTo(2)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("0,9 -> 5,9"), day05Puzzle.parseLine("0,9 -> 2,9"))).get(Point(3,9))).isEqualTo(1)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("0,9 -> 5,9"), day05Puzzle.parseLine("0,9 -> 2,9"))).get(Point(4,9))).isEqualTo(1)
        assertThat(day05Puzzle.pointOverlaps(listOf(day05Puzzle.parseLine("0,9 -> 5,9"), day05Puzzle.parseLine("0,9 -> 2,9"))).get(Point(5,9))).isEqualTo(1)
    }

    /**
     * solve the puzzle
     */
    @ParameterizedTest
    @MethodSource("puzzleInputPart1")
    fun itShouldSolveThePuzzlePart1(inputs: List<String>, expected: Int){
        assertThat(day05Puzzle.dangerZones(inputs, !includeDiagonals)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("puzzleInputPart2")
    fun itShouldSolveThePuzzlePart2(inputs: List<String>, expected: Int){
        assertThat(day05Puzzle.dangerZones(inputs, includeDiagonals)).isEqualTo(expected)
    }

}