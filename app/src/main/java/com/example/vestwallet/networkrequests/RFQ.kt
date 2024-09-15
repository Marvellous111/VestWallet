package com.example.vestwallet.networkrequests

import tbdex.sdk.httpclient.TbdexHttpClient
import tbdex.sdk.protocol.models.CreateRfqData
import tbdex.sdk.protocol.models.CreateSelectedPayinMethod
import tbdex.sdk.protocol.models.CreateSelectedPayoutMethod
import tbdex.sdk.protocol.models.Offering
import tbdex.sdk.protocol.models.Rfq
import web5.sdk.credentials.VerifiableCredential
import web5.sdk.dids.did.BearerDid

fun requestForQuote(
    pfiDid: String,
    userDid: String,
    offeringId: String,
    offeringFromCurrency: String,
    offeringToCurrency: String,
    claims: List<String>,
    amountToGive: String,
    fromPaymentBankAccount: String,
    toPaymentBankAccount: String
): Rfq {
    val rfq = Rfq.create(
        to = pfiDid,
        from = userDid,
        rfqData = CreateRfqData(
            offeringId = offeringId,
            payin = CreateSelectedPayinMethod(
                kind = "BANK_ACCOUNT",
                paymentDetails = mapOf(offeringFromCurrency to fromPaymentBankAccount),
                amount = amountToGive
            ),
            payout = CreateSelectedPayoutMethod(
                kind = "BANK_ACCOUNT",
                paymentDetails = mapOf(offeringToCurrency to toPaymentBankAccount)
            ),
            claims = claims
        )
    )

    return rfq
}


/*
For the sake of documentation and understanding args
offering is the offering gotten from getoffering matching requests
* */
fun verifyRequestForQuote(rfq: Rfq, offering: Offering): String {
    var verified: String
    try{
        rfq.verifyOfferingRequirements(offering)
        verified = "Successful"
    }catch (e: Exception){
        // handle failed verification
        verified = "${e.message}"
    }
    return verified
}


/*
* For docs and understanding args, userBearerDid is the portableDid saved in DidClass
* and as string in database
* */
fun signRequestForQuote(rfq:Rfq, userBearerDid: BearerDid): Rfq {
    rfq.sign(userBearerDid)

    return rfq
}

fun sendRequestForQuoteToPfi(rfq:Rfq) {
    TbdexHttpClient.createExchange(rfq)
}