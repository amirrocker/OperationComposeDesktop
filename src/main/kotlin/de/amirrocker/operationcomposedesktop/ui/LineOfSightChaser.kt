package de.amirrocker.operationcomposedesktop.ui


data class Vector<out T:Number>(val x:T) {}


/**
 * TODO finish this when necessary. So far the idea is based on column-row map.
 */
class LineOfSightChaser {

    var u:Vector<Float> = Vector(0.0f)
    var v:Vector<Float> = Vector(0.0f)


}