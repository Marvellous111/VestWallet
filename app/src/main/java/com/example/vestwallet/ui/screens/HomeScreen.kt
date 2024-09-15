package com.example.vestwallet.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.vestwallet.R
import com.example.vestwallet.data.CountryCurrencyList
import com.example.vestwallet.data.transactionList
import com.example.vestwallet.models.Currency
import com.example.vestwallet.models.TransactionType
import com.example.vestwallet.models.TransactionTypeEnum
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HomeScreen(
    toWithdrawTransactionScreen: () -> Unit,
    toDepositTransactionScreen: () -> Unit,
    toConvertTransactionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val windowInsets = WindowInsets.systemBars
//    val density = LocalDensity.current
//    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            //.padding(top = (statusBarHeight + 50.dp))
    ) {
        HomeMergeComponents(
            toWithdrawTransactionScreen = toWithdrawTransactionScreen,
            toDepositTransactionScreen = toDepositTransactionScreen,
            toConvertTransactionScreen = toConvertTransactionScreen,
        )
//            type = type,
//            toTransactionScreen = toTransactionScreen
    }
}

@Composable
fun HomeMergeComponents(
//    type: TransactionTypeEnum,
    toWithdrawTransactionScreen: () -> Unit,
    toDepositTransactionScreen: () -> Unit,
    toConvertTransactionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(16.dp)
        //verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        MoneyCard(
            toWithdrawTransactionScreen = toWithdrawTransactionScreen,
            toConvertTransactionScreen = toConvertTransactionScreen,
            toDepositTransactionScreen = toDepositTransactionScreen
        )
        Spacer(modifier = Modifier.height(30.dp))
        RecentTransactionsList(
//            type = type,
            toConvertTransactionScreen = toConvertTransactionScreen,
            toDepositTransactionScreen = toDepositTransactionScreen,
            toWithdrawTransactionScreen = toWithdrawTransactionScreen,
            transactionList = transactionList
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.female_emoji),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.home_greeting, " Edith"),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                imageVector = Icons.Filled.NotificationsNone,
                contentDescription = stringResource(R.string.global_icon_navigation),
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// This is the main card to display amount with different buttons for interacting with the app
@Composable
fun MoneyCard(
    toWithdrawTransactionScreen: () -> Unit,
    toConvertTransactionScreen: () -> Unit,
    toDepositTransactionScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val amountNumber = 7600
    val amount = NumberFormat.getCurrencyInstance(Locale.US).format(amountNumber)
    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier.background(color = MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(25.dp)
        ) {
            Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                Image(
                    painter = painterResource(R.drawable.card_image_bottom),
                    contentDescription = "Card bottom image",
                    modifier = Modifier
                        .offset(y = 120.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CurrencyDropDown()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = amount,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CardButtons(
                        toWithdrawScreen = toWithdrawTransactionScreen,
                        toConvertionScreen = toConvertTransactionScreen,
                        toDepositScreen = toDepositTransactionScreen,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Image(
                    painter = painterResource(R.drawable.card_image_top),
                    contentDescription = "Card bottom image",
                    modifier = Modifier
                        .offset(x = 295.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyDropDown(
    modifier: Modifier = Modifier
) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var mExpanded by remember { mutableStateOf(false) }

    val listOfCurrency = CountryCurrencyList.currencyList

    var selectedText by remember { mutableStateOf(listOfCurrency[0].currencyCode) }

    val selectedCountryFlag = when (selectedText) {
        "KES" -> R.drawable.kenya
        "NGN" -> R.drawable.nigeria
        "MXN" -> R.drawable.mexico
        "USD" -> R.drawable.united_states
        "GBP" -> R.drawable.united_kingdom
        "EUR" -> R.drawable.united_kingdom
        "AUD" -> R.drawable.australia
        else -> R.drawable.usd_coin
    }
    Column(
        modifier
    ) {
        OutlinedTextField(
            singleLine = true,
            readOnly = true,
            shape = RoundedCornerShape(50.dp),
            value = selectedText,
            onValueChange = { selectedText = it },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .width(140.dp)
                .height(48.dp)
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(selectedCountryFlag),
                    contentDescription = "Country flag",
                    modifier = Modifier.size(28.dp)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = if (mExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = "Expanded icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        mExpanded = !mExpanded
                    }
                )
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            listOfCurrency.forEach{ label ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label.currencyCode,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        selectedText = label.currencyCode
                        mExpanded = false
                    },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}

@Composable
fun CardButtons(
    toConvertionScreen: () -> Unit,
    toWithdrawScreen: () -> Unit,
    toDepositScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = toDepositScreen,
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.height(42.dp)
        ) {
            Text(
                text = stringResource(R.string.home_button_deposit),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        OutlinedButton(
            onClick = toConvertionScreen,
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.height(42.dp),
        ) {
            Text(
                text = stringResource(R.string.home_button_convert),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        OutlinedButton(
            onClick = toWithdrawScreen,
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.height(42.dp)
        ) {
            Text(
                text = stringResource(R.string.home_button_withdraw),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

// This shows the recent transactions, up to 8 to 10 recent transactions
@Composable
fun RecentTransactionsList(
//    type: TransactionTypeEnum,
    toConvertTransactionScreen: () -> Unit,
    toWithdrawTransactionScreen: () -> Unit,
    toDepositTransactionScreen: () -> Unit,
    transactionList: List<TransactionType>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.home_recent),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(end = 0.dp),
                modifier = Modifier.wrapContentWidth(Alignment.End)
            ) {
                Text(
                    text = stringResource(R.string.home_see_all),
                    style = MaterialTheme.typography.labelMedium,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End,
                )
            }
        }
        //Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            items(transactionList) { transactions ->
                Spacer(modifier = Modifier.height(20.dp))
                when(transactions) {
                    is TransactionType.Convert -> ConvertTransaction(
//                        type = TransactionTypeEnum.ConvertGain,
                        toConvertTransactionScreen = toConvertTransactionScreen,
                        convert = transactions)
                    is TransactionType.Withdraw -> WithdrawTransaction(
//                        type = TransactionTypeEnum.Withdraw,
                        toWithdrawTransactionScreen = toWithdrawTransactionScreen,
                        withdraw = transactions)
                    is TransactionType.Deposit -> DepositTransaction(
//                        type = TransactionTypeEnum.Deposit,
                        toDepositTransactionScreen = toDepositTransactionScreen,
                        deposit = transactions)
                }
            }
        }
    }
}

@Composable
fun DepositTransaction(
//    type: TransactionTypeEnum = TransactionTypeEnum.Deposit,
    toDepositTransactionScreen: () -> Unit,
    deposit: TransactionType.Deposit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable { toDepositTransactionScreen() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(deposit.donorProfileImage),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = deposit.donorProfileName, //Name is dynamic here
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(R.string.home_button_deposit),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
        Text(
            text = "+ ${deposit.transactionAmount}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface
        )

    }
}


@Composable
fun WithdrawTransaction(
//    type: TransactionTypeEnum = TransactionTypeEnum.Withdraw,
    toWithdrawTransactionScreen: () -> Unit,
    withdraw: TransactionType.Withdraw,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable { toWithdrawTransactionScreen() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(withdraw.recipientProfileImage),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = withdraw.recipientProfileName, //Name is dynamic here
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(R.string.home_button_deposit),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
        Text(
            text = "+ ${withdraw.transactionAmount}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface
        )

    }
}

@Composable
fun ConvertTransaction(
//    type: TransactionTypeEnum = TransactionTypeEnum.ConvertGain,
    toConvertTransactionScreen: () -> Unit,
    convert: TransactionType.Convert,
    modifier: Modifier = Modifier
) {
    val transactionAmount: String = if (convert.transactionType == TransactionTypeEnum.ConvertGain)
        "+ ${convert.transactionAmount}"
    else "- ${convert.transactionAmount}"

    val transactionColor: Color = if (convert.transactionType == TransactionTypeEnum.ConvertGain)
        MaterialTheme.colorScheme.surface
    else MaterialTheme.colorScheme.secondary

    Row(
        modifier
            .fillMaxWidth()
            .clickable { toConvertTransactionScreen() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(convert.currentCurrencyImage),
                    contentDescription = convert.currentCurrencyName,
                    modifier = Modifier.size(21.dp)
                )
                Image(
                    painter = painterResource(convert.futureCurrencyImage),
                    contentDescription = convert.futureCurrencyName,
                    modifier = Modifier
                        .offset(x = (-1).dp)
                        .size(21.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Converted ${convert.currentCurrencyName} to ${convert.futureCurrencyName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Text(
            text = transactionAmount,
            style = MaterialTheme.typography.bodyMedium,
            color = transactionColor
        )
    }
}
