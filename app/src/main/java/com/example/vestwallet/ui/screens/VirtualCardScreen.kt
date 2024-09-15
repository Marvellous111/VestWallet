package com.example.vestwallet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vestwallet.R

@Composable
fun VirtualCardScreen(modifier: Modifier = Modifier) {
//    val windowInsets = WindowInsets.systemBars
//    val density = LocalDensity.current
//    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            //.padding(top = (statusBarHeight + 50.dp))
    ) {
        VirtualCardsMergeScreen()
    }
}

@Composable
fun VirtualCardsMergeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            VirtualCards(modifier = Modifier.height(550.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))
        GetVirtualCard()
    }
}

@Composable
fun VirtualCards(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.green_card),
            contentDescription = "Green Virtual Card",
            modifier = Modifier
                .offset(x = 225.dp, y = 50.dp)
                .size(height = 430.dp, width = 270.dp)
        )
        Image(
            painter = painterResource(R.drawable.purple_card),
            contentDescription = "Purple Virtual Card",
            modifier = Modifier
                .offset(x = (-25).dp, y = 50.dp)
                .size(height = 430.dp, width = 270.dp)
        )
        Image(
            painter = painterResource(R.drawable.white_card),
            contentDescription = "White Virtual Card",
            modifier = Modifier
                .offset(x = 70.dp, y = 50.dp)
                .size(height = 430.dp, width = 270.dp)
        )
    }
}

@Composable
fun GetVirtualCard(modifier: Modifier = Modifier) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.virtual_card_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color(0xFF000000)
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.virtual_card_coming_soon),
                style = MaterialTheme.typography.labelLarge,
                //color = Color(0xFF000000)
            )
        }
    }
}