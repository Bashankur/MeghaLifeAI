package com.meghalife.app.navigation

fun ResidentBottomNavItem.routeLabel(): String =
    when (this) {
        ResidentBottomNavItem.Transport -> "Transport"
        ResidentBottomNavItem.Health -> "Health"
        ResidentBottomNavItem.Safety -> "Safety"
        ResidentBottomNavItem.Profile -> "Profile"
    }
