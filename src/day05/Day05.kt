package day05

import util.StringUtil.Companion.parseInt
import java.io.File

val example1 = """
    47|53
    97|13
    97|61
    97|47
    75|29
    61|13
    75|53
    29|13
    97|29
    53|29
    61|53
    97|53
    61|29
    47|13
    75|47
    97|75
    47|61
    75|61
    47|29
    75|13
    53|13
    
    75,47,61,53,29
    97,61,53,29,13
    75,29,13
    75,97,47,61,53
    61,13,29
    97,13,75,29,47
"""

class PartA {
    private fun valid(pages: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        rules.forEach { rule ->
            val ind1 = pages.indexOf(rule.first)
            val ind2 = pages.indexOf(rule.second)
            if (ind1 > -1 && ind2 > -1 && ind1 > ind2) {
                return false
            }
        }

        return true
    }

    fun run() {
//        val parts = example1.trimIndent().split("\n\n")
        val parts = File("./src/${this.javaClass.packageName}/input.txt").readText().split("\n\n")

        val rules = parts[0].split('\n').map {
            val values = it.split('|')
            Pair(Integer.valueOf(values[0]), Integer.valueOf(values[1]))
        }
        val pagesList = parts[1].split('\n').map { parseInt(it, ",")}

        val score = pagesList.fold(0) { acc, pages ->
            if (valid(pages, rules)) {
                acc + pages[(pages.size / 2)]
            } else {
                acc
            }
        }

        println("score: ${ score }")
    }
}

class PartB {
    private fun valid(pages: List<Int>, rules: List<Pair<Int, Int>>): Int {
        rules.forEachIndexed { i, rule ->
            val ind1 = pages.indexOf(rule.first)
            val ind2 = pages.indexOf(rule.second)
            if (ind1 > -1 && ind2 > -1 && ind1 > ind2) {
                return i
            }
        }

        return -1
    }

    private fun fix(pages: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {
        var bad = true
        val pages2 = pages.toMutableList()

        while (bad) {
            val badRuleIndex = valid(pages2, rules)
            if (badRuleIndex == -1) {
                bad = false
            } else {
                val badRule = rules[badRuleIndex]
                pages2.remove(badRule.first)
                pages2.add(pages2.indexOf(badRule.second), badRule.first)
            }
        }

        return pages2.toList()
    }

    fun run() {
//        val parts = example1.trimIndent().split("\n\n")
        val parts = File("./src/${this.javaClass.packageName}/input.txt").readText().split("\n\n")

        val rules = parts[0].split('\n').map {
            val values = it.split('|')
            Pair(Integer.valueOf(values[0]), Integer.valueOf(values[1]))
        }
        val pagesList = parts[1].split('\n').map { parseInt(it, ",")}

        val score = pagesList.fold(0) { acc, pages ->
            if (valid(pages, rules) == -1) {
                acc
            } else {
                val fixedPages = fix(pages, rules)
                acc + fixedPages[(fixedPages.size / 2)]
            }
        }

        println("score: ${ score }")
    }
}

fun main() {
//    PartA().run()
    PartB().run()
}