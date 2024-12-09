package day06

import util.Coord
import util.StringUtil.Companion.parseGraph
import util.StringUtil.Companion.parseInt
import java.io.File

val example1 = """
    ....#.....
    .........#
    ..........
    ..#.......
    .......#..
    ..........
    .#..^.....
    ........#.
    #.........
    ......#...
"""

class PartA {

    fun run() {
//        val lines = example1.trimIndent().split('\n')
        val lines = File("./src/${this.javaClass.packageName}/input.txt").readLines()

        val graph = parseGraph(lines)
        val history = mutableSetOf<String>()
        var done = false
        var current = Coord(0, 0)

        Coord.loopGraph(graph) { value, coord ->
            if (value == "^") {
                current = Coord(coord.x, coord.y, Coord.UP)
            }
        }

        while (!done) {
            history.add("${current.x},${current.y}")

            val next = current.add(current.direction!!)
            val nextValue = next.get(graph)
            if (nextValue == "." || nextValue == "^") {
                current = next
            } else if (nextValue == "#") {
                current = current.turnRight()
            } else {
                done = true
            }
        }

        println("unique positions: ${history.size}")
    }
}

class PartB {

    fun run() {
        val lines = example1.trimIndent().split('\n')
//        val lines = File("./src/${this.javaClass.packageName}/input.txt").readLines()

        val graph = parseGraph(lines)
        var start = Coord(0, 0)

        Coord.loopGraph(graph) { value, coord ->
            if (value == "^") {
                start = Coord(coord.x, coord.y, Coord.UP)
            }
        }

        var loopMakers = 0

        val todoList = mutableSetOf(start)
        val doneList = mutableSetOf<Coord>()

        while (todoList.size > 0) {
            var done = false
            val history = mutableSetOf<Coord>()
            var current = todoList.first()
            todoList.remove(current)

            while (!done) {
                history.add(current)

                val next = current.add(current.direction!!)
                val nextValue = next.get(graph)
                if (nextValue == "." || nextValue == "^") {
                    if (!doneList.contains(current.turnRight())) {
                        todoList.add(current.turnRight())
                        doneList.add(current.turnRight())
                    }
                    current = next
                } else if (nextValue == "#") {
                    current = current.turnRight()
                } else {
                    current = next
                    done = true
                }

                if (!done && history.contains(current)) {
                    loopMakers++
                    println("FOUND: ${current}")
                    done = true
                }
            }

        }

        println("loopMakers: ${ loopMakers }")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}