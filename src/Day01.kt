
fun <T>List<List<T>>.transpose(): List<List<T>> {
    return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }
}

fun frequencyMap(list: List<Int>): MutableMap<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (value in list) {
        val count = map.getOrDefault(value, 0)
        map[value] = count + 1
    }
    return map
}

fun main() {

    fun part1(input: List<String>): Int {
        val out :Int = input.map { it.split("   ") }
            .transpose()
            .map { it.map { it.toInt() }.sorted() }
            .transpose()
            .map { it -> Math.abs(it[0] - it[1]) }
            .reduce { acc, i -> acc + i }
        return out
    }

    fun part2(input: List<String>): Int {
        val (a,b) = input.map { it.split("   ") }
            .transpose()
            .map { it.map { it.toInt() } }
        val fmap = b.groupingBy { it }.eachCount()

        val out = a.map { it * fmap.getOrDefault(it, 0) }
            .reduce { acc, i -> acc + i }
        return out
    }

    val testInput = readInput("Day01_test")

    val input = readInput("Day01")
//    part1(testInput).println()
//    part1(input).println()
//    part2(testInput).println()
    part2(input).println()
}
