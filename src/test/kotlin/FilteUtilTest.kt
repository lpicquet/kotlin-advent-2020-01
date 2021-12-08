import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FilteUtilTest {

    private val fileUtil = FileUtil()


    @Test
    fun itShouldOpenTheFileAsLinesOfText() {
        // when
        val resourceLines: List<String> = fileUtil.readTextFile("/day01/textFile.txt")
        // then
        Assertions.assertThat(resourceLines).isEqualTo(listOf("The quick brown fox jumps over the fence.", "If you want your life to change, change your life."))
    }


    @Test
    fun itShouldOpenTheFileAsLinesOfNumbers(){
        // when
        val resourceLines: List<Number> = fileUtil.readIntegersFile("/day01/integersFile.txt")
        // then
        Assertions.assertThat(resourceLines).isEqualTo(listOf(1, 2, 3, 4, 5))
    }
}