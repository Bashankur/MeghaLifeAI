package com.meghalife.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.meghalife.app.screens.tourist.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristNavHost(
    onExitToModeSelection: () -> Unit = {}
) {

    /* 📱 Tourist navigation controller */
    val navController = rememberNavController()

    /* 🧭 Bottom navigation items */
    val items = listOf(
        TouristBottomNavItem.Explore,
        TouristBottomNavItem.Plan,
        TouristBottomNavItem.Travel,
        TouristBottomNavItem.Safety,
        TouristBottomNavItem.Profile
    )

    /* 🔎 Observe current route */
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(

        /* ───── Bottom Navigation ───── */
        bottomBar = {
            NavigationBar {
                items.forEach { item ->

                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }

    ) { padding ->

        /* 🌍 Tourist Nav Host */
        NavHost(
            navController = navController,
            startDestination = Routes.TOURIST_EXPLORE,
            modifier = Modifier.padding(padding)
        ) {

            composable(Routes.TOURIST_EXPLORE) {
                ExploreMapScreen()
            }

            composable(Routes.TOURIST_PLAN) {
                TravelAdvisoryScreen()
            }

            composable(Routes.TOURIST_TRAVEL) {
                TravelScreen()
            }

            composable(Routes.TOURIST_SAFETY) {
                TouristSafetyScreen()
            }

            composable(Routes.TOURIST_PROFILE) {
                TouristProfileScreen(
                    onSwitchMode = onExitToModeSelection
                )
            }
        }
    }
}
