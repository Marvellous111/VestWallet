package com.example.vestwallet.networkrequests

import com.example.vestwallet.data.pfidata.PfiList
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.models.pfis.ConversionStep
import com.example.vestwallet.models.pfis.Pfi
import com.example.vestwallet.utils.findDetailedConversionPath
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tbdex.sdk.httpclient.TbdexHttpClient
import tbdex.sdk.protocol.models.Offering
import web5.sdk.credentials.PresentationExchange



fun getOfferings(
    inputCurrency: String,
    outputCurrency: String,
    userDid: String,
    userDetails: UserDetails
): Pair<List<Offering>, List<List<String>>> {

    //var scope = CoroutineScope(Dispatchers.IO)

    // Array to store the matched offerings
    val matchedOfferings = ArrayList<Offering>()

    val pfiArray: List<ConversionStep> = findDetailedConversionPath(
        pfiList = PfiList.pfiList,
        fromCurrency = inputCurrency,
        toCurrency = outputCurrency
    )


    lateinit var pfiDid: String

    val credentials = runBlocking {
        val vcApiService = VcApiService()
        vcApiService.getVc(
            userDid = userDid,
            countryCode = userDetails.countryCode,
            userName = userDetails.firstName
        )
    }
    val credentialsList = listOf<String>(credentials)

    val claimsList = mutableListOf<List<String>>()

    if (pfiArray.isNotEmpty()) {
        for (steps in pfiArray) {
            pfiDid = steps.pfi.did
            val offerings = TbdexHttpClient.getOfferings(pfiDid)

            offerings.filter { offering ->
                offering.data.payin.currencyCode == steps.fromCurrency && offering.data.payout.currencyCode == steps.toCurrency
            }.forEach { offering ->
                offering.data.requiredClaims?.let { presentationDefinition ->
//                    requiredClaimsList.add((offering.data.requiredClaims?))
                    try {
                        //Validate user vc against offering presentation definition
                        PresentationExchange.satisfiesPresentationDefinition(
                            credentialsList, presentationDefinition
                        )
                        val selectedClaims = PresentationExchange.selectCredentials(
                            credentialsList,
                            presentationDefinition
                        )
                        claimsList.add(selectedClaims)
                        matchedOfferings.add(offering)
                    } catch (e: Exception) {

                    }
                }
            }
        }
    }

    return Pair(matchedOfferings, claimsList)
}