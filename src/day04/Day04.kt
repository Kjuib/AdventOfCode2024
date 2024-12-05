package day04

import util.Coord
import util.StringUtil.Companion.parseGraph
import java.io.File

val example1 = """
    MMMSXXMASM
    MSAMXMSMSA
    AMXSXMAAMM
    MSAMASMSMX
    XMASAMXAMM
    XXAMMXXAMA
    SMSMSASXSS
    SAXAMASAAA
    MAMMMXMMMM
    MXMXAXMASX
"""

class PartA {
    private fun checkDirection(graph: List<List<String>>, coord: Coord, offset: Coord): Boolean {
        return (coord.get(graph) == "X"
                && coord.add(offset, 1).get(graph) == "M"
                && coord.add(offset, 2).get(graph) == "A"
                && coord.add(offset, 3).get(graph) == "S")
    }

    fun run() {
//        val lines = example1.trimIndent().split('\n')
        val lines = File("./src/${this.javaClass.packageName}/input.txt").readLines()

        val graph = parseGraph(lines)
        var count = 0

        Coord.loopGraph(graph) { _, coord ->
            Coord.allDirections().forEach { direction ->
                if (checkDirection(graph, coord, direction)) {
                    count++
                }
            }
        }

        println("count: ${count}")
    }
}

class PartB {
    private fun checkXMAS(graph: List<List<String>>, coord: Coord): Boolean {
        if (coord.get(graph) == "A") {
            val diag1 = ((coord.add(Coord(-1, -1)).get(graph) == "M" && coord.add(Coord(1, 1)).get(graph) == "S")
                    || (coord.add(Coord(-1, -1)).get(graph) == "S" && coord.add(Coord(1, 1)).get(graph) == "M"))
            val diag2 = ((coord.add(Coord(1, -1)).get(graph) == "M" && coord.add(Coord(-1, 1)).get(graph) == "S")
                    || (coord.add(Coord(1, -1)).get(graph) == "S" && coord.add(Coord(-1, 1)).get(graph) == "M"))

            if (diag1 && diag2) {
                return true
            }
        }

        return false
    }

    fun run() {
//        val lines = example1.trimIndent().split('\n')
        val lines = File("./src/${this.javaClass.packageName}/input.txt").readLines()

        val graph = parseGraph(lines)
        var count = 0

        Coord.loopGraph(graph) { _, coord ->
            if (checkXMAS(graph, coord)) {
                count++
            }
        }

        println("count: ${count}")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}