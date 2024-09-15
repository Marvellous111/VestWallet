package com.example.vestwallet.ui.screens.withdrawscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WithdrawUSDCOptionScreen(modifier: Modifier = Modifier) {
    val windowInsets = WindowInsets.systemBars
    val density = LocalDensity.current
    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.padding(top = (statusBarHeight + 50.dp))
    ) {
        WithdrawUSDCOptionMergeScreen()
    }
}

@Composable
fun WithdrawUSDCOptionMergeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(16.dp)
    ) {
        Text(
            text = "This service is not yet available, however, we are working to get it ready ASAP",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start
        )
    }
}


// For the purpose of the hackathon we won't work on the USDC withdrawal function as it is redundant
/*
@Composable
fun WithdrawUSDCOptionForm(modifier: Modifier = Modifier) {}

@Composable
fun WithdrawUSDCOptionAddressForm(modifier: Modifier = Modifier) {}

@Composable
fun WithdrawUSDCOptionAmountForm(modifier: Modifier = Modifier) {}

@Composable
fun WithdrawUSDCOptionPFIToUseForm(modifier: Modifier = Modifier) {}
*/