package de.amirrocker.operationcomposedesktop.ui

import androidx.compose.foundation.background
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
//import de.amirrocker.operationcomposedesktop.domain.pattern

@Composable
fun InfantryPiece(index:Int, infantryPieceData: InfantryPieceData) {

    val boxSize = 40.dp

    Box( Modifier
//        .offset(boxSize * index, mapPieceData.position.value.dp)
        .offset(infantryPieceData.xPosition.value.dp, infantryPieceData.yPosition.value.dp )
        .shadow(30.dp)
        .clip(RectangleShape)
    ) {
        Box(
            Modifier
                .size(boxSize, boxSize)
                .background(Color.Blue)
                .clickable {
                    infantryPieceData.click()
                }
        )
    }

}



data class InfantryPieceData(val game:Game, val velocity:Float, val name:String, val color:Color) {

//    var position = mutableStateOf(0f)
    // TODO remember to normalize position values !!
    var xPosition = mutableStateOf(0)
    var yPosition = mutableStateOf(0)

    fun update(dt:Long) {
//        val delta = (dt / 1E8 * velocity).toFloat()
//        xPosition.value = xPosition.value

        // temp position handling with control data a first sketch
//        pattern.forEach {
//            this.xPosition.value += it.moveForward.move().times(mapSquareSize).toInt()
//            this.yPosition.value = it.moveForward.move()
//        }
        
    }

    fun click() {
        println("map piece clicked at xPosition: $xPosition and yPositions: $yPosition")
//        position.value = 40.0f
    }
}
