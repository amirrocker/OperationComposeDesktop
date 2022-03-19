package de.amirrocker.operationcomposedesktop.math

/**
 * thanks to sentdex - anybody interested in NN for finance stuff should take a look here :
 * https://www.youtube.com/channel/UCfzlCWGWYyIQ0aLC5w48gBQ
 */


import kotlin.math.exp
import kotlin.math.max
import kotlin.math.tanh


fun tanh(x:Double) = tanh(x)
fun sigmoid(x:Double) = 1.0 / 1.0 + exp(-x)
fun relu(x:Double) = max(0.0, x)
fun softmax(x:Double, allValues: DoubleArray) =
    exp(x) / allValues.map { exp(it) }.sum()

/**
 * this is the most basic building block of each nn.
 * what is modeled here is a simple neuron receiving the inputs from an external
 * source, another neuron.
 * A neuron receives three input values - so we imagine this neuron to be the top neuron
 * inside the hidden layer
 */
fun testASimpleNeuron() {

    val inputs = doubleArrayOf(1.2, 5.1, 2.1)
    val weights = doubleArrayOf(3.1, 2.1, 8.7)
    val bias = 3

    val output = inputs[0] * weights[0] + inputs[1] * weights[1] + inputs[2] * weights[2] + bias
    println("simple neuron output: $output")
}

/**
 * of course this is a very simplified view of the nn. But at its very heart this is core concept.
 * dot product the weights with the input, introduce non-linearity and add some bias.
 * If we then extend the imagination and pretend to have a number of neurons,
 * we get a layer, an input layer or a hidden layer.
 * lets try this:
 */
fun testANumberOfNeurons() {

    val inputs = doubleArrayOf(1.0, 2.0, 3.0, 4.0)

    val weight1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
    val weight2 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
    val weight3 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)

    val biases = doubleArrayOf(2.0, 3.0, 0.5)

    val output = doubleArrayOf(inputs[0] * weight1[0] + inputs[0] * weight1[1] + inputs[0] * weight1[2] + inputs[0] * weight1[3] + biases[0],
                               inputs[1] * weight1[0] + inputs[1] * weight1[1] + inputs[1] * weight1[2] + inputs[1] * weight1[3] + biases[1],
                               inputs[2] * weight1[0] + inputs[2] * weight1[1] + inputs[2] * weight1[2] + inputs[2] * weight1[3] + biases[2])
    println("the output of a three neuron layer: $output")
}


fun tryMeOut() {

    val inputs = doubleArrayOf(1.0, 2.0, 3.0, 4.0)

    val weight1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
    val weight2 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
    val weight3 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)

    val weights = arrayOf(weight1, weight2, weight3)

    val biases = doubleArrayOf(2.0, 3.0, 0.5)

    for(weightArray in weights) {
        println("weightArray: ${weightArray.size}")
    }
}
