import kotlin.math.min

fun main() {
    fun getBoard(input: List<String>): List<Int> {
        // print(input)
        return input.map { it.trim().split("\\s+".toRegex()).map { it.toInt() } }.flatten()
    }

    fun processBoard(board: List<Int>, draws: List<Int>): List<Int> {
        return board
            .map { draws.indexOf(it) }
            .map { if (it >= 0) it else Int.MAX_VALUE }
    }

    fun getColsOfBoard(board: List<Int>): List<List<Int>> {
        return listOf(
            board.filterIndexed { index, _ -> index % 5 == 0 },
            board.filterIndexed { index, _ -> index % 5 == 1 },
            board.filterIndexed { index, _ -> index % 5 == 2 },
            board.filterIndexed { index, _ -> index % 5 == 3 },
            board.filterIndexed { index, _ -> index % 5 == 4 }
        )
    }

    fun getEarliestWinRound(processedBoard: List<Int>): Int {
        val rows = processedBoard.windowed(5, 5)
        val cols = getColsOfBoard(processedBoard)

        val rowMin = rows.mapNotNull { it.maxOrNull() }.minOrNull()
        val colMin = cols.mapNotNull { it.maxOrNull() }.minOrNull()

        return min(rowMin!!, colMin!!)
    }

    fun getBoardScore(board: List<Int>, draws: List<Int>, earliestRound: Int): Int {
        val drawsSoFar = draws.slice(0..earliestRound)
        println(draws[earliestRound])
        println(drawsSoFar)
        return board.filter { !drawsSoFar.contains(it) }.sum() * draws[earliestRound]
    }

    fun part1(input: List<String>): Int {
        val draws = input[0].split(",").map { it.toInt() }
        var earliest = Int.MAX_VALUE
        var earliestBoardIdx = -1
        val boards = mutableListOf<List<Int>>()

        for (i in 2..input.size step 6) {
            val board = getBoard(input.slice(i..i+4))
            val processedBoard = processBoard(board, draws)
            boards.add(board)
            val earliestRound = getEarliestWinRound(processedBoard)

            if (earliest > earliestRound) {
                earliest = earliestRound
                earliestBoardIdx = (i - 2) / 6
            }
        }

        return getBoardScore(boards[earliestBoardIdx], draws, earliest)
    }

    fun part2(input: List<String>): Int {
        val draws = input[0].split(",").map { it.toInt() }
        var latest = -1
        var latestBoardIdx = -1
        val boards = mutableListOf<List<Int>>()

        for (i in 2..input.size step 6) {
            val board = getBoard(input.slice(i..i+4))
            val processedBoard = processBoard(board, draws)
            boards.add(board)
            val earliestRound = getEarliestWinRound(processedBoard)

            if (latest < earliestRound) {
                latest = earliestRound
                latestBoardIdx = (i - 2) / 6
            }
        }

        return getBoardScore(boards[latestBoardIdx], draws, latest)
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
