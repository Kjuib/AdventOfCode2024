package day07

import util.Coord
import util.StringUtil.Companion.parseGraph
import util.StringUtil.Companion.parseInt
import java.io.File

val example1 = """
    190: 10 19
    3267: 81 40 27
    83: 17 5
    156: 15 6
    7290: 6 8 6 15
    161011: 16 10 13
    192: 17 8 14
    21037: 9 7 18 13
    292: 11 6 16 20
"""

class PartA {
    private fun doMath(current: Long, numList: List<Int>, i: Int, opp: String, target: Long): Boolean {
        val total = when (opp) {
            "+" -> {
                current + numList[i]
            }
            "*" -> {
                current * numList[i]
            }
            else -> {
                throw Error("Unknown Opp [${opp}]")
            }
        }

        if (i < numList.size - 1) {
            return doMath(total, numList, i + 1, "+", target)
                    || doMath(total, numList, i + 1, "*", target)
        } else {
            return total == target
        }
    }

    private fun handleLine(line: String): Long {
        val parts = line.split(": ")
        val target = parts[0].toLong()
        val numList = parseInt(parts[1], " ")

        if (doMath(numList[0].toLong(), numList, 1, "+", target)
            || doMath(numList[0].toLong(), numList, 1, "*", target)) {
            return target
        }

        return 0
    }

    fun run() {
//        val lines = example1.trimIndent().split('\n')
        val lines = File("./src/${this.javaClass.packageName}/input.txt").readLines()

        val total: Long = lines.fold(0) { acc, line -> acc + handleLine(line) }
        println("total: ${ total }")
    }
}

class PartB {
    private fun doMath(current: Long, numList: List<Int>, i: Int, opp: String, target: Long): Boolean {
        val total = when (opp) {
            "+" -> {
                current + numList[i]
            }
            "*" -> {
                current * numList[i]
            }
            "||" -> {
                "${current}${numList[i]}".toLong()
            }
            else -> {
                throw Error("Unknown Opp [${opp}]")
            }
        }

        if (i < numList.size - 1) {
            return doMath(total, numList, i + 1, "+", target)
                    || doMath(total, numList, i + 1, "*", target)
                    || doMath(total, numList, i + 1, "||", target)
        } else {
            return total == target
        }
    }

    private fun handleLine(line: String): Long {
        val parts = line.split(": ")
        val target = parts[0].toLong()
        val numList = parseInt(parts[1], " ")

        if (doMath(numList[0].toLong(), numList, 1, "+", target)
            || doMath(numList[0].toLong(), numList, 1, "*", target)
            || doMath(numList[0].toLong(), numList, 1, "||", target)) {
            return target
        }

        return 0
    }

    fun run() {
//        val lines = example1.trimIndent().split('\n')
        val lines = File("./src/${this.javaClass.packageName}/input.txt").readLines()

        val total: Long = lines.fold(0) { acc, line -> acc + handleLine(line) }
        println("total: ${ total }")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}