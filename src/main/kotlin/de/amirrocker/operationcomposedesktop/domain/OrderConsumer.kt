package de.amirrocker.operationcomposedesktop.domain

interface OrderConsumer {

    fun receiveOrder(order:Order)

}