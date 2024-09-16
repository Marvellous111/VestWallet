package com.example.vestwallet.models.did

import com.google.gson.Gson
import web5.sdk.dids.did.BearerDid
import web5.sdk.dids.didcore.DidDocument

class DidClass(
    var userBearerDid: BearerDid? = null
) {
    fun changetoString(bearerDid: BearerDid): String {

        val gson = Gson()
        val jsonString = gson.toJson(bearerDid)
        return jsonString
    }

    fun changetoBearerDid(jsonString: String): BearerDid {
        val gson = Gson()
        val convertedBearerDid = gson.fromJson<BearerDid>(jsonString, BearerDid::class.java)
        return convertedBearerDid
    }
}
