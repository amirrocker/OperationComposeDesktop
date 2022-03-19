package de.amirrocker.operationcomposedesktop.math

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

//****************** VECTOR ****************************** //

interface Vector<T : Number> {
    val x: T
    val y: T
    val z: T
    val w: T

    fun magnitude(): T
    fun toArray(): Array<T>
    fun normalize(): Vector<T>

    // simple scalar division and multiplication
    operator fun div(b: T): Vector<T>
    operator fun times(b: T): Vector<T>

    operator fun plus(vb: Vector<T>): Vector<T>
    operator fun minus(vb: Vector<T>): Vector<T>


}


data class Vector3(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val w: Double = 0.0, // in a 3D vector w is always 0
) : Vector<Double> {

    override fun plus(vb: Vector<Double>): Vector<Double> = Vector3(
        x + vb.x,
        y + vb.y,
        z + vb.z,
    )

    override fun minus(vb: Vector<Double>): Vector<Double> = Vector3(
        x - vb.x,
        y - vb.y,
        z - vb.z,
    )

    override fun toArray(): Array<Double> = Array(3) { 0.0 }.apply {
        this[0] = x
        this[1] = y
        this[2] = z
    }

    override fun div(b: Double): Vector<Double> = Vector3(
        x = x / b,
        y = y / b,
        z = z / b,
    )

    override fun times(b: Double): Vector<Double> = Vector3(
        x = x * b,
        y = y * b,
        z = z * b,
    )

    override fun magnitude(): Double = sqrt(x * x + y * y + z * z)

    override fun normalize(): Vector3 = Vector3(
        x * 1.div(magnitude()),
        y * 1.div(magnitude()),
        z * 1.div(magnitude())
    )

    companion object {
        fun ofFloat(x: Float, y: Float, z: Float): Vector3 =
            Vector3(x.toDouble(), y.toDouble(), z.toDouble())

        fun ofDouble(x: Double, y: Double, z: Double): Vector3 = Vector3(x, y, z)
    }
}

data class Vector4(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val w: Double,
) : Vector<Double> {

    override fun toArray(): Array<Double> = Array<Double>(4) { 0.0 }.apply {
        this[0] = x
        this[1] = y
        this[2] = z
        this[3] = w
    }

    override fun magnitude(): Double = sqrt(x * x + y * y + z * z + w * w)

    override fun normalize(): Vector<Double> = Vector4(
        x * (1.div(magnitude())) * magnitude(),
        y * (1.div(magnitude())) * magnitude(),
        z * (1.div(magnitude())) * magnitude(),
        w * (1.div(magnitude())) * magnitude(),
    )


    override fun plus(vb: Vector<Double>): Vector<Double> = Vector4(
        x + vb.x,
        y + vb.y,
        z + vb.z,
        w + vb.w,
    )

    override fun minus(vb: Vector<Double>): Vector<Double> = Vector4(
        x - vb.x,
        y - vb.y,
        z - vb.z,
        w - vb.w,
    )

    override fun div(b: Double): Vector<Double> = Vector4(
        x = x / b,
        y = y / b,
        z = z / b,
        w = w / b,
    )

    override fun times(b: Double): Vector<Double> = Vector4(
        x = x * b,
        y = y * b,
        z = z * b,
        w = w * b,
    )

    companion object {
        fun ofFloat(x: Float, y: Float, z: Float, w: Float): Vector4 =
            Vector4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

        fun ofDouble(x: Double, y: Double, z: Double, w: Double): Vector4 = Vector4(x, y, z, w)
    }


}

// simple factory methods
fun <T : Number> asVector3(x: T, y: T, z: T): Vector3 = Vector3(x.toDouble(), y.toDouble(), z.toDouble())

fun <T : Number> asVector3(x: T, y: T, z: T, block: (T, T, T) -> Vector3): Vector3 = block(x, y, z)


//****************** MATRIX ****************************** //
/**
 * what I want:
 * - create a matrix same as in numpy and/or pandas:
 * - np.full([shape:Union], scalar ) - create a single matrix with shape and single scalar value
 * - np.array([shape:Union])
 * - np.zeros([shape:Union])
 * - np.ones([shape:Union])
 *
 * Also there is already an existing shot at github at this topic.
 * https://github.com/yinpeng/kotlin-matrix
 * and
 * https://github.com/Kotlin/multik <-- preferred
 *
 * But not only do we need matrix we also different vector types :
 * Vector3
 *  - magnitude -> the length of the vector
 *
 *  - normalize
 *
 * Vector4
 *
 *
 */

interface Matrix<T> {

    fun transpose(): Matrix<T>

    fun reshape(shape: Pair<Int, Int>): Matrix<T>

    fun asShapedData(): Array<List<T>>

    fun asRawArray(): Array<T>

    operator fun get(shape: Pair<Int, Int>): Matrix<T>

    operator fun get(x: Int, y: Int): T

    operator fun set(x: Int, y: Int, value: T): Matrix<T>

    companion object {
        fun <T> asMatrix(
            shape: Pair<Int, Int>,
            data: Array<T>,
        ): Matrix<T> {
            return ImmutableMatrix(shape, data)
        }

        inline fun <reified T> zeros(shape: Pair<Int, Int>): Matrix<T> {
            val array = Array(shape.first * shape.second) { 0.0 as T }
            return ImmutableMatrix(shape, array)
        }

        inline fun <reified T> ones(shape: Pair<Int, Int>): Matrix<T> {
            val array = Array(shape.first * shape.second) { 1.0 as T }
            return ImmutableMatrix(shape, array)
        }

        inline fun <reified T> random(shape: Pair<Int, Int>): Matrix<T> {
            val array = Array(shape.first * shape.second) { Random.nextDouble(0.0, 1.0) as T }
            return ImmutableMatrix(shape, array)
        }
    }
}

/**
 * shape defines a Pair representing
 * shape.first = Rows
 * shape.second = Cols
 */
abstract class AbstractMatrix<T>(
    private val shape: Pair<Int, Int>,
    private val data: Array<T>,
) : Matrix<T> {

    protected var internalDataStructure: Array<List<T>> = emptyArray()

    init {
        internalDataStructure = shapeIt()
    }

    private fun shapeIt(): Array<List<T>> {

        require(data.size % shape.first == 0) { "data size : ${data.size} must be divisable by shape row size : ${shape.first}" }
        require(shape.first * shape.second == data.size) { "first * second == data.size is not true: first: ${shape.first} * ${shape.second} == ${data.size}" }
        require(data.size % shape.second == 0) { "data size : ${data.size} must be divisable by shape column size : ${shape.second} and result in ${shape.first} but was ${data.size % shape.second}" }

        val slices: Array<List<T>> = Array(shape.first) { List<T>(shape.second) { 0.0 as T } }
        (0 until shape.first).forEach {
            val steps = data.size / shape.first
            val from = steps * it
            val until = (from + steps) - 1
            slices[it] = data.slice((from..until))
        }
        println("Matrix rows: ${slices.size}")
        println("Matrix columns: ${slices[0].size}")

        return slices
    }

    override fun get(rows: Int, cols: Int): T {
        return internalDataStructure[rows][cols]
    }

    // TODO this needs some more thought!
    override fun get(shape: Pair<Int, Int>): AbstractMatrix<T> = this

    override fun asShapedData(): Array<List<T>> = this.internalDataStructure

    override fun asRawArray(): Array<T> = data

    override fun transpose(): Matrix<T> {
        // reverse pair
        val rp = Pair(shape.second, shape.first)
        val copy = Matrix.asMatrix(rp, this.data)
        return copy
    }

    override fun reshape(shape: Pair<Int, Int>): Matrix<T> {
        val copy = Matrix.asMatrix(shape, this.data)
        return copy
    }

    abstract override fun set(x: Int, y: Int, value: T): Matrix<T>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractMatrix<*>

        if (shape != other.shape) return false
        if (!data.contentEquals(other.data)) return false
        if (!internalDataStructure.contentEquals(other.internalDataStructure)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + internalDataStructure.contentHashCode()
        return result
    }
}

class ImmutableMatrix<T>(
    private val shape: Pair<Int, Int>,
    private val data: Array<T>,
) : AbstractMatrix<T>(shape, data) {
    override fun set(x: Int, y: Int, value: T): Matrix<T> = error("Please use a Mutable Matrix to set values.")
}

class MutableMatrix<T>(
    private val shape: Pair<Int, Int>,
    data: Array<T>,
) : AbstractMatrix<T>(shape, data) {

    override fun set(row: Int, col: Int, value: T): Matrix<T> {
        (this.internalDataStructure[row] as MutableList)[col] = value
        return Matrix.asMatrix(shape, asRawArray())
    }
}

// ****************** FUNKTION COMPOSITION ****************************** //

val plus3 = { x: Int -> x.times(x) }

val creator =
    { fa: FloatArray, shape: Pair<Int, Int> -> Matrix.asMatrix(Pair(shape.first, shape.second), fa.toTypedArray()) }

fun <T> transform(matrix: Matrix<Float>, fn: (m: Matrix<Float>) -> Matrix<Float>) = fn(matrix)

val command = { vector: FloatArray, ma: Matrix<Float>, fn: (vector: FloatArray, ma: Matrix<Float>) -> Matrix<Float> ->
    fn(
        vector,
        ma
    )
}

/**
 * in this function a regular column vector is multiplied with a homogenous! matrix.
 * shape of the vector is checked in this function.
 *
 * cv = {2.0f, 3.0f, 1.0f, 1.0f}
 *
 * ma = {
 *  1.0f, 0.0f, 0.0f, 0.0f,
 *  0.0f, 1.0f, 0.0f, 0.0f,
 *  0.0f, 0.0f, 1.0f, 0.0f,
 *  0.0f, 0.0f, 0.0f, 1.0f,
 * }
 * ma is a simple identity matrix.
 *
 */
//val multiplyCommand = { columnVector:FloatArray, ma:Matrix<Float> -> Matrix.asMatrix() }

/**
 * Vector math:
 * we need some basic vector functions that work on two vectors.
 * starting with vector skalar multiplication:
 */
val multiplySkalarWithColumnVector4 = { skalar: Float, columnVector: Vector4 ->
    Vector4(
        columnVector.x.times(skalar),
        columnVector.y.times(skalar),
        columnVector.z.times(skalar),
        columnVector.w.times(skalar),
    )
}

val multiplySkalarWithColumnVector3 = { skalar: Float, columnVector: Vector3 ->
    Vector3(
        columnVector.x.times(skalar),
        columnVector.y.times(skalar),
        columnVector.z.times(skalar)
    )
}

val divideColumnVector4BySkalar = { skalar: Float, columnVector: Vector4 ->
    Vector4(
        columnVector.x.times(skalar),
        columnVector.y.times(skalar),
        columnVector.z.times(skalar),
        columnVector.w.times(skalar),
    )
}

val divideColumnVector3BySkalar = { skalar: Float, columnVector: Vector3 ->
    Vector3(
        columnVector.x.div(skalar),
        columnVector.y.div(skalar),
        columnVector.z.div(skalar),
    )
}

/**
 * addition and subtraction of 2 vectors
 */
val addVector = { va: Vector3, vb: Vector3 ->
    Vector3.ofDouble(
        va.x + vb.x,
        va.y + vb.y,
        va.z + vb.z,
    )
}


val subtractVector = { va: Vector3, vb: Vector3 ->
    Vector3.ofDouble(
        va.x - vb.x,
        va.y - vb.y,
        va.z - vb.z,
    )
}

/**
 * dot- aka skalar-product of two vectors
 * returns a float skalar
 *
 * Note:
 * skalar product of two orthogonal, linear independent vectors is zero
 * skalar product of two parallel vectors equals the product of its length or value
 * skalar product of two non-parallel vectors equals the negative product of its length or value
 *
 * Note: calculating the skalar product, the angle between the two vectors should be considered as follows
 * angle of 0° -> 1
 * angle of 90° -> 0
 * angle of 180° -> -1
 *
 */
val dotProduct = { va: Vector3, vb: Vector3 -> va.x * vb.x + va.y * vb.y + va.z * vb.z }

/**
 *
 */
val magnitude = { va: Vector3 -> va.x * va.x + va.y * va.y + va.z * va.z }


private fun log(msg: String) {
    println(msg)
}




