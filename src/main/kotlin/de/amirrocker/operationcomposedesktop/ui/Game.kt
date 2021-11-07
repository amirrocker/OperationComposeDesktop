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
import kotlin.random.Random

const val mapSquareSize = 40
const val numUnits = 2
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

    var squares = mutableStateListOf<List<List<MapSquareData>>>()
        private set
    var pieces = mutableStateListOf<MapPieceData>()
        private set

    fun start() {
        started = true

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

        repeat(numUnits) { index ->
            pieces.add(
                MapPieceData(this, index * 1.5f, "item $index", Color.Green).also { piece ->
                    // TODO a number of ext. functions are in order :P also stick to one representation value
                    //  - prefer col/row to abs. values
                    piece.xPosition.value = Random.nextInt(0, 8).times(mapSquareSize)
                    piece.yPosition.value = Random.nextInt(0, 8).times(mapSquareSize)
                }
            )
        }
    }

    fun update(nanos:Long) {
        val deltaTime = (nanos - previousTimeNanos).coerceAtLeast(0)
        previousTimeNanos = nanos
        elapsedTime = nanos - startTime
        val formattedTime = elapsedTime.div(1000000000)-113130
        println("elapsed time in game loop: $formattedTime secs")

        squares.forEach {
//            it.update()
        }

        pieces.forEach {
            it.update(10)
        }
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
                println("start button clicked")
                game.start()
            }) {
                Text(if(game.started) "Pause" else "Resume", fontSize = 40.sp)
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

