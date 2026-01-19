package com.meghalife.app.language

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.meghalife.app.data.AppLanguage
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "settings")

/**
 * Persistent storage for app language.
 *
 * Uses DataStore (safe, async, lifecycle-aware).
 */
class LanguageStore(private val context: Context) {

    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("app_language")
        private val DEFAULT_LANGUAGE = AppLanguage.ENGLISH
    }

    /**
     * Returns true if user has already selected a language.
     */
    suspend fun isLanguageSelected(): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs.contains(LANGUAGE_KEY)
    }

    /**
     * Save selected language.
     */
    suspend fun saveLanguage(language: AppLanguage) {
        context.dataStore.edit { prefs ->
            prefs[LANGUAGE_KEY] = language.name
        }
    }

    /**
     * Load saved language safely.
     * Falls back to ENGLISH if missing or corrupted.
     */
    suspend fun getLanguage(): AppLanguage {
        val prefs = context.dataStore.data.first()
        val langName = prefs[LANGUAGE_KEY]

        return runCatching {
            if (langName != null) AppLanguage.valueOf(langName)
            else DEFAULT_LANGUAGE
        }.getOrElse {
            DEFAULT_LANGUAGE
        }
    }

    /**
     * Initialize LanguageManager from stored value.
     * Call once at app start.
     */
    suspend fun initLanguageManager() {
        LanguageManager.init(getLanguage())
    }
}
