package day03

import java.io.File

val example1 = """
    xmul(2,4)
    %&mul[3,7]
    !@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
"""

val example2 = """
    xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
"""

class PartA {
    fun run() {
//        val input = example1.trimIndent().replace("\n".toRegex(), "")
        val input =  File("./src/${ this.javaClass.packageName }/input.txt").readText().replace("\n".toRegex(), "")

        val codes = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex().findAll(input)

        val total = codes.toList().fold(0) { acc, match ->
            val num1 = Integer.valueOf(match.groupValues[1])
            val num2 = Integer.valueOf(match.groupValues[2])

            acc + (num1 * num2)
        }

        println("total: ${ total }")
    }
}

class PartB {
    fun run() {
//        val input = example2.trimIndent().replace("\n".toRegex(), "")
        val input =  File("./src/${ this.javaClass.packageName }/input.txt").readText().replace("\n".toRegex(), "")

        val codes = "((mul\\((\\d{1,3}),(\\d{1,3})\\))|(do\\(\\))|(don't\\(\\)))".toRegex().findAll(input)

        var on = true
        val total = codes.toList().fold(0) { acc, match ->
            var rtn = acc

            if (match.groupValues[0] == "don't()") {
                on = false
            } else if (match.groupValues[0] == "do()") {
                on = true
            } else if (on) {
                val num1 = Integer.valueOf(match.groupValues[3])
                val num2 = Integer.valueOf(match.groupValues[4])

                rtn = acc + (num1 * num2)
            }

            rtn
        }

        println("total: ${ total }")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}