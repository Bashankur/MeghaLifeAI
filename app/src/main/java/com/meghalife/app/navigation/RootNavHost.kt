package com.meghalife.app.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.meghalife.app.language.LanguageManager
import com.meghalife.app.language.LanguageStore
import com.meghalife.app.screens.*
import kotlinx.coroutines.launch

@Composable
fun RootNavHost() {

    /* 🌍 Root Nav Controller */
    val navController = rememberNavController()

    /* 🌐 Context & Language */
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val languageStore = remember { LanguageStore(context) }

    /* 🚦 Decide first screen */
    var startDestination by remember { mutableStateOf<String?>(null) }

    /**
     * App launch logic:
     * 1. Check if language is selected
     * 2. If yes → load & apply language
     * 3. Decide first screen
     */
    LaunchedEffect(Unit) {
        if (languageStore.isLanguageSelected()) {
            val savedLanguage = languageStore.getLanguage()
            LanguageManager.init(savedLanguage)
            startDestination = Routes.SPLASH
        } else {
            startDestination = Routes.LANGUAGE_SELECTION
        }
    }

    /* 🧭 Render NavHost only after decision */
    startDestination?.let { start ->

        NavHost(
            navController = navController,
            startDestination = start
        ) {

            /* ───────── Language Selection ───────── */
            composable(Routes.LANGUAGE_SELECTION) {
                LanguageSelectionScreen { selectedLanguage ->
                    scope.launch {
                        languageStore.saveLanguage(selectedLanguage)
                        LanguageManager.change(selectedLanguage)

                        navController.navigate(Routes.SPLASH) {
                            popUpTo(Routes.LANGUAGE_SELECTION) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            /* ───────── Splash ───────── */
            composable(Routes.SPLASH) {
                SplashScreen {
                    navController.navigate(Routes.MODE_SELECTION) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            }

            /* ───────── Mode Selection ───────── */
            composable(Routes.MODE_SELECTION) {
                ModeSelectionScreen(
                    onResident = {
                        navController.navigate(Routes.RESIDENT_HOME) {
                            popUpTo(Routes.MODE_SELECTION) {
                                inclusive = true
                            }
                        }
                    },
                    onTourist = {
                        navController.navigate(Routes.TOURIST_HOME) {
                            popUpTo(Routes.MODE_SELECTION) {
                                inclusive = true
                            }
                        }
                    },
                    onDriver = {
                        navController.navigate(Routes.DRIVER) {
                            popUpTo(Routes.MODE_SELECTION) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            /* ───────── Resident Flow ───────── */
            composable(Routes.RESIDENT_HOME) {
                ResidentHome()
            }

            /* ───────── Tourist Flow ───────── */
            composable(Routes.TOURIST_HOME) {
                TouristNavHost(
                    onExitToModeSelection = {
                        navController.navigate(Routes.MODE_SELECTION) {
                            popUpTo(Routes.MODE_SELECTION) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            /* ───────── Driver Flow ───────── */
            composable(Routes.DRIVER) {
                DriverScreen(
                    onSwitchMode = {
                        navController.navigate(Routes.MODE_SELECTION) {
                            popUpTo(Routes.MODE_SELECTION) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

        }
    }
}
