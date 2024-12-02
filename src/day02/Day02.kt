package day02

import java.io.File
import kotlin.math.abs

val example = """
    7 6 4 2 1
    1 2 7 8 9
    9 7 6 2 1
    1 3 2 4 5
    8 6 4 4 1
    1 3 6 7 9
"""

class PartA {

    private fun handleLine(line: String): Int {
        val digits = line.split(" ").map { Integer.valueOf(it) }

        val increasing = digits[0] < digits[1]

        for (i in 1..< digits.size) {
            val diff = digits[i] - digits[i - 1]

            if (increasing && (diff < 1 || diff > 3)) {
                return 0
            } else if (!increasing && (diff < -3 || diff > -1)) {
                return 0
            }
        }

        return 1
    }

    fun run() {
//        val lines = example.trimIndent().split('\n')
        val lines =  File("./src/${ this.javaClass.packageName }/input.txt").readLines()

        val scores = lines.map { handleLine(it) }
        val total = scores.sum()

        println("total: ${ total }")
    }
}

class PartB {

    private fun checkValues(digits: List<Int>): Int {
        val increasing = digits[0] < digits[1]

        for (i in 1..< digits.size) {
            val diff = digits[i] - digits[i - 1]

            if (increasing && (diff < 1 || diff > 3)) {
                return 0
            } else if (!increasing && (diff < -3 || diff > -1)) {
                return 0
            }
        }

        return 1
    }

    private fun handleLine(line: String): Int {
        val digits = line.split(" ").map { Integer.valueOf(it) }

        val newList = mutableListOf<List<Int>>()
        for (i in 0 .. digits.size) {
            newList.add(digits.filterIndexed { index, _ -> index != i })
        }

        val score = newList.fold(0) { acc, newDigits ->
            acc + checkValues(newDigits)
        }

        return if (score > 0) 1 else 0
    }

    fun run() {
//        val lines = example.trimIndent().split('\n')
        val lines =  File("./src/${ this.javaClass.packageName }/input.txt").readLines()

        val scores = lines.map { handleLine(it) }
        val total = scores.sum()

        println("total: ${ total }")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}