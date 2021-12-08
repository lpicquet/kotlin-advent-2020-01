package lpicquet.advent2021.day04

class BingoBoard (val rows: MutableList<MutableList<Int?>>) {

    /**
     * a 'pivot' of the table by column so that we can access things by columns more easily
     */
    val columns:MutableList<MutableList<Int?>> = MutableList(rows.size, { index -> ArrayList(rows.size)})

    /** a copy of the original values, so that we can verify */
    val originalRows:MutableList<MutableList<Int?>> = MutableList(rows.size, { index -> ArrayList(rows.size)})

    val ticked:MutableList<Int> = ArrayList()

    init {
        rows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, value ->
                columns[columnIndex].add(value)
                originalRows[rowIndex].add(value)
            }
        }
    }

    fun isCompleted():Boolean{
        return rows.any { row -> row.filterNotNull().isEmpty() } ||
                columns.any {column -> column.filterNotNull().isEmpty() }
    }

    fun tick(drawn: Int) {
        rows.forEachIndexed { rowIndex, row ->
            row.mapIndexed() { columnIndex, value ->
                if (row[columnIndex] == drawn) {
                    println("[$rowIndex][$columnIndex]: $value")
                    row.set(columnIndex, null)
                }
            }
        }

        columns.forEachIndexed { columnIndex, column ->
            column.mapIndexed() { rowIndex, value ->
                if (column[rowIndex] == drawn) {
                    println("[$columnIndex][$rowIndex]: $value")
                    column.set(rowIndex, null)
                }
            }
        }
        ticked.add(drawn)
    }

    fun addRemaining():Int {
        return rows.map { row ->  row.filterNotNull().sumOf { aNumber -> aNumber }}.sum()
    }


}