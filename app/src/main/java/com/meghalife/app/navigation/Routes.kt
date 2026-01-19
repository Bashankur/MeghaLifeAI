package com.meghalife.app.navigation

object Routes {

    /* ───────── ENTRY FLOW ───────── */
    const val LANGUAGE_SELECTION = "language_selection"
    const val SPLASH = "splash"
    const val MODE_SELECTION = "mode_selection"

    /* ───────── ROOT DESTINATIONS ───────── */
    const val RESIDENT_HOME = "resident_home"
    const val TOURIST_HOME = "tourist_home"
    const val DRIVER = "driver"

    /* ───────── FULL SCREEN (NO BOTTOM NAV) ───────── */
    const val LIVE_BUS_MAP = "live_bus_map"

    /* ───────── RESIDENT BOTTOM NAV (LOCAL TO ResidentHome) ───────── */
    const val RESIDENT_TRANSPORT = "resident_transport"
    const val RESIDENT_HEALTH = "resident_health"
    const val RESIDENT_SAFETY = "resident_safety"
    const val RESIDENT_PROFILE = "resident_profile"

    /* ───────── TOURIST BOTTOM NAV (LOCAL TO TouristHome) ───────── */
    const val TOURIST_EXPLORE = "tourist_explore"
    const val TOURIST_PLAN = "tourist_plan"
    const val TOURIST_TRAVEL = "tourist_travel"
    const val TOURIST_SAFETY = "tourist_safety"
    const val TOURIST_PROFILE = "tourist_profile"
}
