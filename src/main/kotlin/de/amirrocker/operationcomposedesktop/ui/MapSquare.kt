package de.amirrocker.operationcomposedesktop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun MapSquare(index:Int, mapSquareData: MapSquareData) {

//    val boxSize = 40.dp

    Box( Modifier
        .offset(mapSquareData.xPos.dp, mapSquareData.yPos.dp )
        .shadow(30.dp)
        .clip(RectangleShape)
    ) {
        Box(
            Modifier
                .size(mapSquareSize.dp, mapSquareSize.dp)
                .border(2.dp, Color.Red)
                .clickable {
                    mapSquareData.click()
                }
        )
    }

}



data class MapSquareData(val game:Game, val xPos:Int, val yPos:Int, val color:Color) {

    var xPosition = mutableStateOf(xPos)
    var yPosition = mutableStateOf(yPos)

//    fun update(dt:Long) {
//        val delta = (dt / 1E8 * velocity).toFloat()
//        position.value = position.value + delta
//    }
//
    fun click() {
        println("map square clicked x:$xPos y:$yPos")
//        position.value = 40.0f
    }
}
