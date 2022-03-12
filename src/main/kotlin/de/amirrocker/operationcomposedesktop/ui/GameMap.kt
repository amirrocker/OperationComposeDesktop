package de.amirrocker.operationcomposedesktop.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

sealed class GameMap {

    companion object {
        fun createMap(game:Game, squares:SnapshotStateList<List<List<MapSquareData>>>) = Map().createMap(game, squares)
        fun placePath(game:Game, clickedPos:Pair<Pair<Int, Int>, Pair<Int, Int>>,  pieces: SnapshotStateList<MapPieceData>) =
            Map().placePath(game, clickedPos,  pieces)
    }

    private data class Map(
        val sizeCol:Int = 8,
        val sizeRows:Int = 8
    ) : GameMap() {

        fun createMap(game:Game, squares:SnapshotStateList<List<List<MapSquareData>>>) {  // for debug and for myself I want to draw squares on the map
            val loop = 0..numRows
            loop.map { row: Int ->
                squares.add(
                    listOf(loop.map { column: Int ->
                        println("row: $row and col: $column")
                        MapSquareData(
                            game=game,
                            xPos = column * mapSquareSize,
                            yPos = row * mapSquareSize,
                            Color.DarkGray
                        )
                    })
                )
            }
        }

        fun placePath(game:Game, clickedPos:Pair<Pair<Int, Int>, Pair<Int, Int>>,  pieces: SnapshotStateList<MapPieceData>) {
            val rowColumnPathfinder = RowColumnPathfinder.useWith(game).findPath(from=clickedPos.first, to=clickedPos.second).getResult()
            repeat(rowColumnPathfinder.pathCol.count()) { index ->
                pieces.add(
                    MapPieceData(game, index * 1.5f, "item $index", Color.Green).also { piece ->
                        // TODO a number of ext. functions are in order :P also stick to one representation value
                        //  - prefer col/row to abs. values
                        piece.xPosition.value = rowColumnPathfinder.pathCol[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
                        piece.yPosition.value = rowColumnPathfinder.pathRow[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
                    }
                )
            }
        }
    }



}
