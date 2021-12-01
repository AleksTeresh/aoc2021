fun main() {
    fun countIncreases(arr: List<Int>): Int {
        return arr.windowed(2).map{ (a, b) -> b > a }.count{ it }
    }

    fun part1(input: List<String>): Int {
        val all = input.map { it.toInt() }
        return countIncreases(all)
    }

    fun part2(input: List<String>): Int {
        val all = input.map { it.toInt() }
        val windowSums = all.windowed(3).map{ it.sum() }
        return countIncreases(windowSums)
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
