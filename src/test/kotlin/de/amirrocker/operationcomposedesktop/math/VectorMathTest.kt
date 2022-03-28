package de.amirrocker.operationcomposedesktop.math

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class VectorMathTest {

    // TODO Vector Test - separate these tests
    @Test
    internal fun `given a vector when normalized then expext a unit vector with correct direction`() {
        val vector: Vector3 = asVector3(4.0, 6.0, 3.0)
//      optional factory method
//        val vector2 = asVector3(4.0, 6.0, 3.0) { x, y, z ->
//            Vector3(x, y, z)
//        }
        // sqrt(4*4 + 6*6 + 3*3) = 7,810249675906654
        val result: Vector3 = vector.normalize()
        assertEquals(0.5121475197315839, result.x, "Expect 0.5121475197315839 but was ${result.x}")
        assertEquals(0.7682212795973759, result.y, "Expect 0.7682212795973759 but was ${result.y}")
        assertEquals(0.3841106397986879, result.z, "Expect 0.3841106397986879 but was ${result.z}")
    }

    @Test
    internal fun `given a vector when magnitude then expect a floating point result`() {
        // given
        val vector: Vector3 = asVector3(4.0, 6.0, 3.0)
//      optional factory method
//        val vector2 = asVector3(4.0, 6.0, 3.0) { x, y, z ->
//            Vector3(x, y, z)
//        }

        // sqrt(4*4 + 6*6 + 3*3) = 7,810249675906654
        // when
        val result = vector.magnitude()

        // then
        assertEquals(7.810249675906654, result, "Expect 7,810249675906654 but was $result")
    }

    // Vector with functions tests - not yet started.
    @Test
    internal fun `test calculator method`() {
//        println(calculator)
    }

}