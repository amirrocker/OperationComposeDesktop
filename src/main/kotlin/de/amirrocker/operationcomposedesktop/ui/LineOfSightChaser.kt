package de.amirrocker.operationcomposedesktop.ui

import de.amirrocker.operationcomposedesktop.domain.DirectionableSoldier


//data class Vector1D<out T:Number>(val x:T) {}
//data class Vector2D<out T:Number>(val x:T,val y:T) {}
//data class VRotate2D(val orientation: Float, val position: Vector2D<Float>)


/**
 * TODO finish this when necessary. So far the idea is based on column-row map but with coordinate based elements inside
 * the mapsquares.
 */
class LineOfSightChaser(
    val predator: DirectionableSoldier
) {

//    var u:Vector1D<Float> = Vector1D(0.0f)
//    var v:Vector1D<Float> = Vector1D(0.0f)

    var left = false
    var right = false

//    val u = VRotate2D(
//        -predator.orientation,
//        (prey.position - predator.position )
//    )

}




