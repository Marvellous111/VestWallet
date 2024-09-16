package com.example.vestwallet.ui.screens.signupscreens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vestwallet.R
import com.example.vestwallet.data.countryList
import com.example.vestwallet.models.AuthState
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.ui.viewmodel.AuthViewModel


@Composable
fun SelectCountryScreen(
    viewModel: AuthViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    addressOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            SignUpTopAppBar(
                onClick = onNavigateBack,
                modifier = modifier.statusBarsPadding()
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        SelectCountryMergeScreen(
            viewModel = viewModel,
            addressOnClick = addressOnClick,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SelectCountryMergeScreen(viewModel: AuthViewModel, addressOnClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(16.dp)
            .padding(top = 50.dp)
    ) {
        SelectCountryChoiceForm(
            viewModel = viewModel,
            onClick = addressOnClick,
        )
    }
}

@Composable
fun SelectCountryChoiceForm(viewModel: AuthViewModel, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.select_country_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.select_country_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(40.dp))
        CountryFormButtons(
            viewModel = viewModel,
            userDetails = UserDetails(),
            onClick = onClick
        )
    }
}

@Composable
fun CountryFormButtons(
    viewModel: AuthViewModel,
    userDetails: UserDetails,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewAuthState by viewModel.authState.collectAsState()

    LaunchedEffect(viewAuthState) {
        when (viewAuthState) {
            is AuthState.Authenticated -> onClick()
            is AuthState.Error -> {}
            else -> {}
        }
    }
    LazyColumn {
        items(countryList) {
            OutlinedButton(
                onClick = {
                    val country = it
                    var countryCode = when (country.countryName) {
                        R.string.select_country_australia -> "AUD"
                        R.string.select_country_kenya -> "KES"
                        R.string.select_country_mexico -> "MXN"
                        R.string.select_country_nigeria -> "NGN"
                        R.string.select_country_ghana -> "GHS"
                        R.string.select_country_united_kingdom -> "GBP"
                        R.string.select_country_united_states_of_america -> "USA"
                        else -> "EUR"
                    }
                    var countryName = when (country.countryName) {
                        R.string.select_country_australia -> "America"
                        R.string.select_country_kenya -> "Kenya"
                        R.string.select_country_mexico -> "Mexico"
                        R.string.select_country_nigeria -> "Nigeria"
                        R.string.select_country_ghana -> "Ghana"
                        R.string.select_country_united_kingdom -> "United Kingdom"
                        R.string.select_country_united_states_of_america -> "United States of America"
                        else -> "European Country"
                    }
                    viewModel.updateCountryPage(
                        countryName = countryName,
                        countryCode = countryCode
                    )
                    Log.d("userDetailsEmail", viewModel.userDetailsState.email)
                    viewModel.updateAuthState(AuthState.Authenticated("email"))
                },
                shape = RoundedCornerShape(5.dp),
                modifier = modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 5.dp,
                    end = 20.dp,
                    bottom = 5.dp,
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Image(
                        painter = painterResource(it.countryFlag),
                        contentDescription = stringResource(it.countryName),
                        modifier = Modifier.size(21.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = stringResource(it.countryName),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}