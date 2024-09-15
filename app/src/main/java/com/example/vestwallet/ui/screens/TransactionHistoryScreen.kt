package com.example.vestwallet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.inputFieldColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vestwallet.R
import com.example.vestwallet.data.transactionMap
import com.example.vestwallet.models.TransactionType

@Composable
fun TransactionHistoryScreen(modifier: Modifier = Modifier) {
//    val windowInsets = WindowInsets.systemBars
//    val density = LocalDensity.current
//    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            //.padding(top = (statusBarHeight + 50.dp))
    ) {
        TransactionHistoryMergeScreen()
    }
}

@Composable
fun TransactionHistoryMergeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        HistoryText()
        Spacer(modifier = Modifier.height(40.dp))
        HistoryList(transactionMap = transactionMap)
    }
}

@Composable
fun HistoryText(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(top = 10.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.transaction_history_text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Calendar Filter Icon",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
        HistorySearchBar(onQueryChange={})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorySearchBar(
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isSearchActive = false
    var searchQuery = ""
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(
                text = stringResource(R.string.transaction_search_text),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = if (isSearchActive && searchQuery.isNotEmpty()) {
            {
                IconButton(
                    onClick = {
                        searchQuery = ""
                        onQueryChange("")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(R.string.transaction_search_close),
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        } else {
            null
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            inputFieldColors = inputFieldColors(
                unfocusedTextColor = Color.Gray,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedLeadingIconColor = Color.Gray,
                focusedLeadingIconColor = MaterialTheme.colorScheme.secondary
            )
        ),
        modifier = modifier
            .offset(y = (-20).dp)
            .height(52.dp)
            .fillMaxWidth()
    ) {

    }
}

@Composable
fun HistoryList(transactionMap: Map<String, List<TransactionType>>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier
    ) {
        transactionMap.forEach { (transactionDate, transactionList) ->
            item {
                TransactionDate(transactionDate)
            }
            items(transactionList) { transactions ->
                Spacer(modifier = Modifier.height(20.dp))
                when(transactions) {
                    is TransactionType.Convert -> ConvertTransaction(toConvertTransactionScreen = {}, convert = transactions)
                    is TransactionType.Withdraw -> WithdrawTransaction(toWithdrawTransactionScreen = {}, withdraw = transactions)
                    is TransactionType.Deposit -> DepositTransaction(toDepositTransactionScreen = {}, deposit = transactions)
                }
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun TransactionDate(transactionDate: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = transactionDate,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )
    }
}