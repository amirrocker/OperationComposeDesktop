package de.amirrocker.operationcomposedesktop.ui

import de.amirrocker.operationcomposedesktop.math.SampleNeuralNetwork
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class TestSampleNeuralNetwork {

    lateinit var nn:SampleNeuralNetwork

    @BeforeEach
    internal fun setUp() {
        nn = SampleNeuralNetwork(3, 4, 3, 0.001, 0.5)
    }

    @Test
    internal fun `test init creates a valid network`() {
        println("test called")

        assertTrue(nn.thresholds.size == 10, "expect a valid thresholds array with 10 items")
        assertTrue(nn.thresholds[9] != 0.0, "expect a valid random threshold value between 0 and 1 ")
        assertTrue(nn.learningRate == 0.001, "expect a valid learning rate")

        assertTrue(nn.resultsMatrix.isNotEmpty(), "expect a valid resultsMatrix array")
        assertTrue(nn.resultsMatrix[9] != 0.0, "expect a valid random resultsMatrix value between 0 and 1 ")
        assertTrue(nn.changes.isNotEmpty(), "expect a valid changes array that is not empty")

    }

    @Test
    internal fun `test activation threshold function`() {
        val thresholdValue = nn.calculateActivationThreshold(0.99)
        assertEquals(0.7290879223493065, thresholdValue, "expect a value 0.7290879223493065 but was $thresholdValue")
    }

    //    @Test
//    internal fun `test calculate forward pass output matrix with simple 1x1 vector`() {
//
//        val inputMatrix:Array<Double> = mockMatrix_1x1.invoke(Unit)
//        println("invoke input: $inputMatrix")
//        nn.calculateForwardPassOutput(inputMatrix = inputMatrix)
//        assertEquals(1.1233, nn.outputResults[0], "value expected but was ${nn.outputResults[0]}")
//    }
    @Test
    internal fun `test calculateInputLayer`() {
        // given
        val inputArray = arrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)

        val inputLayer = nn.calculateInputLayer(inputArray)

        assertEquals(inputArray[0], inputLayer[0], "expect the same value input[${inputArray[0]}] == output[1.0] but was ${inputLayer[0]}")
        assertEquals(inputArray[inputArray.size-1], inputLayer[inputLayer.size-1], "expect the same value input[${inputArray[0]}] == output[1.0] but was ${inputLayer[0]}")
    }

    @Test
    internal fun `test calculateHiddenLayer`() {
        val inputArray = listOf(1.0, 2.0, 3.0)

        val inputLayer = nn.calculateHiddenLayer(inputArray)

        assertEquals(inputArray[0], inputLayer[0], "expect the same value input[${inputArray[0]}] == output[1.0] but was ${inputLayer[0]}")
        assertEquals(inputArray[inputArray.size-1], inputLayer[inputLayer.size-1], "expect the same value input[${inputArray[0]}] == output[1.0] but was ${inputLayer[0]}")
    }

    /*
    @Test
    internal fun `test calculate forward pass output matrix with simple 1x5 vector`() {

        val inputMatrix:Array<Double> = makeMockMatrix(arrayOf(0.1, 0.2, 0.3, 0.4, 0.5), mockMatrix_1x1)
        nn.calculateForwardPassOutput(inputMatrix = inputMatrix)
        assertEquals(1.1233, nn.outputResults[0], "value expected but was ${nn.outputResults[0]}")
    }

    @Test
    internal fun `test calculate forward pass output matrix with advanced 3x3 vector`() {

        val inputMatrix:Array<Double> = makeMockMatrix(arrayOf(0.1, 0.2, 0.3, 0.4, 0.5), mockMatrix_1x1)
        nn.calculateForwardPassOutput(inputMatrix = inputMatrix)
        assertEquals(1.1233, nn.outputResults[0], "value expected but was ${nn.outputResults[0]}")
    }
    */

    val mockMatrix_1x1 = { _:Unit -> arrayOf(1.1) }
    val mockMatrix_1x2 = { value:Array<Double> -> arrayOf(1.1, 1.2) }
    val mockMatrix_1x5 = { value:Array<Double> -> arrayOf(1.1, 1.2, 1.3, 1.4, 1.5) }
    val mockMatrix_3x3 = { value:Array<Double> -> arrayOf(
                                                            arrayOf(1.1, 1.2, 1.3),
                                                            arrayOf(1.1, 1.2, 1.3),
                                                            arrayOf(1.1, 1.2, 1.3)
                                                        ) }




}