package day01

import java.io.File
import kotlin.math.abs

val example = """
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3
"""

class PartA {

    private fun handleLine(line: String): Pair<Int, Int> {
        val digits = line.split("   ")
        return Pair(Integer.valueOf(digits[0]), Integer.valueOf(digits[1]))
    }

    fun run() {
//        val lines = example.trimIndent().split('\n')
        val lines =  File("./src/${ this.javaClass.packageName }/input.txt").readLines()

        val pairs = lines.map { handleLine(it) }
        val (list1, list2) = pairs.unzip()

        val list1Sorted = list1.sorted()
        val list2Sorted = list2.sorted()

        val total = list1Sorted.foldIndexed(0) { i, acc, num1 ->
            val num2 = list2Sorted[i]
            val diff = abs(num1 - num2)

            acc + diff
        }

        println("total: ${ total }")
    }
}

class PartB {

    private fun handleLine(line: String): Pair<Int, Int> {
        val digits = line.split("   ")
        return Pair(Integer.valueOf(digits[0]), Integer.valueOf(digits[1]))
    }

    fun run() {
//        val lines = example.trimIndent().split('\n')
        val lines =  File("./src/${ this.javaClass.packageName }/input.txt").readLines()

        val pairs = lines.map { handleLine(it) }
        val (list1, list2) = pairs.unzip()

        val total = list1.fold(0) { acc, num1 ->
            val list2Filtered = list2.filter { num2 -> num2 == num1 }
            val score = num1 * list2Filtered.size

            acc + score
        }

        println("total: ${ total }")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}