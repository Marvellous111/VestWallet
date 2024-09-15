package com.example.vestwallet.models

import androidx.annotation.DrawableRes

sealed class TransactionType {
    data class Deposit(
        @DrawableRes val donorProfileImage: Int,
        val donorProfileName: String,
        val recipientProfileName: String,
        val transactionType: TransactionTypeEnum,
        val transactionAmount: String,
    ) : TransactionType()

    data class Withdraw(
        @DrawableRes val recipientProfileImage: Int,
        val recipientProfileName: String,
        val donorProfileName: String,
        val transactionType: TransactionTypeEnum,
        val transactionAmount: String,
    ) : TransactionType()

    data class Convert(
        @DrawableRes val currentCurrencyImage: Int,
        @DrawableRes val futureCurrencyImage: Int,
        val currentCurrencyName: String,
        val futureCurrencyName: String,
        val transactionType: TransactionTypeEnum,
        val transactionAmount: String,
    ) : TransactionType()
}

