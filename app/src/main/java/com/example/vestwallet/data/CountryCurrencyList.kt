package com.example.vestwallet.data

import com.example.vestwallet.models.Currency
import com.example.vestwallet.models.UserDetails

object CountryCurrencyList {
    var currencyList = listOf<Currency>(
        Currency(
            currencyCode = "USDC",
            currencyCountry = "USDC",
            currencyAccount = "USDCACCOUNT"
        ),
        Currency(
            currencyCode = "GHS",
            currencyCountry = "Ghana",
            currencyAccount = "GHSACCOUNT"
        ),
        Currency(
            currencyCode = "NGN",
            currencyCountry = "Nigeria",
            currencyAccount = "NGNACCOUNT"
        ),
        Currency(
            currencyCode = "KES",
            currencyCountry = "Kenya",
            currencyAccount = "KESACCOUNT"
        ),
        Currency(
            currencyCode = "USD",
            currencyCountry = "United States of America",
            currencyAccount = "USDACCOUNT"
        ),
        Currency(
            currencyCode = "EUR",
            currencyCountry = "European Country",
            currencyAccount = "EURACCOUNT"
        ),
        Currency(
            currencyCode = "GBP",
            currencyCountry = "United Kingdom",
            currencyAccount = "GBPACCOUNT"
        ),
        Currency(
            currencyCode = "BTC",
            currencyCountry = "Bitcoin",
            currencyAccount = "BTCACCOUNT"
        ),
        Currency(
            currencyCode = "AUD",
            currencyCountry = "Australia",
            currencyAccount = "AUDACCOUNT"
        ),
        Currency(
            currencyCode = "MXN",
            currencyCountry = "Mexico",
            currencyAccount = "MXNACCOUNT"
        ),
    )
}