import lpicquet.advent2021.day01.part1.Day01Puzzle
import java.io.File

class FileUtil {

    fun readTextFile(classPathResource: String): List<String> {
        val uri = this::class.java.getResource(classPathResource)!!.toURI()
        val file = File(uri)
        return file.readLines()
    }

    fun readIntegersFile(classPathResource: String): List<Int> {
        val textFile = readTextFile(classPathResource)
        return textFile.map(String::toInt)
    }
}