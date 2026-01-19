package com.meghalife.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TouristBottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Explore : TouristBottomNavItem(
        Routes.TOURIST_EXPLORE,
        "Explore",
        Icons.Default.Map
    )

    object Plan : TouristBottomNavItem(
        Routes.TOURIST_PLAN,
        "Plan",
        Icons.Default.Event
    )

    object Travel : TouristBottomNavItem(
        Routes.TOURIST_TRAVEL,
        "Travel",
        Icons.Default.DirectionsBus
    )

    object Safety : TouristBottomNavItem(
        Routes.TOURIST_SAFETY,
        "Safety",
        Icons.Default.Warning
    )

    object Profile : TouristBottomNavItem(
        Routes.TOURIST_PROFILE,
        "Profile",
        Icons.Default.Person
    )
}
