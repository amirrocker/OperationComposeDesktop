package de.amirrocker.operationcomposedesktop.ui

import de.amirrocker.operationcomposedesktop.math.Matrix
import de.amirrocker.operationcomposedesktop.math.Vector3
import de.amirrocker.operationcomposedesktop.math.asVector3
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestMath {

    val data_1x4_valid =  arrayOf( 0.0, 1.0, 2.0, 3.0 )
    val data_1x4_invalid =  arrayOf( 0.0, 1.0, 2.0, 3.0, 4.0, 5.0 )

    val data_float_2x2_valid =  floatArrayOf( 0.0f, 1.0f, 2.0f, 3.0f )
    val data_2x2_valid =  arrayOf( 0.0, 1.0, 2.0, 3.0 )
    val data_2x2_invalid =  arrayOf( 0.0, 1.0, 2.0, 3.0, 4.0, 5.0 )

    val data_3x4_valid =  arrayOf( 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0 )
    val data_3x4_invalid =  arrayOf( 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 11.1 )
    val data_even =  arrayOf( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 )
    val data_odd =  arrayOf( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 )
    val data_empty =  emptyArray<Int>() // arrayOf( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 )

    val shape_1x4 = Pair(1, 4)
    val shape_2x2 = Pair(2, 2)
    val shape_2x4 = Pair(2, 4)
    val shape_3x4 = Pair(3, 4)
    val shape_4x2 = Pair(4, 2)
    val shape_4x3 = Pair(4, 3)
    val shape_4x4 = Pair(4, 4)

    @Test
    internal fun `test matrix_1x4 creation`() {
        println("test matrix creation ....")

        val matrix = Matrix.asMatrix(shape = shape_1x4, data_1x4_valid)

        assertTrue(matrix.asShapedData().size == 1, "Expect a 1D array with 1 item ")
        assertTrue(matrix.asShapedData()[0].size == 4, "Expect a 1D array with 4 column items ")

    }

    @Test
    internal fun `test matrix_2x2 creation`() {
        println("test squared matrix 2x2 creation ....")

        val matrix = Matrix.asMatrix(shape = shape_2x2, data_2x2_valid)

        assertTrue(matrix.asShapedData().size == 2, "Expect a 2D array with 2 items ")
        assertTrue(matrix.asShapedData()[0].size == 2, "Expect a 2D array with 2 column items ")

    }

    @Test
    internal fun `test matrix_2x2 creation invalid inputs`() {
        println("test squared matrix 2x2 creation ....")
        val exception = assertThrows<IllegalArgumentException> {
            val matrix = Matrix.asMatrix(shape = shape_2x2, data_2x2_invalid)
        }
    }

    @Test
    internal fun `test matrix creator property`() {
        val matrix = Matrix.zeros<Double>(Pair(2, 2))

        assertEquals(2, matrix.asShapedData().size, "Expect a matrix with 2 rows but was ${matrix.asShapedData().size} ")
        assertEquals(2, matrix.asShapedData()[0].size, "Expect a matrix with 2 columns but was ${matrix.asShapedData()[0].size} ")
        assertEquals(0.0, matrix.asShapedData()[0][0], "Expect a matrix with 2 columns but was ${matrix.asShapedData()[0][0]} ")
        assertEquals(0.0, matrix.asShapedData()[0][1], "Expect a matrix with 2 columns but was ${matrix.asShapedData()[0][1]} ")
        assertEquals(0.0, matrix.asShapedData()[1][0], "Expect a matrix with 2 columns but was ${matrix.asShapedData()[1][0]} ")
        assertEquals(0.0, matrix.asShapedData()[1][1], "Expect a matrix with 2 columns but was ${matrix.asShapedData()[1][1]} ")
    }


    @Test
    internal fun `test transform method for matrix`() {

        val matrix = Matrix.asMatrix(Pair(4, 4), arrayOf(
            1.0, 0.5, 0.0, 0.1,
            0.1, 0.6, 1.1, 1.0,
            1.1, 1.6, 0.1, 1.1,
            0.4, 0.3, 0.2, 0.1,
        ))

        // what we expect:
        /*
            1.0 0.1 1.1 0.4
            0.5 0.6 1.6 0.3
            0.0 1.1 0.1 0.2
            0.1 1.0 1.1 0.1

        */

        val transposed = matrix.transpose()

        assertEquals(1.0, transposed.get(0, 0), "expect 0.1 but was ${transposed.get(0,0)}")
        assertEquals(0.1, transposed.get(1, 0), "expect 0.1 but was ${transposed.get(1,0)}")
        assertEquals(1.1, transposed.get(2, 0), "expect 0.1 but was ${transposed.get(2,0)}")
        assertEquals(0.4, transposed.get(3, 0), "expect 0.1 but was ${transposed.get(3,0)}")

        assertEquals(0.5, transposed.get(0, 1), "expect 0.1 but was ${transposed.get(0,1)}")
        assertEquals(0.6, transposed.get(1, 1), "expect 0.1 but was ${transposed.get(1,1)}")
        assertEquals(1.6, transposed.get(2, 1), "expect 0.1 but was ${transposed.get(2,1)}")
        assertEquals(0.3, transposed.get(3, 1), "expect 0.1 but was ${transposed.get(3,1)}")

    }

    @Test
    internal fun `test the ones factory method`() {
        val numberColumns = 3
        val matrix = Matrix.ones<Double>(Pair(1, numberColumns)) // a 1x3 Matrix
//        assertEquals(1.0, matrix.get(0, 2), "expect a value of 1.0 but was ${matrix.get(0, 2)}")
//        assertEquals(1.0, matrix.get(0, 1), "expect a value of 1.0 but was ${matrix.get(0, 1)}")
//        assertEquals(1.0, matrix.get(0, 0), "expect a value of 1.0 but was ${matrix.get(0, 0)}")
        matrix.asRawArray().forEach {
            assertEquals(1.0, matrix.get(0, 0), "expect a value of 1.0 but was ${matrix.get(0, 0)}")
        }
    }

    // TODO Vector Test - separate these tests
    @Test
    internal fun `given a vector when normalized then expext a unit vector with correct direction`() {
        val vector:Vector3 = asVector3(4.0, 6.0, 3.0)
//      optional factory method
//        val vector2 = asVector3(4.0, 6.0, 3.0) { x, y, z ->
//            Vector3(x, y, z)
//        }
        // sqrt(4*4 + 6*6 + 3*3) = 7,810249675906654
        val result:Vector3 = vector.normalize()
        assertEquals(0.5121475197315839, result.x, "Expect 0.5121475197315839 but was $result")
        assertEquals(0.5121475197315839, result.y, "Expect 0.5121475197315839 but was $result")
        assertEquals(0.5121475197315839, result.z, "Expect 7,810249675906654 but was $result")
    }

    @Test
    internal fun `given a vector when magnitude then expect a floating point result`() {
        val vector:Vector3 = asVector3(4.0, 6.0, 3.0)
//      optional factory method
//        val vector2 = asVector3(4.0, 6.0, 3.0) { x, y, z ->
//            Vector3(x, y, z)
//        }

        // sqrt(4*4 + 6*6 + 3*3) = 7,810249675906654
        val result = vector.magnitude()

        assertEquals(7.810249675906654, result, "Expect 7,810249675906654 but was $result")
    }




    // Vector with functions tests - not yet started.
    @Test
    internal fun `test calculator method`() {
//        println(calculator)
    }



}