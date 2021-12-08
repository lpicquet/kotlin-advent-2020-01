package lpicquet.advent2021.day03.part2

class Day03Puzzle {

    fun diagnose(diagnostics: List<String>): Unit {
        println("power consumption: ${powerConsumption(diagnostics)}")
        println("life support rating: ${lifeSupportRating(diagnostics)}")
        println("oxygen generator rating: ${oxygenGeneratorRating(diagnostics)}" )
        println("co2 scrubber rating: ${co2ScrubberRating(diagnostics)}" )
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
            diagnostic.map { it.toString() }.forEachIndexed { index, digit ->
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

    /**
     * count the number of occurrences of each digit.
     * Returns the digit with the most occurrences
     */
    fun gammaDigit(mappedDiagnostic: String): Char {
        val groupedByChararacter = mappedDiagnostic.groupBy { aChar -> aChar }

        var zeroes=0
        var ones=0
        if (groupedByChararacter['0'] != null){
            zeroes=groupedByChararacter['0']!!.size
        }
        if (groupedByChararacter['1'] != null){
            ones=groupedByChararacter['1']!!.size
        }

        if (zeroes > ones ){
            return '0'
        } else {
            return '1'
        }
    }

    fun gammaDigits(diagnostics: List<String>) : List<Char> {
        val mappedDiagnostics = mapDiagnostics(diagnostics)
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

    fun powerConsumption(diagnostics: List<String>): Int {
        val gammaDigits = toBinaryString(gammaDigits(diagnostics))
        val gammaRate = fromBinary(gammaDigits)
        val epsilonRate = epsilonRate(gammaDigits)
        return gammaRate * epsilonRate
    }

    fun oxygenGeneratorRating(diagnostics: List<String>, digitPosition:Int = 0): Int {
        val gammaDigits = toBinaryString(gammaDigits(diagnostics))
        val filteringDigit = gammaDigits.substring(digitPosition..digitPosition)
        val filteredDiagnostics = diagnostics.filter { diagnostic -> diagnostic.substring(digitPosition, digitPosition +1) == filteringDigit }

        // stop if there is only 1 left
        if (filteredDiagnostics.size == 1){
            return fromBinary(filteredDiagnostics[0])
        } else {
            // we recurse
            return oxygenGeneratorRating(filteredDiagnostics, digitPosition + 1)
        }
    }

    fun co2ScrubberRating(diagnostics: List<String>, digitPosition:Int = 0): Int {
        val gammaDigits = toBinaryString(gammaDigits(diagnostics))
        val epsilonDigits = toBinaryString(epsilonDigits(gammaDigits))
        val filteringDigit = epsilonDigits.substring(digitPosition..digitPosition)
        val filteredDiagnostics = diagnostics.filter { diagnostic -> diagnostic.substring(digitPosition, digitPosition +1) == filteringDigit }

        // stop if there is only 1 left
        if (filteredDiagnostics.size <= 1){
            return fromBinary(filteredDiagnostics[0])
        } else {
            // we recurse
            return co2ScrubberRating(filteredDiagnostics, digitPosition + 1)
        }
    }

    fun lifeSupportRating(diagnostics: List<String>): Int {
        return oxygenGeneratorRating(diagnostics) * co2ScrubberRating(diagnostics)
    }

}