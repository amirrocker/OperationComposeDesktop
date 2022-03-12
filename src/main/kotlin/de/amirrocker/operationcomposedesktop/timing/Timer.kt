package de.amirrocker.operationcomposedesktop.timing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime


@OptIn(InternalCoroutinesApi::class)
fun startFpsCalculator() = ticker(10, 0L)
            .map {
                LocalDateTime.now()
            }
            .distinctUntilChanged { old, new -> old.second == new.second }
//            .onEach {
//                println("time: $it")
//            }
            .flowOn(Dispatchers.IO)






//suspend fun calculateFps() =
////    kotlintimer(100000) { index ->
////        println("repeated: $index")
////    }
//
//    ticker(10, 0L)
//        .map {
//            LocalDateTime.now()
//        }
//        .distinctUntilChanged { old, new -> old.second == new.second }
//        .onEach {
//            println("time: $it")
//        }
//        .flowOn(Dispatchers.Default)
//        .collectLatest {
//            println("latest dateTime: $it")
//        }
//        // android would do something like
////        .launchIn(viewModelScope)



private fun ticker(period:Long, initialDelay:Long = Long.MIN_VALUE) = flow {
    delay(initialDelay)
    while(true) {
        emit(Unit)
        delay(period)
    }
}

