package de.amirrocker.operationcomposedesktop.ui

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

sealed class GameMap {

    companion object {
        fun createMap(game:Game, squares:SnapshotStateList<List<List<MapSquareData>>>) = Map().createMap(game, squares)
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

    }
}
