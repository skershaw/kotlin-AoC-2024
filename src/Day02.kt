//    The levels are either all increasing or all decreasing.
//    Any two adjacent levels differ by at least one and at most three.

fun isDecreasing(input: List<Int>): Boolean {
    val listIterator = input.listIterator()
    for ((index, value) in listIterator.withIndex()) {
        if (index == input.size - 1)
            return true
        if (value <= input[index + 1] || (value - input[index + 1]) > 3)
            return false
    }
    return true
}

fun isIncreasing(input: List<Int>): Boolean {
    val listIterator = input.listIterator()
    for ((index, value) in listIterator.withIndex()) {
        if (index == input.size - 1)
            return true
        if (value >= input[index + 1] || (input[index + 1] - value) > 3)
            return false
    }
    return true
}

fun isDecreasingWithTolerance(input: List<Int>): Boolean {
    val listIterator = input.listIterator()
    var mutable1: MutableList<Int> = input.toMutableList()
    var mutable2: MutableList<Int> = input.toMutableList()
    for ((index, value) in listIterator.withIndex()) {
        if (index == input.size - 1)
            return true
        if (value <= input[index + 1] || (value - input[index + 1]) > 3) {
            mutable1.removeAt(index)
            mutable2.removeAt(index + 1)
            return isDecreasing(mutable1) || isDecreasing(mutable2)
        }
    }
    return true
}

fun isIncreasingWithTolerance(input: List<Int>): Boolean {
    val listIterator = input.listIterator()
    var mutable1: MutableList<Int> = input.toMutableList()
    var mutable2: MutableList<Int> = input.toMutableList()
    for ((index, value) in listIterator.withIndex()) {
        if (index == input.size - 1)
            return true
        if (value >= input[index + 1] || (input[index + 1] - value) > 3) {
            mutable1.removeAt(index)
            mutable2.removeAt(index + 1)
            return isIncreasing(mutable1) || isIncreasing(mutable2)
        }
    }
    return true
}

fun main() {

    fun part1(input: List<String>): Int {
        val reports = input.map { it.split(" ").map { it.toInt() }}
        val count = reports.map { isDecreasing(it) || isIncreasing(it) }.count{ it }
        return count
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { it.split(" ").map { it.toInt() }}
        val count = reports.map { isDecreasingWithTolerance(it) || isIncreasingWithTolerance(it) }.count{ it }
        return count
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
