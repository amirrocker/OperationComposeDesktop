package de.amirrocker.operationcomposedesktop.domain

import org.junit.jupiter.api.Test
import java.net.Authenticator
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.test.assertTrue

class OrdersTest {

    private val hq = object: OrderProducer {

    override val orderQueue = ArrayDeque<Order>()

        override fun produceOrder(): Order {
            TODO("Not yet implemented")
        }
    }

    enum class OrderRequestType {
        MOVEMENT,
        COMBAT,
        ADMINISTRATION,
        SPECIAL,
    }

    interface OrderRequest {
        val requestType : OrderRequestType
    }

    fun OrderRequest.orderRequest(requestorType: OrderRequestType) = {
        requestType = request
    }

    @Test
    internal fun `given a HQ when received order then prepare and store the order in queue`() {

        val preparedOrder = hq.produceOrder( orderRequest {
            requestType = MoveTo {
                from = FromLocation.current()
                to = ToLocation.selected()
                moveType = MoveType.FAST
            }
        })

        hq.addOrder(preparedOrder)

        hq.orderQueue.size shouldBe 1

    }
}