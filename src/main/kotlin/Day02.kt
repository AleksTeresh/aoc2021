fun main() {
    fun part1(input: List<String>): Int {
        val tokens = input
            .map{ it.split(' ') }
            .map{ (comm, num) -> Pair(comm, num.toInt())}
        val xCommands = tokens.filter { x -> x.first == "forward" }.map { it.second }
        val yPlusComms = tokens.filter { x -> x.first == "down" }.map { it.second }
        val yMinusComms = tokens.filter { x -> x.first == "up" }.map { it.second }
        val xPos = xCommands.sum()
        val yPos = yPlusComms.sum() - yMinusComms.sum()
        return xPos * yPos
    }

    fun part2(input: List<String>): Int {
        val tokens = input
            .map{ it.split(' ') }
            .map{ (comm, num) -> Pair(comm, num.toInt())}
        val xCommands = tokens.filter { x -> x.first == "forward" }.map { it.second }
        val xPos = xCommands.sum()
        //                     yPos, Aim
        val yPos = tokens.fold(Pair(0, 0)){ yPosAndAim, pair ->
            when (pair.first) {
                "down" -> Pair(yPosAndAim.first, yPosAndAim.second + pair.second)
                "up" -> Pair(yPosAndAim.first, yPosAndAim.second - pair.second)
                else -> Pair(yPosAndAim.first + yPosAndAim.second * pair.second, yPosAndAim.second)
            }
        }
        return xPos * yPos.first
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
