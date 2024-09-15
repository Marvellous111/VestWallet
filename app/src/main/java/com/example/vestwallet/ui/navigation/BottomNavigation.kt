package com.example.vestwallet.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vestwallet.models.Routes

@Composable
fun BottomNavigation(
    navList: List<Routes>,
    navController: NavHostController,
    //modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        navList.forEach { route ->
            currentDestination?.hierarchy?.let { it ->
                val isSelected = it.any {it.route == route.route}
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(route.route)
//                        navController.navigate(route.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
                    },
                    icon = {
                        route.routeIcon?.let { it1 ->
                            Icon(
                                it1,
                                contentDescription=null,
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    label = {
                        route.routeLabel?.let { it1 ->
                            Text(
                                text = it1,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.background,
                    ),
                    //modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}