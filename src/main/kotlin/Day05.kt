import kotlin.math.abs
import kotlin.math.max

const val BOARD_WIDTH = 1000
fun main() {
    fun calculateOverlaps(input: List<String>, linePredicate: (List<List<Int>>) -> Boolean): Int {
        val lines = input.map { x -> x.split(" -> ")
            .map { y -> y.split(',')
                .map { it.toInt() } } }
            .filter(linePredicate)

        val board = Array(BOARD_WIDTH) {Array(BOARD_WIDTH) {0} }

        for (line in lines) {
            val startX = line[0][0]
            val startY = line[0][1]
            val xStep = if (line[0][0] == line[1][0]) 0 else if (line[0][0] < line[1][0]) 1 else -1
            val yStep = if (line[0][1] == line[1][1]) 0 else if (line[0][1] < line[1][1]) 1 else -1

            val length = max(abs(line[0][0] - line[1][0]), abs(line[0][1] - line[1][1]))

            for (i in 0..length) {
                board[startX + i*xStep][startY + i*yStep]++
            }
        }

        var counter = 0
        for (x in 0 until BOARD_WIDTH) {
            for (y in 0 until BOARD_WIDTH) {
                if (board[x][y] > 1) {
                    counter++
                }
            }
        }

        return counter
    }
    fun calculateOverlaps(input: List<String>): Int = calculateOverlaps(input) { true }

    fun part1(input: List<String>): Int {
        return calculateOverlaps(input) { o -> o[0][0] == o[1][0] || o[0][1] == o[1][1] }
    }

    fun part2(input: List<String>): Int {
        return calculateOverlaps(input)
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
