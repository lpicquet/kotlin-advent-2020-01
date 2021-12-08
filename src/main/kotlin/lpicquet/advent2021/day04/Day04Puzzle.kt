package lpicquet.advent2021.day04

import java.util.*

private const val bingoGridRows = 5
private const val bingoGridColumns = 5

class Day04Puzzle {


    fun bingo(inputs: List<String>, winner: Boolean): Int {
        val completedBoards = ArrayList<BingoBoard>()

        println(inputs)

        // extract the first line of the input as the drawn numbers
        val numbersToDraw = ArrayDeque(inputs[0].split(",").map { aNumberString -> aNumberString.toInt() }.toMutableList())

        // turn the inputs into a list of boards.
        // each board is a 2 dimensional array (list of lists of strings)
        val boards = makeBoards(inputs)

        // go through the list of drawn numbers and remove them from each row and each column
        var bingo=false
        while (numbersToDraw.size != 0) {
            val drawn = numbersToDraw.pop()
            println("drawn: $drawn")

            markBoards(boards, drawn)

            // now that we have the boards marked, we can see if one of them has a row completed
            bingo = hasBingo(boards)
            println("$bingo")

            if (bingo){
                val completedBoardsThisDraw = getCompletedBoards(boards)
                println("completedBoards: $completedBoards")
                completedBoards.addAll(completedBoardsThisDraw)
                boards.removeAll(completedBoardsThisDraw)
            }
        }

        val completedBoard = if (winner) completedBoards.first() else completedBoards.last()
        return completedBoard.addRemaining() * completedBoard.ticked.last()

    }

    private fun getCompletedBoards(boards: List<BingoBoard>): List<BingoBoard> {
        return boards.filter { board -> board.isCompleted()}
    }

    private fun hasBingo(boards: List<BingoBoard>) = boards.any { board -> board.isCompleted() }

    private fun markBoards(boards: List<BingoBoard>, drawn: Int?) {
        boards.forEach { board ->
            board.tick(drawn!!)
        }
    }

    /**
     * makes an immutable list of boards.
     * Each board is an immutable list (row) of mutable list (column) of numbers
     *
     * The numbers on the boards are going to be swapped with nulls whenever we 'ink' them as they are being called
     */
    private fun makeBoards(inputs: List<String>): MutableList<BingoBoard> {
        val boardsInputs = inputs.subList(2, inputs.size).filter { line -> line.isNotBlank() }

        val boards = boardsInputs.windowed(size = bingoGridRows, step = bingoGridColumns)
            .map { board: List<String> ->
                val boardGrid = board.map { boardLine ->
                    boardLine.trim().split("""\s+""".toRegex())
                        .map { value -> value.toInt() }.toMutableList<Int?>()
                }.toMutableList()
                BingoBoard(boardGrid)
            }
        return boards.toMutableList()
    }

}
