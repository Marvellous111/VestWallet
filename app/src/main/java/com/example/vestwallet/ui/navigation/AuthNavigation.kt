package com.example.vestwallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vestwallet.models.AuthRoutes
import com.example.vestwallet.ui.MainScreen
import com.example.vestwallet.ui.screens.AuthChoiceScreen
import com.example.vestwallet.ui.screens.SignInScreen
import com.example.vestwallet.ui.screens.signupscreens.AddressScreen
import com.example.vestwallet.ui.screens.signupscreens.SelectCountryScreen
import com.example.vestwallet.ui.screens.signupscreens.SetPinScreen
import com.example.vestwallet.ui.screens.signupscreens.SignUpScreen
import com.example.vestwallet.ui.screens.signupscreens.VerifyScreen
import com.example.vestwallet.ui.viewmodel.AuthViewModel

@Composable
fun AuthNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = AuthRoutes.HeroScreen.route,
        modifier = modifier
    ) {
        composable(AuthRoutes.HeroScreen.route) {
            AuthChoiceScreen(
                signInOnClick = {
                    navController.navigate(AuthRoutes.SignInScreen.route)
                },
                signUpOnClick = { navController.navigate(AuthRoutes.SignUpScreen.route) }
            )
        }
        composable(AuthRoutes.SignInScreen.route) {
            SignInScreen(
                toMainPage = {
                    navController.navigate(AuthRoutes.MainHomeScreen.route) {
                        popUpTo(AuthRoutes.HeroScreen.route) { inclusive = true }
                    }
                },
                toSignUpPage = {
                    navController.navigate(AuthRoutes.SignUpScreen.route)
                }
            )
        }
        composable(AuthRoutes.MainHomeScreen.route) { MainScreen() }
        composable(AuthRoutes.SignUpScreen.route) {
            SignUpScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                selectCountryOnClick = {
                    navController.navigate(AuthRoutes.SelectCountryScreen.route)
                }
            )
        }
        composable(AuthRoutes.SelectCountryScreen.route) {
            SelectCountryScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                addressOnClick = {
                    navController.navigate(AuthRoutes.AddressScreen.route)
                }
            )
        }
        composable(AuthRoutes.AddressScreen.route) {
            AddressScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                mainPageOnClick = {
                    navController.navigate(AuthRoutes.MainHomeScreen.route)
                }
            )
        }
        composable(AuthRoutes.VerifyScreen.route) {
            VerifyScreen()
        }
        composable(AuthRoutes.SetPinScreen.route) {
            SetPinScreen()
        }
    }
}