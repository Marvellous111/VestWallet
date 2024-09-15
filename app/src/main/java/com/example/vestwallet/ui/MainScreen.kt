package com.example.vestwallet.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vestwallet.data.mainNavigationList
import com.example.vestwallet.ui.navigation.AppNavigation
import com.example.vestwallet.ui.navigation.BottomNavigation
import com.example.vestwallet.ui.screens.HomeTopBar

//@SuppressLint("The expression is unused")
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            HomeTopBar(
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            BottomNavigation(
                navList = mainNavigationList,
                navController = navController,
            )
        },
    ) { innerPadding ->
        AppNavigation(
            navController,
            modifier = modifier
                .padding(innerPadding)
        )
    }
}