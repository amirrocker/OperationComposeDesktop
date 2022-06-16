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
        UNDEFINED,
    }

    interface OrderRequest {
        val requestType : OrderRequestType
    }

    class MoveOrderRequest(
        override val requestType: OrderRequestType = OrderRequestType.UNDEFINED
    ): OrderRequest {}

    fun OrderRequest(init: OrderRequest.()->OrderRequest):OrderRequest =
        OrderRequest().apply(init)


    fun OrderRequest.MoveTo(init:OrderRequest.() -> OrderRequest):OrderRequest =
        MoveOrderRequest().apply(init)


    @Test
    internal fun `given a HQ when received order then prepare and store the order in queue`() {

        val preparedOrder = hq.produceOrder(
            orderRequest {
                requestType = MoveTo {
                    from = FromLocation.current()
                    to = ToLocation.selected()
                    moveType = MoveType.FAST
                }
            }
        )

        hq.addOrder(preparedOrder)

        hq.orderQueue.size shouldBe 1

    }
}