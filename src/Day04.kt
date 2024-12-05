
data class Coordinate(val x: Int, val y: Int)

fun getSurroundingXMASs(coord: Coordinate, colCount: Int, rowCount: Int, board: List<String>): Int {
    val directions = listOf(
        Pair(-1, -1), Pair(0, -1), Pair(1, -1),
        Pair(-1, 0), Pair(1, 0),
        Pair(-1, 1), Pair(0, 1), Pair(1, 1)
    )
    var count = 0

    for (direction in directions) {
        val newX = coord.x + direction.first
        val newY = coord.y + direction.second
        if ((newX in 0 .. colCount) &&
            (newY in 0 .. rowCount) &&
            board[newX][newY] == 'M') {
            val newX2 = newX + direction.first
            val newY2 = newY + direction.second
            if ((newX2 in 0 .. colCount) &&
                (newY2 in 0 .. rowCount) &&
                board[newX2][newY2] == 'A') {
                val newX3 = newX2 + direction.first
                val newY3 = newY2 + direction.second
                if ((newX3 in 0 .. colCount) &&
                    (newY3 in 0 .. rowCount) &&
                    board[newX3][newY3] == 'S') {
                    count += 1
                }

            }
        }
    }
    return count
}

fun hasSurroundingMASs(coord: Coordinate, board: List<String>): Boolean {
    val tlX = coord.x - 1
    val tlY = coord.y - 1
    val brX = coord.x + 1
    val brY = coord.y + 1

    val trX = coord.x + 1
    val trY = coord.y - 1
    val blX = coord.x - 1
    val blY = coord.y + 1

    val d1MAS = (board[tlX][tlY] == 'M' && board[brX][brY] == 'S')
    val d1SAM = (board[tlX][tlY] == 'S' && board[brX][brY] == 'M')
    val d2MAS = (board[trX][trY] == 'M' && board[blX][blY] == 'S')
    val d2SAM = (board[trX][trY] == 'S' && board[blX][blY] == 'M')

    return (d1MAS && (d2MAS || d2SAM)) || (d1SAM && (d2MAS || d2SAM))
}

fun main() {

    fun part1(input: List<String>): Int {
        var xmasCount = 0
        val colCount = input[0].length - 1
        val rowCount = input.size - 1
        for (i in 0..colCount) {
            for (j in 0..rowCount) {
                if (input[j][i] == 'X') {
                    xmasCount += getSurroundingXMASs(Coordinate(j,i), colCount, rowCount, input)
                }
            }
        }
        return xmasCount
    }

    fun part2(input: List<String>): Int {
        var xmasCount = 0
        val colCount = input[0].length - 1
        val rowCount = input.size - 1
        for (i in 1..colCount - 1) {
            for (j in 1..rowCount - 1) {
                if (input[j][i] == 'A') {
                    if (hasSurroundingMASs(Coordinate(j,i), input))
                        xmasCount += 1
                }
            }
        }
        return xmasCount
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
