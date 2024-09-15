package com.example.vestwallet.models

sealed class AuthRoutes(
    val route: String,
) {
    object HeroScreen : AuthRoutes("heroscreen")
    object SignInScreen : AuthRoutes("signinscreen")
    object SignUpScreen : AuthRoutes("signupscreen")
    object AddressScreen : AuthRoutes("addressscreen")
    object SelectCountryScreen : AuthRoutes("selectcountryscreen")
    object VerifyScreen : AuthRoutes("verifyscreen")
    object SetPinScreen : AuthRoutes("setpinscreen")
    object MainHomeScreen : AuthRoutes("home")
}