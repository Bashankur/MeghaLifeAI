package com.meghalife.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ResidentBottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val labelKey: String
) {

    object Transport : ResidentBottomNavItem(
        route = Routes.RESIDENT_TRANSPORT,
        selectedIcon = Icons.Filled.DirectionsBus,
        unselectedIcon = Icons.Outlined.DirectionsBus,
        labelKey = "Transport"
    )

    object Health : ResidentBottomNavItem(
        route = Routes.RESIDENT_HEALTH,
        selectedIcon = Icons.Filled.LocalHospital,
        unselectedIcon = Icons.Outlined.LocalHospital,
        labelKey = "Health"
    )

    object Safety : ResidentBottomNavItem(
        route = Routes.RESIDENT_SAFETY,
        selectedIcon = Icons.Filled.Security,
        unselectedIcon = Icons.Outlined.Security,
        labelKey = "Safety"
    )

    object Profile : ResidentBottomNavItem(
        route = Routes.RESIDENT_PROFILE,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        labelKey = "Profile"
    )

    companion object {
        val items = listOf(
            Transport,
            Health,
            Safety,
            Profile
        )
    }
}
