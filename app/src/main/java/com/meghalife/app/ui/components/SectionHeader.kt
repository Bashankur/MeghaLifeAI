package com.meghalife.app.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
}
