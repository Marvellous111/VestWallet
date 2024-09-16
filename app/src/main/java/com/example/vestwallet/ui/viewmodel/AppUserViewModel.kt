package com.example.vestwallet.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vestwallet.data.pfidata.PfiList
import com.example.vestwallet.models.UserCurrency
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
import com.example.vestwallet.utils.findDetailedConversionPath
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import tbdex.sdk.protocol.models.Quote
import web5.sdk.dids.did.BearerDid

class AppUserViewModel : ViewModel() {

    //val userEmail = MutableLiveData<String>((authViewModel.userEmail).toString())

    //private val _appUiState = MutableStateFlow<>()

    private lateinit var realm: Realm
    private val _userDetails = MutableStateFlow<UserDetails?>(null)
    val userDetails: StateFlow<UserDetails?> = _userDetails.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val config = RealmConfiguration.Builder(schema = setOf(UserDetails::class, UserCurrency::class))
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .name("user_database.realm")
                .build()
            realm = Realm.open(config)

            val result = realm.query<UserDetails>().first().find()
            withContext(Dispatchers.Main) {
                _userDetails.value = result
                userDetails.value?.let { Log.d("Current user name", it.firstName) }
            }
        }
    }

    suspend fun getCurrentUser(): Triple<String, String, String> {
        var countryCode: String = ""
        var currencyAmount: String = ""
        var firstName = ""
        viewModelScope.launch(Dispatchers.IO) {
            val result = realm.query<UserDetails>().first().find()
            withContext(Dispatchers.Main) {
                _userDetails.value = result
            }
            firstName = _userDetails.value?.firstName.toString()


            countryCode = userDetails.value?.countryCode.toString()
            var countryCurrency =
                userDetails.value?.currencies?.query("code == $0", countryCode)?.first()?.find()
            currencyAmount = if (countryCurrency?.value == 0.00) {
                "0.00"
            } else {
                countryCurrency?.value.toString()
            }
        }

        return Triple(firstName, countryCode, currencyAmount)
    }

    fun getCurrencyQuotes(
        inputCurrencyCode: String,
        conversionAmount: String,
        outputCurrencyCode: String,
        userDetails: UserDetails = realm.query<UserDetails>().find().first()
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
            userDid = userDetails.userDid,
            userDetails = userDetails
        )

        val credentials = runBlocking {
            val vcApiService = VcApiService()
            vcApiService.getVc(
                userDid = userDetails.userDid,
                countryCode = userDetails.countryCode,
                userName = userDetails.firstName
            )
        }
        //val credentialsList = listOf<String>(credentials)
        Log.d("convertButtonPressed", matchedOfferings.toString())
        for (offeringIndex in 0..matchedOfferings.first.lastIndex) {
            val rfq = inputCurrency?.let {
                outputCurrency?.let { it1 ->
                    requestForQuote(
                        pfiDid = pfiArray[offeringIndex].pfi.did,
                        userDid = userDetails.userDid,
                        offeringId = matchedOfferings.first[offeringIndex].metadata.id,
                        offeringFromCurrency = inputCurrencyCode,
                        offeringToCurrency = outputCurrencyCode,
                        claims = matchedOfferings.second[offeringIndex],
                        amountToGive = conversionAmount,
                        fromPaymentBankAccount = it.account,
                        toPaymentBankAccount = it1.account
                    )
                }
            }
            Log.d("rfq", rfq.toString())
//            var quoteVerificationMessage = rfq?.let {
//                verifyRequestForQuote(
//                    rfq = it,
//                    offering = matchedOfferings[offeringIndex]
//                )
//            }


            var convertedToBearerDid: BearerDid? = null
            val signedRfq = rfq?.let {
                convertedToBearerDid = DidClass().changetoBearerDid(userDetails.didDocument)
                signRequestForQuote(
                    rfq = it,
                    userBearerDid = convertedToBearerDid!!
                )
            }
            Log.d("signedrfq", signedRfq.toString())

            if (signedRfq != null) {
                sendRequestForQuoteToPfi(signedRfq)
                Log.d("requestsenttopfi", "Request sent")
            }
            val quoteForRfq = signedRfq?.let {
                convertedToBearerDid?.let { it1 ->
                    pollQuoteFromPfi(
                        rfq = it,
                        userDid = it1
                    )
                }
            }
            Log.d("quoteforrfq", quoteForRfq.toString())
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

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }
}