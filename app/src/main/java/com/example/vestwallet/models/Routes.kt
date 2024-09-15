package com.example.vestwallet.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(
    val route: String,
    val routeIcon: ImageVector?,
    val routeLabel: String?
) {
    object Home : Routes("home", Icons.Outlined.Home, "Home")
    object Transaction : Routes("transaction", Icons.AutoMirrored.Filled.CompareArrows, "Transaction")
    object Cards : Routes("card", Icons.Outlined.CreditCard, "Cards")
    object More : Routes("more", Icons.Outlined.MoreHoriz, "More")

    object TransactionReciept : Routes("reciept", null, null)
}