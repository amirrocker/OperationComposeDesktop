package de.amirrocker.operationcomposedesktop.math

import de.amirrocker.operationcomposedesktop.math.Matrix
import de.amirrocker.operationcomposedesktop.math.MyoNn
import org.junit.jupiter.api.Test

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