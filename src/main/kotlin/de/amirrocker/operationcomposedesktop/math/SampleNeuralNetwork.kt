package de.amirrocker.operationcomposedesktop.math

import kotlin.math.exp

/**
 * this is a java port - not yet complete
 * see end-to-end ml on tablet
 */
class SampleNeuralNetwork(
    val inputCount: Int,
    val hiddenCount: Int,
    val outputCount: Int,

    val learningRate: Double,
    val momentum: Double
) {

    val inputResults = Array<Double>(inputCount) { 0.0 }
    val hiddenResults = Array<Double>(hiddenCount) { 0.0 }
    val outputResults = Array<Double>(outputCount) { 0.0 }

    val weights:Int = (inputCount * hiddenCount) + (hiddenCount * outputCount)
    val resultsMatrix = Array<Double>(weights) { 0.0 }

    private val totalNeurons:Int = inputCount + hiddenCount + outputCount
    val thresholds = Array<Double>(totalNeurons) { 0.0 }
    val errorChanges = Array<Double>(totalNeurons) { 0.0 }
    val lastErrors = Array<Double>(totalNeurons) { 0.0 }
    val allThresholds = Array<Double>(totalNeurons) { 0.0 }

    val changes = Array<Double>(weights) { 0.0 }
    val weightChanges = Array<Double>(weights) { 0.0 }
    val thresholdChanges = Array<Double>(totalNeurons) { 0.0 }

    init {
        reset()
    }


    fun reset() {
        assert(resultsMatrix.isNotEmpty()) { "resultsMatrix may not be empty" }
        // reset all thresholds
        // TODO rewrite using RxJava
        (0 until this.totalNeurons).forEach { index:Int ->
            thresholds[index] = 0.5 - Math.random()
            thresholdChanges[index] = 0.0
            allThresholds[index] = 0.0
        }
        (0 until this.resultsMatrix.size).forEach { index:Int ->
            resultsMatrix[index] = 0.5 - Math.random()
            weightChanges[index] = 0.0
            changes[index] = 0.0
        }
    }

    // Sigmoid activation function
    fun calculateActivationThreshold(sum: Double):Double = 1.0 / (1 + exp(-1.0 * sum))

    fun calculateForwardPassOutput(inputMatrix:Array<Double>) {
        val hiddenIndex = inputCount
        val outIndex = inputCount + hiddenCount

        val inputResultVector = calculateInputLayer(inputMatrix)

        val hiddenResultVector = calculateHiddenLayer(inputResultVector)

//        for(index in (0 until inputCount)) {
//            if( inputMatrix.size==1 ) {
//                inputResults[index] = inputMatrix[0]
//            } else
//                if( inputMatrix.size < inputCount) {
//                    throw IllegalArgumentException("shape of input must be either (1,1) or at least min (1, $inputCount) ")
//                } else {
//                    inputResults[index] = inputMatrix[index]
//                }
//        }

//        var rLoc = 0
//        for(index in (0 until hiddenCount)) {
//            var sum = thresholds[index]
//            for(position in (0 until inputCount)) {
//                sum += inputResults[position] * resultsMatrix[rLoc++]
//            }
//            outputResults[index] = calculateActivationThreshold(sum)
//            println("outputResults[$index]: ${outputResults[index]}")
//        }
//
//        val result = Array(outputCount) { 0.0 }
//        for(index in (outIndex until totalNeurons)) {
//            var sum = thresholds[index]
//            for(position in (hiddenIndex until outIndex)) {
//                sum += outputResults[position] * resultsMatrix[rLoc++]
//            }
//            outputResults[index] = calculateActivationThreshold(sum)
//            val tindex = index - outIndex
//            result[tindex] = outputResults[index]
//        }

    }

    fun calculateHiddenLayer(inputResultVector: List<Double>): List<Double> {
        // the manual way - without using matrices
//        val hiddenVector = (0 until hiddenCount).map { i:Int ->
//            var internalAcc = 0.0
//            val tempWeightVector = listOf(2.0, 3.0, 4.0)
//            inputResultVector.forEachIndexed { i:Int, value:Double ->
//                internalAcc += value * tempWeightVector[i]
//            }
//            internalAcc
//        }

        // a better way using fold - but still not using matrices
        val hiddenVector = (0 until hiddenCount).map { i:Int ->
            val tempWeightVector = listOf(2.0, 3.0, 4.0)
            val result = inputResultVector.foldIndexed(0.0) { j:Int, acc:Double, value:Double ->
                acc.plus( value * tempWeightVector[j] )
            }
            result
        }

        return hiddenVector
    }

    fun calculateInputLayer(inputMatrix:Array<Double>) = inputMatrix.map { it }.take(inputCount)

}