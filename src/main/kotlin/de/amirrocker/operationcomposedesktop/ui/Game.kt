package de.amirrocker.operationcomposedesktop.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val mapSquareSize = 40
//const val numUnits = 8
const val numRows = 8
const val numColumns = 8
const val numTiles = numRows * numColumns

class Game {

    private var previousTimeNanos = Long.MAX_VALUE
    var elapsedTime by mutableStateOf(0L)
    var startTime = 0L

    var size by mutableStateOf(Pair(0.dp, 0.dp))

    var started by mutableStateOf(false)
    var paused by mutableStateOf(false)
    var finished by mutableStateOf(false)

    var buttonText by mutableStateOf("Start Here")

    var squares = mutableStateListOf<List<List<MapSquareData>>>()
        private set
    var pieces = mutableStateListOf<MapPieceData>()
        private set

    var clickDestinationSquare = Pair(1, 5)
    var clickedPos:Pair<Pair<Int, Int>, Pair<Int, Int>> = Pair(Pair(8,1), clickDestinationSquare /*Pair(1, 5)*/)


    fun resume() {
        this.paused = false
        buttonText = "Pause"
    }

    fun pause() {

        this.paused = true
        buttonText = "Resume"
    }

    fun start() {
        started = true

        buttonText = "Pause"
        squares.clear()
        pieces.clear()

        // for debug and for myself I want to draw squares on the map
        val loop = 0..numRows
        /*val result:List<List<MapSquareData>> = */loop.map { row:Int ->
            squares.add(listOf( loop.map { column: Int ->
                println("row: $row and col: $column")
                MapSquareData(this,
                    xPos = column * mapSquareSize,
                    yPos = row * mapSquareSize,
                    Color.DarkGray)
            }))
        }

        val rowColumnPathfinder = RowColumnPathfinder.useWith(this).findPath(from=clickedPos.first, to=clickedPos.second).getResult()

//        repeat(numUnits) { index ->
        repeat(rowColumnPathfinder.pathCol.count()) { index ->
            pieces.add(
                MapPieceData(this, index * 1.5f, "item $index", Color.Green).also { piece ->
                    // TODO a number of ext. functions are in order :P also stick to one representation value
                    //  - prefer col/row to abs. values
                    piece.xPosition.value = rowColumnPathfinder.pathCol[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
                    piece.yPosition.value = rowColumnPathfinder.pathRow[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
                }
            )
        }
    }

    // render loop
    fun update(nanos:Long) {

        pieces.clear()

        val deltaTime = calculateElapsedTimeNanos(nanos = nanos, logToConsole = false)

//        tempPathFind()

        val rowColumnPathfinder = RowColumnPathfinder.useWith(this).findPath(from=clickedPos.first, to=clickDestinationSquare).getResult()

        squares.forEach {
//            it.update()
        }

        repeat(rowColumnPathfinder.pathCol.count()) { index ->
            pieces.add(
                MapPieceData(this, index * 1.5f, "item $index", Color.Green).also { piece ->
                    // TODO a number of ext. functions are in order :P also stick to one representation value
                    //  - prefer col/row to abs. values
                    piece.xPosition.value = rowColumnPathfinder.pathCol[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
                    piece.yPosition.value = rowColumnPathfinder.pathRow[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
                }
            )
        }

    }

    private fun calculateElapsedTimeNanos(nanos:Long, logToConsole:Boolean = false): Long {
        val deltaTime = (nanos - previousTimeNanos).coerceAtLeast(0)
        previousTimeNanos = nanos
        elapsedTime = nanos - startTime
        if( logToConsole ) println("elapsed time in game loop: $elapsedTime nanosecs")
        return deltaTime
    }

    fun tempPathFind() {
        val from = Pair(2,3)
        val to = Pair(7,6)
//        val path:Pair<Pair<Int, Int>, Pair<Int, Int>> = Pathfinder.useWith(this).findPath(from=from, to=to)
        val rowColumnPathfinder = RowColumnPathfinder.useWith(this).findPath(from=from, to=to).getResult()
        println("pathCol: ${rowColumnPathfinder.pathCol}")
        println("pathRow: ${rowColumnPathfinder.pathRow}")
    }

}


@Composable
@Preview
fun OperationXGame() {

    val game = remember { Game() }
    val density = LocalDensity.current

    Column {
        Row {

            Button(onClick = {

                if(!game.started) {
                    println("start game")
                    game.start()
                } else
                    if( game.started && !game.paused ) {
                        game.pause()
                    } else
                        if( game.paused ) {
                            game.resume()
                        }
            }) {
                Text(game.buttonText, fontSize = 40.sp)
            }
        }

        if( game.started ) {
            println("game started")
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .onSizeChanged {
                    with(density) {
                        game.size = it.width.toDp() to it.height.toDp()
                    }
                }
            ) {
//                WorldMap()

                game.squares.forEachIndexed { index, mapSquareDataList ->
                    println("mapSquareData: $mapSquareDataList")
                    mapSquareDataList[0].forEachIndexed { index, mapSquareData ->
                        MapSquare(index, mapSquareData)
                    }
                }

                game.pieces.forEachIndexed { index: Int, mapPieceData: MapPieceData ->
                    MapPiece(index, mapPieceData)
                }
            }
        }

        LaunchedEffect(Unit) {
            while(true) {
                withFrameNanos {
                    if(game.started && !game.paused && !game.finished) game.update(it)
                }
            }
        }
    }
}

