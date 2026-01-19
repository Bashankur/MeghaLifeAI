package com.meghalife.app.language

import androidx.compose.runtime.*
import com.meghalife.app.data.AppLanguage

@Composable
fun t(text: String): String {

    val lang = LanguageManager.currentLanguage
    var value by remember { mutableStateOf(text) }

    LaunchedEffect(text, lang) {
        value = OfflineTranslator.translate(text, lang)
    }

    return value
}
