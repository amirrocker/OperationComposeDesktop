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
import de.amirrocker.operationcomposedesktop.coroutine.createUnitService
import de.amirrocker.operationcomposedesktop.timing.startFpsCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

const val mapSquareSize = 40
//const val numUnits = 8
const val numRows = 8
const val numColumns = 8
const val numTiles = numRows * numColumns

const val START_HERE = "Start Here"



class Game {

//    val navController = rememN

    private var previousTimeNanos = Long.MAX_VALUE
    var elapsedTime by mutableStateOf(0L)
    var startTime = 0L

    var size by mutableStateOf(Pair(0.dp, 0.dp))

    /* game states */
    var started by mutableStateOf(false)
    var paused by mutableStateOf(false)
    var finished by mutableStateOf(false)

    /* click handler helper data */
    var clickDestinationSquare = Pair(1, 5)
    var clickedPos:Pair<Pair<Int, Int>, Pair<Int, Int>> = Pair(Pair(8,1), clickDestinationSquare /*Pair(1, 5)*/)

    /* Simple game button */
    var buttonText by mutableStateOf(START_HERE)

    // restrict access
    var squares = mutableStateListOf<List<List<MapSquareData>>>()
        private set

    var pieces = mutableStateListOf<MapPieceData>()
        private set

    var infantryPieces = mutableStateListOf<InfantryPieceData>()
        private set

    val service = createUnitService()

    val scope = CoroutineScope(Job())

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

//        val scope = runBlocking {
////            doHello()
////            doCancellationCorrectly()
//        }

        println("Hello Start")


//        // for debug and for myself I want to draw squares on the map
//        val loop = 0..numRows
//            loop.map { row:Int ->
//                squares.add(listOf( loop.map { column: Int ->
//                    println("row: $row and col: $column")
//                    MapSquareData(this,
//                        xPos = column * mapSquareSize,
//                        yPos = row * mapSquareSize,
//                        Color.DarkGray)
//                })
//            )
//        }
        GameMap.createMap(this, squares)


//        val rowColumnPathfinder = RowColumnPathfinder.useWith(this).findPath(from=clickedPos.first, to=clickedPos.second).getResult()
//        repeat(rowColumnPathfinder.pathCol.count()) { index ->
//            pieces.add(
//                MapPieceData(this, index * 1.5f, "item $index", Color.Green).also { piece ->
//                    // TODO a number of ext. functions are in order :P also stick to one representation value
//                    //  - prefer col/row to abs. values
//                    piece.xPosition.value = rowColumnPathfinder.pathCol[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
//                    piece.yPosition.value = rowColumnPathfinder.pathRow[index].times(mapSquareSize) // Random.nextInt(0, 8).times(mapSquareSize)
//                }
//            )
//        }

        repeat(3) { index ->
            infantryPieces.add(
                InfantryPieceData(this, 1.0f, "someInf", Color.Blue).also { piece ->
                    piece.xPosition.value = 1.times(mapSquareSize)
                    piece.yPosition.value = (index*2 + 1).times(mapSquareSize)
                }
            )
        }

        // useless play around -> remove playWithScope!
//        playWithScope()

//        playWithScope()

//        val scope = runBlocking {
//            structureCalculate()
//        }

    }

//    suspend fun doHello() = coroutineScope {
//        launch {
//            delay(300)
//            println("...Coroutine")
//        }
//
//        val job = launch {
//            repeat(1000) { index ->
//                println("Job sleep in doHello at index : $index")
//                delay(100)
//            }
//        }
//
//        delay(1300L)
//        println("Tired of waiting ... ")
//        job.cancel()
//        job.join()
//        println("main . now quit....")
//
//        println("Hello ...")
//    }

//    suspend fun doCancellationCorrectly() = coroutineScope {
//        val startTime = System.currentTimeMillis()
//        val job = launch(context = Dispatchers.Default) {
//            var nextPrintTime = startTime
//            var i = 0
//            while(isActive) {
//                if(System.currentTimeMillis() >= nextPrintTime) {
//                    println("job : sleeping: ${i++}")
//                    nextPrintTime += 500L
//                }
//            }
//        }
//        delay(1300L)
//        println("tired of wainting -> quitting")
//        job.cancelAndJoin()
//        println("canceled and joined -> quit now")
//    }




    // render loop
    fun update(nanos:Long) {


        pieces.clear()

        val deltaTime = calculateElapsedTimeNanos(nanos = nanos, logToConsole = false)
        println("deltaTime: $deltaTime")


        val job = scope.launch {
            startFpsCalculator().collect {
                println("latest Date time: ${it.format(DateTimeFormatter.ISO_DATE)}")
            }
        }


//        tempPathFind()

        val rowColumnPathfinder = RowColumnPathfinder.useWith(this).findPath(from=clickedPos.first, to=clickDestinationSquare).getResult()

//        squares.forEach {
//            it.update()
//        }

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

//        GameMap.placePath(this, clickedPos, pieces)

//        repeat(3) { index ->
//            infantryPieces.add(
//                InfantryPieceData(this, 1.0f, "someInf", Color.Blue).also { piece ->
//                    piece.xPosition.value = 1.times(mapSquareSize)
//                    piece.yPosition.value = (index *+ 1).times(mapSquareSize)
//                }
//            )
//        }

        infantryPieces.forEach {
            it.update(1L)
        }
    }

    private fun calculateElapsedTimeNanos(nanos:Long, logToConsole:Boolean = false): Long {
        val deltaTime = (nanos - previousTimeNanos).coerceAtLeast(0)
        previousTimeNanos = nanos
        elapsedTime = nanos - startTime
//        if( logToConsole ) println("elapsed time in game loop: $elapsedTime nanosecs")
        return deltaTime
    }
}


//val exitApp: ()->Unit = { println("exit app") }

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
                WorldMap()

                game.squares.forEachIndexed { index, mapSquareDataList ->
//                    println("mapSquareData: $mapSquareDataList")
                    mapSquareDataList[0].forEachIndexed { index, mapSquareData ->
                        MapSquare(index, mapSquareData)
                    }
                }

                game.pieces.forEachIndexed { index: Int, mapPieceData: MapPieceData ->
                    MapPiece(index, mapPieceData)
                }

                game.infantryPieces.forEachIndexed { index: Int, infantryPieceData: InfantryPieceData ->
                    InfantryPiece(index, infantryPieceData)
                }
            }
        }

        LaunchedEffect(Unit) {
            while(true) {
                withFrameNanos {
                    println("in seconds: ${(it/1_000_000_000_000_000.0)}")
                    println("nanoseconds per frame: ${(it/60.0)}")
                    if(game.started && !game.paused && !game.finished) game.update(it)
                }
            }
        }
    }
}

