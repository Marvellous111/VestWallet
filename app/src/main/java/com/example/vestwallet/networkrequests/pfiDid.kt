package com.example.vestwallet.networkrequests

import web5.sdk.dids.methods.dht.DidDht
import web5.sdk.dids.methods.dht.CreateDidDhtOptions
import web5.sdk.crypto.InMemoryKeyManager
import web5.sdk.dids.did.BearerDid
import web5.sdk.dids.didcore.DidDocument

//val customerDid = DidDht.create()

//val customerDidDht = DidDht.create(InMemoryKeyManager(), CreateDidDhtOptions(publish = true))
//
//val portableDid = DidDht.resolve(customerDidDht.uri)



fun createUserDid(): Pair<BearerDid, String> {
    val userDidDht = DidDht.create(InMemoryKeyManager(), CreateDidDhtOptions(publish = true))
    val userDid = userDidDht.uri

    return Pair(userDidDht, userDid)
}

fun getUserDidDocument(userDid: String): DidDocument? {
    val portableDid = DidDht.resolve(userDid)
    val userDidDocument = portableDid.didDocument

    return userDidDocument
}

