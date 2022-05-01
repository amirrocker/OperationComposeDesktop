package de.amirrocker.operationcomposedesktop.domain

import java.util.*
import kotlin.collections.ArrayDeque

interface OrderProducer {
    val orderQueue : ArrayDeque<Order>
    fun produceOrder():Order

}