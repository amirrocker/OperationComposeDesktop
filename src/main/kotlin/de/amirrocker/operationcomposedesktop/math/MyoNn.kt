package de.amirrocker.operationcomposedesktop.math

typealias InputLayer = Matrix<Double>
typealias HiddenLayer = Matrix<Double>
typealias OutputLayer = Matrix<Double>


class MyoNn(
    val inputLayer: InputLayer,
    val hiddenLayer: HiddenLayer,
    val outputLayer: OutputLayer,
    val learningRate: Double,

) {

    fun init() {}

    fun train() {}

    fun query() {}

}