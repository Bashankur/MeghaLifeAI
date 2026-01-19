package com.meghalife.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.meghalife.app.language.LanguageManager
import com.meghalife.app.language.LanguageStore
import com.meghalife.app.navigation.RootNavHost
import com.meghalife.app.ui.theme.MeghaLifeAITheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var languageStore: LanguageStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        languageStore = LanguageStore(this)

        // 🔹 Load saved language BEFORE UI
        lifecycleScope.launch {
            val savedLanguage = languageStore.getLanguage()
            LanguageManager.init(savedLanguage)
        }

        setContent {
            MeghaLifeAITheme {
                RootNavHost()
            }
        }
    }
}
