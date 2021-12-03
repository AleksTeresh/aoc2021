fun main() {
    fun countOnes(width: Int, input: List<String>): List<Int> {
        val initOneCounts = List(width) { 0 }
        return input.fold(initOneCounts) { oneCounts, str ->
            str.mapIndexed { idx, char ->
                if (char == '1') oneCounts[idx] + 1 else oneCounts[idx]
            }
        }
    }

    fun getByBitCriteria(input: List<String>, byMostCommon: Boolean): String {
        var idx = 0
        var arr = input
        while (arr.size > 1) {
            val width = arr[0].length

            val count = countOnes(width, arr)
            val oneIsMostCommon = count[idx] >= arr.size / 2.0

            val filterVal = if(oneIsMostCommon && byMostCommon || !oneIsMostCommon && !byMostCommon) '1' else '0'

            arr = arr.filter { it[idx] == filterVal }
            idx++
        }
        return arr[0]
    }

    fun part1(input: List<String>): Int {
        val inputSize = input.size
        val width = input[0].length

        val counts = countOnes(width, input)
        val gamma = counts.map { if (it > inputSize / 2) '1' else '0' }.joinToString("")
        val epsilon = gamma.map { if (it == '1') '0' else '1' }.joinToString("")

        val a = Integer.parseInt(gamma, 2);
        val b = Integer.parseInt(epsilon, 2);
        return a * b
    }

    fun part2(input: List<String>): Int {


        val ox = getByBitCriteria(input, true)
        val co2 = getByBitCriteria(input, false)

        return Integer.parseInt(ox, 2) * Integer.parseInt(co2, 2);
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
