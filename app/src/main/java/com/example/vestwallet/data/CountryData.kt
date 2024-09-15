package com.example.vestwallet.data

import com.example.vestwallet.R
import com.example.vestwallet.models.Country

var countryList: List<Country> = listOf<Country>(
    Country(
        countryName = R.string.select_country_australia,
        countryFlag = R.drawable.australia
    ),
    Country(
        countryName = R.string.select_country_ghana,
        countryFlag = R.drawable.australia
    ),
    Country(
        countryName = R.string.select_country_kenya,
        countryFlag = R.drawable.kenya
    ),
    Country(
        countryName = R.string.select_country_mexico,
        countryFlag = R.drawable.mexico
    ),
    Country(
        countryName = R.string.select_country_nigeria,
        countryFlag = R.drawable.nigeria
    ),
    Country(
        countryName = R.string.select_country_united_kingdom,
        countryFlag = R.drawable.united_kingdom
    ),
    Country(
        countryName = R.string.select_country_united_states_of_america,
        countryFlag = R.drawable.united_states
    ),
)