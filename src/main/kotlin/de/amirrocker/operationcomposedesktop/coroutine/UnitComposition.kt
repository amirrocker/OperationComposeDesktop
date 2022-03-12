package de.amirrocker.operationcomposedesktop.coroutine

/**
 * is it possible to represent AND Types and OR Types in Kotlin, as it is done in F# ?
 * Def of a Type in FP is :
 *
 * Input -> Function F(int16) -> Output
 *
 * Note that Input does not make any assumption about the content of type, all we know
 * is the declared parameter type of the function, int16, or in kotlin it would be a Short value.
 * But no def. of how the Type is structured is given, so it is up to devs to def. it :P
 *
 * Imagine as Input all values from -1000 up to 1000. That would lead to familiar code for anybody who worked with
 * downstream flows such as Flow or Rx, where you have Observable Types that act as a container for
 * the Lists or Types.
 */

fun defType() {
    (-1000 until 1000).forEach {
        FunctionF(it)
    }
}

fun FunctionF(input:Int) {
    println("received input $input")
}
