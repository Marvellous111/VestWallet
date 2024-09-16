package com.example.vestwallet.ui.screens.convertscreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vestwallet.R
import com.example.vestwallet.data.CountryCurrencyList
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.models.did.DidClass
import com.example.vestwallet.models.pfis.ConversionStep
import com.example.vestwallet.ui.theme.Poppins
import com.example.vestwallet.ui.theme.VestWalletTheme
import com.example.vestwallet.ui.viewmodel.AppUserViewModel
import com.example.vestwallet.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tbdex.sdk.protocol.models.Quote

@Composable
fun ConvertScreen(
    viewModel: AppUserViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val windowInsets = WindowInsets.systemBars
    val density = LocalDensity.current
    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        ConvertMergeScreen(
            viewModel = viewModel,
            didClass = DidClass(),
            userDetails = UserDetails()
        )
    }
}


@Composable
fun ConvertMergeScreen(
    viewModel: AppUserViewModel,
    didClass: DidClass,
    userDetails: UserDetails,
    modifier: Modifier = Modifier
) {
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    // Input currency code and amount
    var inputCurrencyCode by remember { mutableStateOf("USD") }
    var inputAmount by remember { mutableStateOf("0.00") }

    var quoteAndArray: Triple<MutableList<Quote>, MutableList<Double>, List<ConversionStep>>
    var closingMessage: Pair<Boolean?, String?>

    val conversionFee = if (inputAmount.toDouble() in 3.00..10.00) {
        (inputAmount.toDouble() * 0.1).toString()
    }else if (inputAmount.toDouble() in 1.00..3.00 ) {
        (0.1).toString()
    }else if (inputAmount.toDouble() == 0.00) {
        "0.00"
    } else {
        (inputAmount.toDouble() * 0.03).toString()
    }

    var convertedAmount = (inputAmount.toDouble() - conversionFee.toDouble()).toString()

    var currencyQuote = "0.00"

    // Output currency code
    var outputCurrencyCode by remember { mutableStateOf("KES") }
    var outputCurrencyAmount = ""

    val countryName by remember { derivedStateOf {
        when (inputCurrencyCode) {
            "KES" -> "Kenyan Shilling"
            "NGN" -> "Nigerian Naira"
            "MXN" -> "Mexican Pesos"
            "USD" -> "United States Dollar"
            "GBP" -> "GBP"
            "EUR" -> "Euros"
            "AUD" -> "Australian Dollar"
            "BTC" -> "Bitcoin"
            "USDC" -> "USD Coin"
            else -> "Ghaniain cedis"
        }
    } }

    val countryFlag by remember { derivedStateOf {
        when (inputCurrencyCode) {
            "KES" -> R.drawable.kenya
            "NGN" -> R.drawable.nigeria
            "MXN" -> R.drawable.mexico
            "USD" -> R.drawable.united_states
            "GBP" -> R.drawable.united_kingdom
            "EUR" -> R.drawable.united_kingdom
            "AUD" -> R.drawable.australia
            "BTC" -> R.drawable.bitcoin
            "USDC" -> R.drawable.usd_coin
            else -> R.drawable.etherium
        }
    } }
    val countryAmount by remember { derivedStateOf {
        val currency = userDetails.currencies.find { it.code.equals(inputCurrencyCode, ignoreCase = true) }
        currency?.value ?: "Currency not found"
    } }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            ConvertMoneyCard(
                countryName = countryName,
                countryFlag = countryFlag,
                countryAmount = countryAmount.toString()
            )
            Spacer(modifier = Modifier.height(20.dp))
            ConvertCard(
                inputCurrencyCode = inputCurrencyCode,
                inputAmount = inputAmount,
                outputCurrencyCode = outputCurrencyCode,
                onInputCurrencyChange = { newCurrencyCode ->
                    inputCurrencyCode = newCurrencyCode
                },
                onInputAmountChange = { newAmount ->
                    inputAmount = newAmount
                },
                outputCurrencyAmount = outputCurrencyAmount,
                onOutputCurrencyChange = { newCurrencyCode ->
                    outputCurrencyCode = newCurrencyCode
                },
                conversionFee = conversionFee,
                convertedAmount = convertedAmount,
                currencyQuote = currencyQuote
            )
        }
        ConvertButton(
            onConvertButtonClick = {
                Log.d("convertButtonPressed", "Convert started")
                coroutineScope.launch {
//                    val mockUserDid = createUserDid()
//
//                    var mockUserDidDocument = getUserDidDocument(mockUserDid.second)
//
//                    val mockDidClass = DidClass(userBearerDid = mockUserDid.first)

                    quoteAndArray = viewModel.getCurrencyQuotes(
                        inputCurrencyCode = inputCurrencyCode,
                        conversionAmount = convertedAmount,
                        outputCurrencyCode = outputCurrencyCode,
                    )
                    closingMessage = didClass.userBearerDid?.let {
                        viewModel.convertCurrency(
                            userBearerDid = it,
                            quotes = quoteAndArray.first,
                            userDid = userDetails.userDid
                        )
                    }!!
                    withContext(Dispatchers.Main) {
                        outputCurrencyAmount = quoteAndArray.first.last().data.payout.amount
                        closingMessage.second?.let { Log.d("ConvertScreen", it) }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PFIBottomSheet(
    closeMessage: String,
    conversions: List<ConversionStep>,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Optional, it allows or prevents partial expansion
    )
    val scope = rememberCoroutineScope()

    // State to control when the sheet is shown
    var showSheet by remember { mutableStateOf(false) }

    // Button to trigger the Bottom Sheet
    Button(onClick = { showSheet = true }) {
        Text("Show Bottom Sheet")
    }

    // ModalBottomSheet Layout
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }, // Handle dismiss action
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                conversions.forEach { it ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.pfi.pfiName,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = "${it.fromCurrency} to ${it.toCurrency}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(
                            text = closeMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                Text(
                    text = "This is a bottom sheet",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    // Close the sheet
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showSheet = false
                        }
                    }
                }) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun ConvertMoneyCard(
    countryName: String,
    countryFlag: Int,
    countryAmount: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(countryFlag),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = countryName,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "$${countryAmount}",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun ConvertCard(
    inputCurrencyCode: String,
    inputAmount: String,
    onInputAmountChange:(String) -> Unit,
    onInputCurrencyChange: (String) -> Unit,
    outputCurrencyCode: String,
    outputCurrencyAmount: String,
    onOutputCurrencyChange: (String) -> Unit,
    conversionFee: String,
    convertedAmount: String,
    currencyQuote: String,
    modifier: Modifier = Modifier
) {

    //var inputCurrency by remember { mutableStateOf("") }
    //var outputCurrency by remember { mutableStateOf("") }

    Card(
        modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(R.string.convert_amount_to_convert),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                // Fix text input design
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ConvertCurrencyDropDown(
                        selectedCurrencyCode = inputCurrencyCode,
                        onCurrencyChange = onInputCurrencyChange
                    )
                    TextField(
                        value = inputAmount,
                        onValueChange = onInputAmountChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = Color.Gray,
                            focusedTextColor = MaterialTheme.colorScheme.secondary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                            cursorColor = MaterialTheme.colorScheme.secondary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedPlaceholderColor = Color.Gray
                        ),
                        placeholder = {
                            Text(
                                text = "$0.00",
                                style = TextStyle(
                                    letterSpacing = (-1).sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.End
                                ),
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        maxLines = 1,
                        singleLine = true,
                        textStyle = TextStyle(
                            letterSpacing = (-1).sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = 17.sp,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.width(80.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.convert_conversion_fee),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "-$${conversionFee}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.convert_converted_amount),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "$${convertedAmount}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.convert_currency_quote),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = currencyQuote,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(R.string.convert_amount_to_recieve),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                // Fix text input design
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ConvertCurrencyDropDownOutput(
                        selectedCurrencyCode = outputCurrencyCode,
                        onCurrencyChange = onOutputCurrencyChange
                    )
                    TextField(
                        value = outputCurrencyAmount,
                        onValueChange = {  },
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = Color.Gray,
                            focusedTextColor = MaterialTheme.colorScheme.secondary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                            cursorColor = MaterialTheme.colorScheme.secondary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedPlaceholderColor = Color.Gray
                        ),
                        placeholder = {
                            Text(
                                text = "$0.00",
                                style = TextStyle(
                                    letterSpacing = (-1).sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.End
                                ),
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        maxLines = 1,
                        readOnly = true,
                        singleLine = true,
                        textStyle = TextStyle(
                            letterSpacing = (-1).sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = 17.sp,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.width(80.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertCurrencyDropDown(
    selectedCurrencyCode: String,
    onCurrencyChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var mExpanded by remember { mutableStateOf(false) }

    val listOfCurrency = CountryCurrencyList.currencyList

    //var selectedText by remember { mutableStateOf(listOfCurrency[0].currencyCode) }

    val selectedCountryFlag = when (selectedCurrencyCode) {
        "KES" -> R.drawable.kenya
        "NGN" -> R.drawable.nigeria
        "MXN" -> R.drawable.mexico
        "USD" -> R.drawable.united_states
        "GBP" -> R.drawable.united_kingdom
        "EUR" -> R.drawable.united_kingdom
        "AUD" -> R.drawable.australia
        "BTC" -> R.drawable.bitcoin
        else -> R.drawable.usd_coin
    }
    Column(
        modifier
    ) {
        OutlinedTextField(
            singleLine = true,
            readOnly = true,
            shape = RoundedCornerShape(50.dp),
            value = selectedCurrencyCode,
            onValueChange = {  },
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
                        onCurrencyChange(label.currencyCode)
                        mExpanded = false
                    },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertCurrencyDropDownOutput(
    selectedCurrencyCode: String,
    onCurrencyChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var mExpanded by remember { mutableStateOf(false) }

    val listOfCurrency = CountryCurrencyList.currencyList

    //val selectedText by remember { mutableStateOf(listOfCurrency[0].currencyCode) }

    val selectedCountryFlag = when (selectedCurrencyCode) {
        "KES" -> R.drawable.kenya
        "NGN" -> R.drawable.nigeria
        "MXN" -> R.drawable.mexico
        "USD" -> R.drawable.united_states
        "GBP" -> R.drawable.united_kingdom
        "EUR" -> R.drawable.united_kingdom
        "AUD" -> R.drawable.australia
        "BTC" -> R.drawable.bitcoin
        else -> R.drawable.usd_coin
    }
    Column(
        modifier
    ) {
        OutlinedTextField(
            singleLine = true,
            readOnly = true,
            shape = RoundedCornerShape(50.dp),
            value = selectedCurrencyCode,
            onValueChange = {  },
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
                        onCurrencyChange(label.currencyCode)
                        mExpanded = false
                    },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}


@Composable
fun ConvertButton(
    onConvertButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onConvertButtonClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.convert_button_text),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun ConvertDoneScreenPreview() {
    VestWalletTheme {
        ConvertScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}