package lpicquet.advent2021.day03.part1

class Day03Puzzle {

    fun diagnose(diagnostics: List<String>): Unit {
        println("power consumption: ${consumption(diagnostics)}")
    }

    fun mapDiagnostics(diagnostics: List<String>): List<String> {

        // initialise the mapped diagnostics
        val length = diagnostics[0].length
        val mappedDiagnostics = mutableListOf<StringBuilder>()
        for (i in 0..length -1){
            mappedDiagnostics.add(java.lang.StringBuilder(diagnostics.size))
        }

        // append each digit to the corresponding position
        diagnostics.map { diagnostic ->
            println("diagnostic: ${diagnostic}")
            diagnostic.map { it.toString() }.forEachIndexed { index, digit ->
                println("index: $index, digit: $digit")
                mappedDiagnostics[index].append(digit);
            }
        }

        // make strings of what we have
        return mappedDiagnostics.map { mappedDiagnostic -> mappedDiagnostic.toString() }

    }

    fun fromBinary(binaryString: String): Int {
        return Integer.parseInt(binaryString, 2) // read the digits which are in base 2 as an integer
    }

    fun toBinaryString(digits: List<Char>): String {
        val stringBuilder = StringBuilder()
        digits.forEach { stringBuilder.append(it) }
        val binaryString = stringBuilder.toString()
        return binaryString
    }

    fun gammaDigit(mappedDiagnostic: String): Char {
        val groupedByChararacter = mappedDiagnostic.groupBy { aChar -> aChar }
        if (groupedByChararacter['0']!!.size > groupedByChararacter['1']!!.size ){
            return '0'
        } else {
            return '1'
        }
    }

    fun gammaDigits(diagnostics: List<String>) : List<Char> {
        val mappedDiagnostics = mapDiagnostics(diagnostics)
        println("mappedDiagnostics: ${mappedDiagnostics}")
        return mappedDiagnostics.map { mappedDiagnostic -> gammaDigit(mappedDiagnostic) }
    }

    fun epsilonDigits(gammaDigits: String): List<Char> {
        val invertedDigits = gammaDigits.map { digit -> if (digit == '0') '1' else '0' }
        return invertedDigits
    }

    fun gammaRate(diagnostics: List<String>): Int {
        val gammaDigits = toBinaryString(gammaDigits(diagnostics))
        val gammaRate = fromBinary(gammaDigits)
        return gammaRate
    }

    fun epsilonRate(gammaDigits: String): Int {
        val epsilonDigits = toBinaryString(epsilonDigits(gammaDigits))
        val epsilonRate = fromBinary(epsilonDigits)
        return epsilonRate
    }

    fun consumption(diagnostics: List<String>): Int {
        val gammaDigits = toBinaryString(gammaDigits(diagnostics))
        val gammaRate = fromBinary(gammaDigits)
        val epsilonRate = epsilonRate(gammaDigits)
        println("Gamma rate: ${gammaRate}")
        println("Epsilon rate: ${epsilonRate}")
        return gammaRate * epsilonRate
    }



}