package com.example.vestwallet.data

import com.example.vestwallet.R
import com.example.vestwallet.models.TransactionType
import com.example.vestwallet.models.TransactionTypeEnum
import java.text.NumberFormat
import java.util.Locale


private fun FormatCurrencyToString(amount: Double): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(amount)
}

var transactionList = listOf<TransactionType>(
    TransactionType.Deposit(
        donorProfileName = "Quincy Retur",
        donorProfileImage = R.drawable.female_emoji,
        recipientProfileName = "Emily",
        transactionType = TransactionTypeEnum.Deposit,
        transactionAmount = FormatCurrencyToString(1500.00)
    ),
    TransactionType.Convert(
        currentCurrencyName = "USD",
        futureCurrencyName = "NGN",
        currentCurrencyImage = R.drawable.united_states,
        futureCurrencyImage = R.drawable.nigeria,
        transactionType = TransactionTypeEnum.ConvertLoss,
        transactionAmount = FormatCurrencyToString(100.00)
    ),
    TransactionType.Deposit(
        donorProfileName = "Quincy Retur",
        donorProfileImage = R.drawable.female_emoji,
        recipientProfileName = "Emily",
        transactionType = TransactionTypeEnum.Deposit,
        transactionAmount = FormatCurrencyToString(1800.00)
    ),
    TransactionType.Convert(
        currentCurrencyName = "NGN",
        futureCurrencyName = "USD",
        currentCurrencyImage = R.drawable.united_states,
        futureCurrencyImage = R.drawable.nigeria,
        transactionType = TransactionTypeEnum.ConvertGain,
        transactionAmount = FormatCurrencyToString(20.00)
    ),
    TransactionType.Withdraw(
        recipientProfileName = "Remy Alyssa",
        recipientProfileImage = R.drawable.female_emoji,
        donorProfileName = "Emily",
        transactionType = TransactionTypeEnum.Withdraw,
        transactionAmount = FormatCurrencyToString(890.70)
    ),
    TransactionType.Deposit(
        donorProfileName = "Quincy Retur",
        donorProfileImage = R.drawable.female_emoji,
        recipientProfileName = "Emily",
        transactionType = TransactionTypeEnum.Deposit,
        transactionAmount = FormatCurrencyToString(2500.00)
    ),
    TransactionType.Withdraw(
        recipientProfileName = "Remy Alyssa",
        recipientProfileImage = R.drawable.female_emoji,
        donorProfileName = "Emily",
        transactionType = TransactionTypeEnum.Withdraw,
        transactionAmount =FormatCurrencyToString(500.00)
    ),
    TransactionType.Convert(
        currentCurrencyName = "USD",
        futureCurrencyName = "NGN",
        currentCurrencyImage = R.drawable.united_states,
        futureCurrencyImage = R.drawable.nigeria,
        transactionType = TransactionTypeEnum.ConvertLoss,
        transactionAmount = FormatCurrencyToString(2100.00)
    ),
)

var transactionMap = mapOf<String, List<TransactionType>>(
    "Monday, 6th Sept 2024" to transactionList,
    "Sunday, 5th Sept 2024" to transactionList,
    "Saturday, 4th Sept 2024" to transactionList,
    "Friday, 3th Sept 2024" to transactionList,
    "Thursday, 2th Sept 2024" to transactionList,
)
