package com.example.vestwallet.ui.screens.withdrawscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.vestwallet.R

@Composable
fun WithdrawBankOptionScreen(modifier: Modifier = Modifier) {
    val windowInsets = WindowInsets.systemBars
    val density = LocalDensity.current
    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.padding(top = (statusBarHeight + 50.dp))
    ) {
        WithdrawBankOptionMergeScreen()
    }
}

@Composable
fun WithdrawBankOptionMergeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            WithdrawBankOptionCountryDropDown()
            Spacer(modifier = Modifier.height(20.dp))
            WithdrawBankOptionBanksDropDown()
            Spacer(modifier = Modifier.height(20.dp))
            WithdrawBankOptionBankAccountNumberForm()
            Spacer(modifier = Modifier.height(20.dp))
            WithdrawBankOptionBankAccountNameForm()
            Spacer(modifier = Modifier.height(20.dp))
            WithdrawBankOptionAmountForm()
            Spacer(modifier = Modifier.height(20.dp))
            WithdrawBankOptionNoteForm()
            Spacer(modifier = Modifier.height(20.dp))
        }
        WithdrawBankButton()
    }
}

@Composable
fun WithdrawBankOptionCountryDropDown(modifier: Modifier = Modifier) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var mExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Nigeria") }
    val listOfCurrency = listOf("Nigeria", "United States of America", "Mexico")
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Country",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            singleLine = true,
            readOnly = true,
            shape = RoundedCornerShape(5.dp),
            value = selectedText,
            onValueChange = { selectedText = it },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = Color.Gray,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            ),
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
                .background(color = MaterialTheme.colorScheme.tertiary)
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            listOfCurrency.forEach{ label ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        selectedText = label
                        mExpanded = false
                    },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiary)
                )
            }
        }
    }
}

@Composable
fun WithdrawBankOptionBanksDropDown(modifier: Modifier = Modifier) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var mExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    val listOfCurrency = listOf("Kuda Microfinance Bank", "Opay", "Access Bank", "Zenith Bank")
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Bank Name",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            singleLine = true,
            readOnly = true,
            shape = RoundedCornerShape(5.dp),
            value = selectedText,
            onValueChange = { selectedText = it },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = Color.Gray,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            ),
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
                .background(color = MaterialTheme.colorScheme.tertiary)
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            listOfCurrency.forEach{ label ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        selectedText = label
                        mExpanded = false
                    },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiary)
                )
            }
        }
    }
}

@Composable
fun WithdrawBankOptionBankAccountNumberForm(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Account number",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            singleLine = true,
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            placeholder = {
                Text(
                    text = "0123456789",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun WithdrawBankOptionBankAccountNameForm(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Account Name",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            readOnly = true,
            singleLine = true,
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun WithdrawBankOptionAmountForm(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Amount",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            singleLine = true,
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            placeholder = {
                Text(
                    text = "0123456789",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun WithdrawBankOptionNoteForm(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Note",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            singleLine = true,
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            placeholder = {
                Text(
                    text = "Note for payment",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun WithdrawBankButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.withdraw_button),
            style = MaterialTheme.typography.titleMedium
        )
    }
}