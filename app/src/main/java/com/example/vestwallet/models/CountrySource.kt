package com.example.vestwallet.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Country(
    @StringRes val countryName: Int,
    @DrawableRes val countryFlag: Int
)
