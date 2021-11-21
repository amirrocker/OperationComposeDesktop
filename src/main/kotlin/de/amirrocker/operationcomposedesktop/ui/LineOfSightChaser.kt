package de.amirrocker.operationcomposedesktop.ui


data class Vector<out T:Number>(val x:T) {}

class LineOfSightChaser {

    var u:Vector<Float> = Vector(0.0f)
    var v:Vector<Float> = Vector(0.0f)


}