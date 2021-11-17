package de.amirrocker.operationcomposedesktop.ui

import kotlin.math.abs

class Pathfinder(
    private val game:Game
) {

    private val pathCol = emptyList<Int>().toMutableList()
    private val pathRow = emptyList<Int>().toMutableList()

    fun getResult() = MapCoordinates(pathCol, pathRow)

    companion object {
        fun useWith(game:Game):Pathfinder = Pathfinder(game)
    }

//    data class MapCoordinates(val pair:Pair<Int, Int>)
    data class MapCoordinates(val pathCol:List<Int>, val pathRow:List<Int>)

    // todo use Point / Vector objects - we should have some somewhere
    fun findPath(from:Pair<Int, Int>, to:Pair<Int, Int>):Pathfinder {

        var nextCol = from.first
        var nextRow = from.second

        val endCol = to.first
        val endRow = to.second

        var deltaCol = to.first - from.first
        var deltaRow = to.second - from.second

        // path init
//        var stepCol:Int = 1
//        var stepRow:Int = 1
        var currentStep = 0
        var fraction: Float = 0.0f

        println("deltaCol: $deltaCol")
        println("deltaRow: $deltaRow")

        val stepCol = if(deltaCol < 0) -1 else 1
        val stepRow = if(deltaRow < 0) -1 else 1

        deltaCol = abs(deltaCol*2)
        deltaRow = abs(deltaRow*2)

        currentStep++

        pathRow.add(nextRow)
        pathCol.add(nextCol)


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

                pathRow.add(currentStep, nextRow)
                pathCol.add(currentStep, nextCol)
                currentStep++
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

                pathRow.add(currentStep, nextRow)
                pathCol.add(currentStep, nextCol)
                currentStep++
            }

        }



//        (0..numTiles).forEach { index ->
//
//        }


        return this
    }


}