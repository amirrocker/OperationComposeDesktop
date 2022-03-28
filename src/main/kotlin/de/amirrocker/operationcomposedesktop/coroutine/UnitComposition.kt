package de.amirrocker.operationcomposedesktop.coroutine

/**
 * is it possible to represent AND Types and OR Types in Kotlin, as it is done in F# ?
 * Definition of a Type in FP(F#) is :
 *
 * Input -> Function F(int16) -> Output
 *
 * Note that Input does not make any assumption about the content of type, all we know
 * is the declared parameter type of the function, int16, or in kotlin it would be a Short value.
 * But no definition of how the Type is structured is given, so it is up to devs to define the Type.
 *
 * Imagine as Input all values from -1000 up to 1000. That would lead to familiar code for anybody who worked with
 * downstream flows such as Flow or Rx, where you have Observable Types that act as a container for
 * the Lists or Types.
 *
 * types in FP are much less defined than classes in OOP. A Type can be any number
 * of inputs to a specific function.
 *
 * a valid Set of Input A ==> Input -> Output ==> a valid Set of Outputs
 *
 * In this case the Type is "a valid Set of Input A" meaning it can be
 * ("a", "b", "c") or all numbers from (-1000 to 1000)
 *
 * TODO finish this text! at the moment it misses all context and therefore is a bit senseless. Context should be
 *      Function Composition with kotlin as it is possible with F#/Scala and other FP languages.
 */

fun defType() {
    (-1000 until 1000).forEach {
        FunctionF(it)
    }
}

fun FunctionF(input:Int) {
    println("received input $input")
}
