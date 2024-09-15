package com.example.vestwallet.networkrequests

import tbdex.sdk.httpclient.TbdexHttpClient
import tbdex.sdk.protocol.models.Close
import tbdex.sdk.protocol.models.CloseData
import tbdex.sdk.protocol.models.Quote
import tbdex.sdk.protocol.models.Rfq
import web5.sdk.dids.did.BearerDid

fun pollQuoteFromPfi(
    rfq: Rfq,
    userDid: BearerDid,
): Quote? {
    var quote: Quote? = null
    var close: Close?

    while (quote == null) {
        val exchange = TbdexHttpClient.getExchange(
            pfiDid = rfq.metadata.to,
            requesterDid = userDid,
            exchangeId = rfq.metadata.exchangeId
        )
        quote = exchange.find { it is Quote } as Quote?

        if (quote == null) {
            // Make sure the exchange is still open
           close = exchange.find { it is Close } as Close?

            if (close != null) {
                break
            } else {
                // Wait 2 seconds before making another request
                Thread.sleep(2000)
            }
        }
    }
    return quote
}

fun closeOrder(quote: Quote, userDid: String, userBearerDid: BearerDid) {
    val close = Close.create(
        from = userDid,
        to = quote.metadata.from,
        exchangeId = quote.metadata.exchangeId,
        closeData = CloseData(reason = "Closed by user")
    )
    close.sign(userBearerDid)
    TbdexHttpClient.submitClose(close)
}