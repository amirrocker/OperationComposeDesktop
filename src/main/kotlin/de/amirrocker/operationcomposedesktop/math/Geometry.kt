package de.amirrocker.operationcomposedesktop.math

import kotlin.math.cos
import kotlin.math.sin

//****************** GEOMETRY ****************************** //

sealed interface Geometry<T : Number> {

    companion object {
        // find the vector between these two points
        fun vectorBetween(from: Point<Double>, to: Point<Double>) =
            Vector3(
                to.x - from.x,
                to.y - from.y,
                to.z - from.z
            )
    }
}


data class Point<T : Number>(
    val x: T,
    val y: T,
    val z: T,
) : Geometry<T> {

    // simple movement along single axis
    // check for diff. impl. of T
    // when(x) is Float -> do this, else do that....
    fun translateY(dy: T) = Point(x, y.toDouble().plus(dy.toDouble()), z)
    fun translateX(dx: T) = Point(x.toDouble().plus(dx.toDouble()), y, z)
    fun translateZ(dz: T) = Point(x, y, z.toDouble().plus(dz.toDouble()))

    // rotation around single axis
    // cos(x) - sin(x)
    // sin(x) + cos(x)

    // rotate a point on the x-y plane
    fun rotate2D(angle: Float) =
        Matrix.asMatrix(Pair(2, 2),
            arrayOf(cos(x.toDouble()), -sin(y.toDouble()), sin(x.toDouble()), cos(y.toDouble())))

    fun rotate2DAroundPlane(angle: Float, plane: Plane = Plane.X_Y_PLANE) =
        when (plane) {
            Plane.X_Y_PLANE -> Matrix.asMatrix(Pair(2, 2),
                arrayOf(cos(x.toDouble()), -sin(y.toDouble()), sin(x.toDouble()), cos(y.toDouble())))
            Plane.Z_Y_PLANE -> Matrix.asMatrix(Pair(2, 2),
                arrayOf(cos(z.toDouble()), -sin(y.toDouble()), sin(z.toDouble()), cos(y.toDouble())))
            Plane.Z_X_PLANE -> Matrix.asMatrix(Pair(2, 2),
                arrayOf(cos(z.toDouble()), -sin(x.toDouble()), sin(z.toDouble()), cos(x.toDouble())))
        }

    fun rotate3D(angle: Float): Nothing = TODO("Need impl.")

}

enum class Plane {
    X_Y_PLANE,
    Z_Y_PLANE,
    Z_X_PLANE,
}

data class Circle(
    val center: Point<Double>,
    val radius: Float,
) {
    fun scale(scale: Float) = Circle(center, scale.times(radius))
}

data class Sphere(
    val center: Point<Double>,
    val radius: Float,
) : Geometry<Double> {

}

data class Cylinder(
    val center: Point<Double>,
    val radius: Float,
    val height: Float,
) {}

data class Ray(
    val point: Point<Double>,
    val vector: Vector3,
) : Geometry<Double> {

}