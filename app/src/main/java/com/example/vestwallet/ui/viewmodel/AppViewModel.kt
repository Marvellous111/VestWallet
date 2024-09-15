package com.example.vestwallet.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vestwallet.data.pfidata.PfiList
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.models.did.DidClass
import com.example.vestwallet.models.pfis.ConversionStep
import com.example.vestwallet.networkrequests.VcApiService
import com.example.vestwallet.networkrequests.getCloseOrderMessage
import com.example.vestwallet.networkrequests.getOfferings
import com.example.vestwallet.networkrequests.placeOrder
import com.example.vestwallet.networkrequests.pollQuoteFromPfi
import com.example.vestwallet.networkrequests.requestForQuote
import com.example.vestwallet.networkrequests.sendOrderToPfi
import com.example.vestwallet.networkrequests.sendRequestForQuoteToPfi
import com.example.vestwallet.networkrequests.signOrder
import com.example.vestwallet.networkrequests.signRequestForQuote
import com.example.vestwallet.networkrequests.verifyRequestForQuote
import com.example.vestwallet.utils.findDetailedConversionPath
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import tbdex.sdk.protocol.models.Quote
import web5.sdk.dids.did.BearerDid

class ConvertCurrencyClass {

    //private val _appUiState = MutableStateFlow<>()

    fun getCurrencyQuotes(
        inputCurrencyCode: String,
        conversionAmount: String,
        outputCurrencyCode: String,
        userDid: String,
        userBearerDid: DidClass,
        userDetails: UserDetails
    ): Triple<MutableList<Quote>, MutableList<Double>, List<ConversionStep>> {

        val quoteDoubleList = mutableListOf<Double>()

        val quoteList = mutableListOf<Quote>()

        val pfiArray = findDetailedConversionPath(
            pfiList = PfiList.pfiList,
            fromCurrency = inputCurrencyCode,
            toCurrency = outputCurrencyCode
        )

        Log.d("convertButtonPressed", pfiArray.toString())

        val inputCurrency = userDetails.currencies.find { it.code.equals(inputCurrencyCode, ignoreCase = true) }
        val outputCurrency = userDetails.currencies.find { it.code.equals(outputCurrencyCode, ignoreCase = true) }

        val matchedOfferings = getOfferings(
            inputCurrency = inputCurrencyCode,
            outputCurrency = outputCurrencyCode,
            userDid = userDid,
            userDetails = userDetails
        )

        val credentials = runBlocking {
            val vcApiService = VcApiService()
            vcApiService.getVc(
                userDid = userDid,
                countryCode = userDetails.countryCode,
                userName = userDetails.firstName
            )
        }
        val credentialsList = listOf<String>(credentials)
        Log.d("convertButtonPressed", matchedOfferings.toString())
        for (offeringIndex in 0..matchedOfferings.lastIndex) {
            val rfq = inputCurrency?.let {
                outputCurrency?.let { it1 ->
                    requestForQuote(
                        pfiDid = pfiArray[offeringIndex].pfi.did,
                        userDid = userDid,
                        offeringId = matchedOfferings[offeringIndex].metadata.id,
                        offeringFromCurrency = inputCurrencyCode,
                        offeringToCurrency = outputCurrencyCode,
                        claims = credentialsList,
                        amountToGive = conversionAmount,
                        fromPaymentBankAccount = it.account,
                        toPaymentBankAccount = it1.account
                    )
                }
            }
            Log.d("convertButtonPressed", rfq.toString())
//            var quoteVerificationMessage = rfq?.let {
//                verifyRequestForQuote(
//                    rfq = it,
//                    offering = matchedOfferings[offeringIndex]
//                )
//            }


            val signedRfq = rfq?.let {
                userBearerDid.userBearerDid?.let { it1 ->
                    signRequestForQuote(
                        rfq = it,
                        userBearerDid = it1
                    )
                }
            }

            if (signedRfq != null) {
                sendRequestForQuoteToPfi(signedRfq)
            }
            val quoteForRfq = signedRfq?.let {
                userBearerDid.userBearerDid?.let { it1 ->
                    pollQuoteFromPfi(
                        rfq = it,
                        userDid = it1
                    )
                }
            }
            if (quoteForRfq != null) {
                quoteList.add(quoteForRfq)
            }

            val payinAmount = (quoteForRfq?.data?.payin?.amount)?.toDouble()
            val payoutAmount = (quoteForRfq?.data?.payout?.amount)?.toDouble()

            val quoteDouble = (payinAmount?.let { payoutAmount?.div(it) }) ?: 0.00

            quoteDoubleList.add(quoteDouble)
        }
        return Triple(quoteList, quoteDoubleList, pfiArray)
    }

    fun convertCurrency(
        userBearerDid: BearerDid,
        quotes: MutableList<Quote>,
        userDid: String,
    ): Pair<Boolean?, String?> {
        val closeMessageList =  mutableListOf<Pair<Boolean?, String?>>()
        for (quote in quotes) {
            val orderDetail = placeOrder(userDid = userDid, quote = quote)

            val signedOrder = signOrder(
                order = orderDetail,
                userBearerDid = userBearerDid
            )

            val orderPair = sendOrderToPfi(
                order = signedOrder,
                userBearerDid = userBearerDid
            )

            val closeMessage = getCloseOrderMessage(
                close = orderPair.second
            )
            closeMessageList.add(closeMessage)
        }
        return closeMessageList.last()
    }
}