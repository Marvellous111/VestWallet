package com.example.vestwallet.networkrequests

import tbdex.sdk.httpclient.TbdexHttpClient
import tbdex.sdk.protocol.models.Close
import tbdex.sdk.protocol.models.Order
import tbdex.sdk.protocol.models.OrderStatus
import tbdex.sdk.protocol.models.Quote
import web5.sdk.dids.did.BearerDid

fun placeOrder(userDid: String, quote: Quote): Order {
    val order = Order.create(
        from = userDid,
        to = quote.metadata.from,
        exchangeId = quote.metadata.exchangeId,
        protocol = "2.0.1"
    )

    return order
}

fun signOrder(order: Order, userBearerDid: BearerDid): Order {
    order.sign(userBearerDid)

    return order
}

fun sendOrderToPfi(order: Order, userBearerDid: BearerDid): Pair<String?, Close> {
    TbdexHttpClient.submitOrder(order)

    var orderStatusUpdate: String? = ""
    var close: Close? = null

    while (close == null) {
        val exchange = TbdexHttpClient.getExchange(
            pfiDid = order.metadata.to,
            requesterDid = userBearerDid,
            exchangeId = order.metadata.exchangeId //.toString() possibly used if exchangeId isn't string
        )

        for (message in exchange) {
            when (message) {
                is OrderStatus -> {
                    // a status update to display to your customer
                    orderStatusUpdate = message.data.orderStatus
                }
                is Close -> {
                    // final message of exchange has been written
                    close = message
                }
                else -> {}
            }
        }
    }
    return Pair(orderStatusUpdate, close)
}

fun getCloseOrderMessage(close: Close): Pair<Boolean?, String?> {
    val isSuccessful = close.data.success
    val reasonForClose = close.data.reason

    return Pair(isSuccessful, reasonForClose)
}