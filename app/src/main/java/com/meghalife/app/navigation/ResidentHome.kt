package com.meghalife.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.meghalife.app.language.t
import com.meghalife.app.screens.MapScreen
import com.meghalife.app.screens.TransportScreen
import com.meghalife.app.screens.health.HealthScreen
import com.meghalife.app.screens.profile.ProfileScreen
import com.meghalife.app.screens.safety.SafetyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResidentHome() {

    /* 🌐 Root navigation (full-screen destinations) */
    val rootNavController = rememberNavController()

    /* 📱 Bottom navigation (tabs) */
    val bottomNavController = rememberNavController()

    val items = ResidentBottomNavItem.items

    /* 🔎 Observe current routes */
    val rootBackStackEntry by rootNavController.currentBackStackEntryAsState()
    val currentRootRoute = rootBackStackEntry?.destination?.route

    val bottomBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentBottomRoute = bottomBackStackEntry?.destination?.route

    Scaffold(

        /* ───── Bottom Navigation (ONLY for tab screens) ───── */
        bottomBar = {
            if (currentRootRoute == Routes.RESIDENT_HOME) {

                NavigationBar {
                    items.forEach { item ->

                        val selected = currentBottomRoute == item.route

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                bottomNavController.navigate(item.route) {
                                    popUpTo(
                                        bottomNavController.graph.startDestinationId
                                    ) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector =
                                        if (selected)
                                            item.selectedIcon
                                        else
                                            item.unselectedIcon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(t(item.labelKey))
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->

        /* 🌐 ROOT NAV HOST */
        NavHost(
            navController = rootNavController,
            startDestination = Routes.RESIDENT_HOME,
            modifier = Modifier.padding(padding)
        ) {

            /* ───── Bottom-tab container ───── */
            composable(Routes.RESIDENT_HOME) {

                NavHost(
                    navController = bottomNavController,
                    startDestination = ResidentBottomNavItem.Transport.route
                ) {

                    composable(ResidentBottomNavItem.Transport.route) {
                        TransportScreen(
                            onLiveMapClick = {
                                rootNavController.navigate(Routes.LIVE_BUS_MAP)
                            }
                        )
                    }

                    composable(ResidentBottomNavItem.Health.route) {
                        HealthScreen()
                    }

                    composable(ResidentBottomNavItem.Safety.route) {
                        SafetyScreen()
                    }

                    composable(ResidentBottomNavItem.Profile.route) {
                        ProfileScreen(
                            onSwitchMode = {
                                // 🔁 Switch back to Mode Selection
                                rootNavController.navigate(Routes.MODE_SELECTION) {
                                    popUpTo(0)
                                }
                            }
                        )
                    }
                }
            }

            /* ───── Full-screen Map (no bottom bar) ───── */
            composable(Routes.LIVE_BUS_MAP) {
                MapScreen(
                    onBack = { rootNavController.popBackStack() }
                )
            }
        }
    }
}
