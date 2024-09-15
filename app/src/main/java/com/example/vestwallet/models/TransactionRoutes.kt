package com.example.vestwallet.models

sealed class TransactionRoute(
    val route: String
) {
    object DepositOption : TransactionRoute("depositoption")
    object DepositCreditCardOption : TransactionRoute("depositcreditcardoption")
    object DepositCreditCardConfirm : TransactionRoute("depositcreditcardconfirm")
    object DepositUSDCOption : TransactionRoute("depositusdcoption")
    object DepositBankOption : TransactionRoute("depositbankoption")

    object WithdrawOption : TransactionRoute("withdrawoption")
    object WithdrawBankOption : TransactionRoute("withdrawbankoption")
    object WithdrawUSDCOption : TransactionRoute("withdrawusdcoption")

    object TypePin : TransactionRoute("typepin")

    object ConvertScreen : TransactionRoute("convertscreen")
    object ConvertDoneScreen : TransactionRoute("convertdonescreen")
}