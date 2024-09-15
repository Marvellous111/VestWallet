package com.example.vestwallet.models.pfis

data class Pfi(
    val pfiName: String,
    val did: String,
    val conversions: Map<String, String>
)

data class ConversionStep(
    val pfi: Pfi,
    val fromCurrency: String,
    val toCurrency: String,
    val isForward: Boolean
)