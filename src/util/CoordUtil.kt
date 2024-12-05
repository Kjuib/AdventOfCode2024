package util

data class Coord(val x: Int, val y: Int) {
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
    }

    fun <T> get(graph: List<List<T>>): T? {
        val row = graph.elementAtOrNull(y)
        if (row != null) {
            return row.elementAtOrNull(x)
        }
        return null
    }

    fun add(offset: Coord, multiplier: Int = 1): Coord {
        return Coord(x + (offset.x * multiplier), y + (offset.y * multiplier))
    }
}
