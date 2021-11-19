package de.amirrocker.operationcomposedesktop.domain

sealed interface Order {

    object Move : Order

    object Fight : Order

    object Support : Order

}

sealed interface OrderSequence {

    fun addOrder(order:Order)

    fun initSequence()

    fun executeOrder()

}






