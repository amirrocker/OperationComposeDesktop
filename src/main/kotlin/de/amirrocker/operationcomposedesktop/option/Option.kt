package de.amirrocker.operationcomposedesktop.option

sealed class Option<out T> {

    object None : Option<Nothing>()

    data class Some<out T>(val data: T) : Option<T>()

}

fun <T, R> Option<T>.map(transform: (T)->R): Option<R> = when(this) {
    Option.None -> Option.None
    is Option.Some<T> -> Option.Some(transform(this.data))
}


