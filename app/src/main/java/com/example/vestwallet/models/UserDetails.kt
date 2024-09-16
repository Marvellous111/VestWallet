package com.example.vestwallet.models

import android.telecom.Call.Details.hasProperty
import io.realm.kotlin.MutableRealm
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.migration.AutomaticSchemaMigration
import io.realm.kotlin.migration.RealmMigration
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey
import web5.sdk.dids.didcore.DidDocument
import kotlin.properties.Delegates


//open class userTransactionsHistory : RealmObject {
//    var
//}


open class UserCurrency : RealmObject {
    var code: String = ""    // E.g., USD, EUR
    var country: String = "" // E.g., United States of America, European Country
    var value: Double = 0.00
    var account: String = ""
}

open class UserDetails : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var email: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var middleName: String = ""
    var password: String = ""
    var dateOfBirth: String = ""
    var country: String = ""
    var phoneNumber: String = ""
    var countryCode: String = ""
    var transactionPin: String = ""
    var userDid: String = ""

    var didDocument: String = ""

    var currencies: RealmList<UserCurrency> = realmListOf(
        UserCurrency().apply {
            code = "USDC"
            country = "USDC"
            value = 0.00
            account = "USDCACCOUNT"
        },
        UserCurrency().apply {
            code = "GHS"
            country = "Ghana"
            value = 0.00
            account = "GHSACCOUNT"
        },
        UserCurrency().apply {
            code = "NGN"
            country = "Nigeria"
            value = 0.00
            account = "NGNACCOUNT"
        },
        UserCurrency().apply {
            code = "KES"
            country = "Kenya"
            value = 0.00
            account = "KESACCOUNT"
        },
        UserCurrency().apply {
            code = "USD"
            country = "United States of America"
            value = 0.00
            account = "USDACCOUNT"
        },
        UserCurrency().apply {
            code = "EUR"
            country = "European Country"
            value = 0.00
            account = "EURACCOUNT"
        },
        UserCurrency().apply {
            code = "GBP"
            country = "United Kingdom"
            value = 0.00
            account = "GBPACCOUNT"
        },
        UserCurrency().apply {
            code = "BTC"
            country = "Bitcoin"
            value = 0.00
            account = "BTCACCOUNT"
        },
        UserCurrency().apply {
            code = "AUD"
            country = "Australia"
            value = 0.00
            account = "AUDACCOUNT"
        },
        UserCurrency().apply {
            code = "MXN"
            country = "Mexico"
            value = 0.00
            account = "MXNACCOUNT"
        }
    )
}


sealed class AuthState {
    data object Unauthenticated : AuthState()
    data class Authenticated(val email: String) : AuthState()
    data class Error(var message: String) : AuthState()
}