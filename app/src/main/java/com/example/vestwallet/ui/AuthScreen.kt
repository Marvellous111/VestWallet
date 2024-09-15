package com.example.vestwallet.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vestwallet.ui.navigation.AuthNavigation

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    Surface {
        AuthNavigation(
            navController,
            modifier = modifier
        )
    }
}