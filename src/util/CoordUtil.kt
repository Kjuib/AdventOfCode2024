package util

data class Coord(val x: Int, val y: Int, val direction: Coord? = null) {
    companion object {
        fun allDirections(): List<Coord> {
            return listOf(
                Coord(-1, -1),
                Coord(0, -1),
                Coord(1, -1),

                Coord(-1, 0),
                Coord(1, 0),

                Coord(-1, 1),
                Coord(0, 1),
                Coord(1, 1),
            )
        }

        fun <T> loopGraph(graph: List<List<T>>, run: (value: T, coord: Coord) -> Unit) {
            for (y in graph.indices) {
                for (x in graph[y].indices) {
                    run(graph[y][x], Coord(x, y))
                }
            }
        }

        val UP = Coord(0, -1)
        val RIGHT = Coord(1, 0)
        val DOWN = Coord(0, 1)
        val LEFT = Coord(-1, 0)
    }

    fun <T> get(graph: List<List<T>>): T? {
        val row = graph.elementAtOrNull(y)
        if (row != null) {
            return row.elementAtOrNull(x)
        }
        return null
    }

    fun add(offset: Coord, multiplier: Int = 1): Coord {
        return Coord(x + (offset.x * multiplier), y + (offset.y * multiplier), direction)
    }

    fun turnRight(): Coord {
        when (direction) {
            UP -> {
                return Coord(x, y, RIGHT)
            }
            RIGHT -> {
                return Coord(x, y, DOWN)
            }
            DOWN -> {
                return Coord(x, y, LEFT)
            }
            LEFT -> {
                return Coord(x, y, UP)
            }
            else -> {
                throw error("No direction")
            }
        }
    }

    fun turnLeft(): Coord {
        when (direction) {
            UP -> {
                return Coord(x, y, LEFT)
            }
            LEFT -> {
                return Coord(x, y, DOWN)
            }
            DOWN -> {
                return Coord(x, y, RIGHT)
            }
            RIGHT -> {
                return Coord(x, y, UP)
            }
            else -> {
                throw error("No direction")
            }
        }
    }
}
