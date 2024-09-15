package com.example.vestwallet.ui.screens.signupscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vestwallet.R

@Composable
fun VerifyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        VerifyForm()
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up_continue_button),
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}


@Composable
fun VerifyForm(modifier: Modifier = Modifier) {
    Column(
        modifier
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
                contentDescription = stringResource(R.string.navigate_back),
                tint = MaterialTheme.colorScheme.secondary,
                modifier = modifier
                    .size(60.dp)
                    .offset(x = (-14).dp)
                    .align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.verify_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.verify_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        TextButton(
            onClick = {},
            contentPadding = PaddingValues(start = 0.dp)
        ) {
            Text(
                text = stringResource(R.string.verify_send_again),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}