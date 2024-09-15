package com.example.vestwallet.networkrequests

import com.example.vestwallet.models.UserDetails
import web5.sdk.credentials.VerifiableCredential
import java.time.Instant
import java.util.Date

data class UserCredential(
    val position: String,
    val startDate: String,
    val employmentStatus: String
)
//
//fun createVC(userDetails: UserDetails): VerifiableCredential {
//    val vc = VerifiableCredential.create(
//        type = "EmploymentCredential",
//        issuer = employerDid.uri,
//        subject = userDetails.userDid,
//        expirationDate = Date.from(Instant.parse("2025-09-30T12:34:56Z")),
//        data = UserCredential(
//            position = "Software Developer",
//            startDate = "2021-04-01T12:34:56Z",
//            employmentStatus = "Contractor"
//        )
//    )
//
//    return vc
//}