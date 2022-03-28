package de.amirrocker.operationcomposedesktop.option

import de.amirrocker.operationcomposedesktop.option.Option
import de.amirrocker.operationcomposedesktop.option.map
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class TestOption {

    @Test
    internal fun `given an option class when query for result then receive expected value`() {

        val testData = "ein kleiner String"
        // given
        val option = Option.Some(testData)

        // when
        val result = option.data
        // then
        assertEquals( testData, result, "Expect false but was true")
    }

    @Test
    internal fun `given an option class when None then expect None value`() {
        // given
        val option = Option.None
        // then
        assertEquals(Option.None, option, "expect none but was $option")
    }

    @Test
    internal fun `given an option when map from T to R then expect Some of R`() {
        // given
        val option = Option.Some("A Simple String")

        // when
        val mapped:Option<Double> = option.map {
            1.0 + option.data.length.toDouble()
        }

        // then
        assertEquals(Option.Some(16.0), mapped, "Expect a new value")

    }
}