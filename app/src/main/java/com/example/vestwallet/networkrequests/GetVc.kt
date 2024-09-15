package com.example.vestwallet.networkrequests

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText


class VcApiService {
    private val client = HttpClient(Android)

    suspend fun getVc(
        userDid: String,
        countryCode: String,
        userName: String
    ): String {
        val url = "https://mock-idv.tbddev.org/kcc?name=${userName}&country=${countryCode}&did=${userDid}"
        return try {
            client.get(url).bodyAsText()
        } catch (e: Exception) {
            "${e.message}"
        }
    }
}