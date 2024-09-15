package com.example.vestwallet.ui.screens.moresettingscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vestwallet.R

@Composable
fun MoreScreen(modifier: Modifier = Modifier) {
//    val windowInsets = WindowInsets.systemBars
//    val density = LocalDensity.current
//    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            //.padding(top = (statusBarHeight + 50.dp))
    ) {
        MoreMergeScreen()
    }
}

@Composable
fun MoreMergeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            MoreAccountAndSecurity()
            Spacer(modifier = Modifier.height(20.dp))
            MoreSupport()
            Spacer(modifier = Modifier.height(20.dp))
            MoreAppearance()
            Spacer(modifier = Modifier.height(20.dp))
            MoreActions()
        }
        Spacer(modifier = Modifier.height(125.dp))
        LogOutButton()
    }
}

@Composable
fun MoreAccountAndSecurity(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.more_account_security_label),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 15.dp, topEnd = 15.dp, bottomStart = 0.dp, bottomEnd = 0.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_account_security_verification),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(2.5.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_account_security_account_details),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(2.5.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_account_security_change_pin),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(2.5.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 15.dp, bottomEnd = 15.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_account_security_account_history),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MoreSupport(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.more_support_label),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 15.dp, topEnd = 15.dp, bottomStart = 0.dp, bottomEnd = 0.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_support_self_care),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(2.5.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_support_help),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(2.5.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 15.dp, bottomEnd = 15.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_support_contact_us),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MoreAppearance(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.more_appearance_label),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_appearance_display),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MoreActions(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.more_actions_label),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            shape = RoundedCornerShape(
                topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp
            ),
            colors =  ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.error
            ),
            contentPadding = PaddingValues(
                top = 5.dp,
                bottom = 5.dp,
                start = 16.dp,
                end = 16.dp
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.more_actions_delete_account),
                style = MaterialTheme.typography.bodyMedium,
                //color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LogOutButton(
    modifier: Modifier = Modifier
) {
    TextButton(
        shape = RoundedCornerShape(50.dp),
        colors =  ButtonDefaults.textButtonColors(
            //containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.error,
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.error),
        onClick = { /*TODO*/ },
        modifier = modifier
            .height(42.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.more_log_out),
            style = MaterialTheme.typography.labelLarge
        )
    }
}