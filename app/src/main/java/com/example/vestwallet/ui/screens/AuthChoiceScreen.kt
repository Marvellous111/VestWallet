package com.example.vestwallet.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vestwallet.R

@Composable
fun AuthChoiceScreen(
    signInOnClick:() -> Unit,
    signUpOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box (
            modifier = Modifier
                //.padding(16.dp)
                .fillMaxHeight(0.6F),
        ) {
            //image and text
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.bitcoin),
                contentDescription = "Bitcoin",
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = 140.dp, y = 105.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.etherium),
                contentDescription = "Ethereum",
                modifier = Modifier
                    .size(175.dp)
                    .offset(x = 87.dp, y = 478.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.usd_coin),
                contentDescription = "USD coin",
                modifier = Modifier
                    .size(110.dp)
                    .offset(x = (8).dp, y = 310.dp)
            )
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .offset(x = 16.dp, y = 82.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.main_image),
                contentDescription = "Central Image",
                modifier = Modifier
                    .size(350.dp)
                    .offset(x = 120.dp, y = 230.dp)
            )
        }

        // Text and Buttons
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.auth_choice_main_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineLarge,
                lineHeight = 38.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.auth_choice_sub_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium,
                //lineHeight = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SignInSignUpButton(signinonclick = signInOnClick, signuponclick = signUpOnClick)
        }
    }
}


@Composable
fun SignInSignUpButton(signinonclick:() -> Unit, signuponclick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Button(
            onClick = signinonclick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(42.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                stringResource(R.string.auth_choice_signin),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        TextButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = signuponclick
        ) {
            Text(
                stringResource(id = R.string.auth_choice_create),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
