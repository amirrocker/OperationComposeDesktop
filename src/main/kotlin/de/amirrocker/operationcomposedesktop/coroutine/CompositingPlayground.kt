package de.amirrocker.operationcomposedesktop.coroutine

import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.system.measureTimeMillis

/**
 * types in FP are much less defined than classes in OOP. A Type can be any number
 * of inputs to a specific function.
 *
 * a valid Set of Input A ==> Input -> Output ==> a valid Set of Outputs
 *
 * In this case the Type is "a valid Set of Input A" meaning it can be
 * ("a", "b", "c") or all numbers from (-1000 to 1000)
 *
 */

//fun playWithScope() = runBlocking {
////    calculate(1)
////    calculate(2)
//
//    structureCalculate()
//    println("playWithScope finished")
//
//}

suspend fun calculate(index:Int) {
    delay(1000)
    println("calculate $index")
}

suspend fun structureCalculate() = coroutineScope {
    val launchJob = launch(Dispatchers.Default) {
        (0..100).forEach {
            delay(10)
            println("The first delay $it")
        }
    }

    val asyncJob = async(Dispatchers.Default) {
        val result = (0..10).forEach {
            delay(200)
            println("The second delay $it")
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond().times(1000)
        }
        println(result)
    }

    println("structuredCalculate bottom print")

    launchJob.join()
    asyncJob.join()

    println("jobs are done")

}

// using async - returns a deferred (aka a Future)
suspend fun doFutureStuff() = coroutineScope {
    val timeOverall = measureTimeMillis {

        val one = async {
            delay(2000)
            println("one finished")
            "Awaited One - the one"
        }

        val two = async {
            delay(1000)
            println("two finished")
            "Awaited Two - take Two"
        }
        println("${one.await()} ${two.await()}")
    }
    println("completed in overall time: $timeOverall ms")
}


suspend fun doFutureStuffLazily() = coroutineScope {
    val overallTime = measureTimeMillis {
        val jobA = async(start = CoroutineStart.LAZY) {
            delay(1000L)
            "JobA ran"
        }

        val jobB = async(start = CoroutineStart.LAZY) {
            delay(2000)
            "JobB ran"
        }
        println("jobA isActive: ${jobA.isActive}")
        println("jobB isActive: ${jobB.isActive}")

        delay(5000)

        // note: when not explicitly starting, the jobs will execute sequentially increasing the time by about 1000ms.
        //       which kinda removes the lazy argument so try to avoid this ;P
        jobA.start()
        jobB.start()

        println("jobA.await() -> result: ${jobA.await()}")
        println("jobB.await() -> result: ${jobB.await()}")
    }
    println("both jobs completed in : $overallTime ms")
}

suspend fun doStructuredConcurrency() {
    val time = measureTimeMillis {
        val result = concurrentSum()
        println("result: $result")
    }
    println("time: $time")
}

fun doSomethingUseful() = 256
fun doSomethingElseUseful() = 24

suspend fun concurrentSum() : Int = coroutineScope {
    val one = async { doSomethingUseful() }
    val two = async { doSomethingElseUseful() }
    one.await() + two.await()
}


// now we come to the bread and butter of coroutines
//suspend fun runDispatchers() = coroutineScope {
//
//}


// From old main

//var aquired = 0
//
//class Resource {
//    init {
//        aquired++
//        println(aquired)
//    }
//    fun close() {
//        aquired--
//        println(aquired)
//    }
//}
//
//fun main() {

//        runThePoet()
//        calculateFps()

//    runBlocking {
//        repeat(100) {
//            launch {
//                val resource = withTimeout(10L) {
//                    delay(10L)
//                    Resource()
//                }
//                resource.close()
//            }
//        }
//    }
//    println("MAIN aquired: $aquired")
//}

//sealed class FunList<out T> {
//
//    object Nil : FunList<Nothing>()
//
//    data class Cons<out T>(val head:T, val tail:FunList<out T>): FunList<T>()
//}
//
//
//fun playWithFunList() {
//
//    // functional but ugly, very, very ugly :)
//    val funList = FunList.Cons("value1", FunList.Cons("value2", FunList.Cons("value3", FunList.Nil)))
//
//
//}

//@OptIn(DelicateCoroutinesApi::class) // necessary due to newSingleThreadedContext
//fun main(args:Array<String>) = runBlocking {
////    doFutureStuff()
////    doFutureStuffLazily()
////    doStructuredConcurrency()
//
////    works here as well - just doesn't make as much sense as in Android :P
////    val mainScope = MainScope()
//
////    val runBlockinScope = runBlocking {
//        // launch calls - sequentially called
////        println("start of scope called ... ")
////        launch {
////            println("launch main runBlocking : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////
////        launch(Dispatchers.Unconfined) {
////            println("launch Unconfined : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////
////        launch(Dispatchers.Default) {
////            println("launch Default : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////
////        launch(newSingleThreadContext("OwnThread")) {
////            println("launch newSingleThreadContext : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////        println("end of scope called ... ")
////
////        // async calls
////        println("start of scope called ... ")
////        async {
////            println("async main runBlocking : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////
////        async(Dispatchers.Unconfined) {
////            println("async Unconfined : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////
////        async(Dispatchers.Default) {
////            println("async Default : currentThread: ${java.lang.Thread.currentThread().name}")
////        }
////
////        async(newSingleThreadContext("OwnThread")) {
////            println("async newSingleThreadContext : currentThread: ${Thread.currentThread().name}")
////        }
////        println("end of scope called ... ")
////
////        // confined vs. unconfined
////        launch(Dispatchers.Unconfined) {
////            println("Unconfined in with : ${Thread.currentThread().name}")
////            delay(1500)
////            println("Unconfined go on with : ${Thread.currentThread().name}")
////        }
////
////        launch {
////            println("Main in with : ${Thread.currentThread().name}")
////            delay(1500)
////            println("Main go on with : ${Thread.currentThread().name}")
////        }
////    }
////
////    // jumping between threads - try to avoid using the newSingleThreadedContext when possible! for now
////    newSingleThreadContext("Ctx1").use { ctx1 ->
////        newSingleThreadContext("ctx2").use { ctx2 ->
////            runBlocking(ctx1) {
////                println("started in ctx1")
////                withContext(ctx2) {
////                    println("working in ctx2")
////                }
////                println("back to ctx1")
////            }
////        }
////    }
////
////    //
////    println("still inside mainScope ... ")
////
////    val version1 = async(CoroutineName("Version1Coroutine")) {
////        delay(2000)
////        println("version1 after delay")
////        123.0
////    }
////
////    val version2 = async(CoroutineName("Version2Coroutine")) {
////        delay(1000)
////        println("version2 after delay")
////        321.0
////    }
////
////    println("answer for version1/version2: ${version1.await() / version2.await()}")
////
////
////    // the preferred way of coroutine creation:
////    val version3 = async(Dispatchers.Default + CoroutineName("Version3Coroutine")) {
////        println("Working in thread: ${Thread.currentThread().name}")
////    }
////
////
//    // launch concurrent coroutine to check whether the main thread is blocked
//    launch {
//        (0..100).forEach {
//            println("main is not blocked : ${Thread.currentThread().name}")
//            delay(1000)
//        }
//    }
////
////    // now also run a flow
////    fun simple() = flow {
////        for(i in 0..10) {
////            println("flow pretend we do real work - i: $i")
////            delay(500)
////            emit(i)
////        }
////    }
////
////    simple().collect { value:Int ->
////        println("value:> $value")
////    }
////
////    simple().collect { value ->
////        println("second collect on cold flow : ${value * 2}")
////    }
////
////    // simple flow builders
////    // from a sequence
////    (0..10).asFlow().collect {
////        println("asFlow: $it")
////    }
////
////    // operators in flow can call suspending async funs unlike e.g. sequences or collections
////    (0..3).asFlow()
////        .map {
////            performRequest(it)
////        }.collect {
////            println("final result: $it")
////        }
////
////    // transform
////    (0..3).asFlow()
////        .transform {
////            emit("Some Value - string in this case - emitted before suspending fun is called with pot. long running request")
////            emit(performRequest(it))
////        }.collect { response:String ->
////            println("response: $response")
////        }
//
//    val numbers = flow<Int> {
//        try {
//            (0..5).fold(10) { acc: Int, i: Int ->
//                println("inside fold: $acc and i: $i")
//                emit(acc + i)
//                acc + i
//            }
//        } finally {
//            println("finally called")
//        }
//    }.take(2).collect {
//        println("collect: $it")
//    }
//
//    val chars = ('A'..'Z').asFlow()
//        .map {
//                it.lowercase()
//        }.reduce { accumulator: String, value: String ->
//            accumulator + value
//        }
//
////    chars.collect { println("collecting chars: $it")}
//    println("chars: expect a String: $chars")
//    println("chars: expect a String: ${chars.count()}")
//
//    // context change in flow
//    // using flowOn is right way - using withContext(Dispatchers...) is wrong way !
//    // DO NOT DO THIS! throws IllegalStateException
//    val tenPieces = flow<Int> {
//        withContext(Dispatchers.IO) {
//            (0..10).forEach {
////                emit(it) // uncomment to throw exception
//            }
//        }
//    }.collect {
//        println(it)
//    }
//
//    // instead do this:
//
//    val eleven = flow<Int> {
//        (0..15).forEach {
//            emit(it)
//        }
//    }.flowOn(Dispatchers.Default)
//        .collect {
//            println("received emit value on Main[${java.lang.Thread.currentThread()}]: $it")
//        }
//
//    val simple = flow<String> {
//        (0..14).forEachIndexed { index, value ->
//            delay(1000)
//            emit(index.toString())
//        }
//    }
//
//    val time = measureTimeMillis {
//        simple.collect { result ->
//            delay(1000)
//            println("after delay: $result")
//        }
//    }
//    println("all took $time ms")
//
//}
//
//
//suspend fun performRequest(req:Int):String {
//    delay(2000)
//    return "response: $req"
//}





