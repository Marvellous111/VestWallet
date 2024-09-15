package com.example.vestwallet.data.pfidata

import com.example.vestwallet.models.pfis.Pfi


// This is the data containing the list of pfis and their did
object PfiList {
    var pfiList = listOf<Pfi>(
        Pfi(
            "AquaFinance Capital",
            "did:dht:3fkz5ssfxbriwks3iy5nwys3q5kyx64ettp9wfn1yfekfkiguj1y",
            mapOf<String, String>(
                "GHS" to "USDC",
                "NGN" to "KES",
                "KES" to "USD",
                "USD" to "KES"
            )
        ),
        Pfi(
            "Flowback Financial",
            "did:dht:zkp5gbsqgzn69b3y5dtt5nnpjtdq6sxyukpzo68npsf79bmtb9zy",
            mapOf<String, String>(
                "USD" to "EUR",
                "EUR" to "USD",
                "USD" to "GBP",
                "USD" to "BTC"
            )
        ),
        Pfi(
            "Vertex Liquid Assets",
            "did:dht:enwguxo8uzqexq14xupe4o9ymxw3nzeb9uug5ijkj9rhfbf1oy5y",
            mapOf<String, String>(
                "EUR" to "USD",
                "EUR" to "USDC",
                "USD" to "EUR",
                "EUR" to "GBP"
            )
        ),
        Pfi(
            "Titanium Trust",
            "did:dht:ozn5c51ruo7z63u1h748ug7rw5p1mq3853ytrd5gatu9a8mm8f1o",
            mapOf<String, String>(
                "USD" to "AUD",
                "USD" to "GBP",
                "USD" to "KES",
                "USD" to "MXN"
            )
        )
    )
}