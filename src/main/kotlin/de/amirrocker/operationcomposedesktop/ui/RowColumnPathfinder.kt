package de.amirrocker.operationcomposedesktop.ui

import kotlin.math.abs

class RowColumnPathfinder(
    private val game:Game
) {

    internal val pathCol = emptyList<Int>().toMutableList()
    internal val pathRow = emptyList<Int>().toMutableList()

    fun getResult() = MapCoordinates(pathCol, pathRow)

    companion object {
        fun useWith(game:Game):RowColumnPathfinder = RowColumnPathfinder(game)
    }

//    data class MapCoordinates(val pair:Pair<Int, Int>)
    data class MapCoordinates(val pathCol:List<Int>, val pathRow:List<Int>)

    // todo use Point / Vector objects - we should have some somewhere
    fun findPath(from:Pair<Int, Int>, to:Pair<Int, Int>):RowColumnPathfinder {

        var nextCol = from.first
        var nextRow = from.second

        val endCol = to.first
        val endRow = to.second

        var deltaCol = to.first - from.first
        var deltaRow = to.second - from.second

        // path init
        var currentStep = 0
        var fraction: Float = 0.0f

        val stepCol = if(deltaCol < 0) -1 else 1
        val stepRow = if(deltaRow < 0) -1 else 1

        deltaCol = abs(deltaCol*2)
        deltaRow = abs(deltaRow*2)

//        currentStep++

        pathRow.add(currentStep, nextRow)
        pathCol.add(currentStep, nextCol)

        println("deltaCol: $deltaCol")
        println("deltaRow: $deltaRow")

        if( deltaCol > deltaRow ) {
            println("deltaCol > deltaRow")

            fraction = deltaRow * 2.0f - deltaCol

            while(nextCol != endCol) {
                println("going down cols ")

                if(fraction >= 0) {
                    nextRow = nextRow + stepRow
                    println("nextRow: $nextRow")
                    fraction -= deltaCol
                }
                nextCol += stepCol
                fraction += deltaRow

                currentStep++
                pathRow.add(currentStep, nextRow)
                pathCol.add(currentStep, nextCol)
            }

        } else {
            println("deltaCol < deltaRow")

            fraction = deltaCol * 2.0f - deltaRow
            println("fraction: $fraction")
            while(nextRow != endRow) {
                println("going down rows ")
                println("nextRow: $nextRow ")
                println("endRow: $endRow ")

                if(fraction >= 0) {
                    nextCol += stepCol
                    println("nextCol: $nextCol")
                    fraction -= deltaRow
                }
                nextRow += stepRow
                fraction += deltaCol

                currentStep++
                pathRow.add(currentStep, nextRow)
                pathCol.add(currentStep, nextCol)
            }

        }
        return this
    }
}