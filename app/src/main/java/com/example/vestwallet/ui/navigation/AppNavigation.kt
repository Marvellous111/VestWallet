package com.example.vestwallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vestwallet.models.Routes
import com.example.vestwallet.ui.screens.HomeScreen
import com.example.vestwallet.ui.screens.TransactionHistoryScreen
import com.example.vestwallet.ui.screens.VirtualCardScreen
import com.example.vestwallet.ui.screens.convertscreens.ConvertDoneScreen
import com.example.vestwallet.ui.screens.convertscreens.ConvertScreen
import com.example.vestwallet.ui.screens.moresettingscreens.MoreScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                toWithdrawTransactionScreen = {
                    navController.navigate(Routes.TransactionReciept.route)
                },
                toDepositTransactionScreen = {
                    navController.navigate(Routes.TransactionReciept.route)
                },
                toConvertTransactionScreen = {
                    navController.navigate(Routes.TransactionReciept.route)
                },
            )
        }
        composable(Routes.Transaction.route) {
            TransactionHistoryScreen()
        }
        composable(Routes.Cards.route) {
            VirtualCardScreen()
        }
        composable(Routes.More.route) {
            MoreScreen()
        }
        composable(Routes.TransactionReciept.route) {
            ConvertScreen()
        }
    }
}