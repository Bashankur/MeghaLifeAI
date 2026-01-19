package com.meghalife.app.language

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.meghalife.app.data.AppLanguage

/**
 * Global language state holder.
 *
 * - Backed by Compose state
 * - Observed by UI via t()
 * - Initialized once at app start
 */
object LanguageManager {

    private var isInitialized = false

    var currentLanguage by mutableStateOf(AppLanguage.ENGLISH)
        private set

    /**
     * Initialize language (called once from RootNavHost / MainActivity)
     */
    fun init(language: AppLanguage) {
        if (!isInitialized) {
            currentLanguage = language
            isInitialized = true
        }
    }

    /**
     * Change language at runtime
     */
    fun change(language: AppLanguage) {
        if (currentLanguage != language) {
            currentLanguage = language
        }
    }
}
