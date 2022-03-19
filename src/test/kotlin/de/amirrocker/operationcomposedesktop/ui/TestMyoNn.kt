package de.amirrocker.operationcomposedesktop.ui

import de.amirrocker.operationcomposedesktop.math.Matrix
import de.amirrocker.operationcomposedesktop.math.MyoNn
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

class TestMyoNn {

    @Test
    internal fun `setup the myonn correctly`() {
        val numberOfInputNodes = 3
        val numberOfHiddenNodes = 4
        val numberOfOutputNodes = 3

        val myonn = MyoNn(
            Matrix.zeros(Pair(1, numberOfInputNodes)),
            Matrix.zeros(Pair(1, numberOfHiddenNodes)),
            Matrix.zeros(Pair(1, numberOfOutputNodes)),
            0.3
        )
        println("myonn: $myonn")
    }
}