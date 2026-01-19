package com.meghalife.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meghalife.app.data.AppLanguage
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@Composable
fun LanguageSelectionScreen(
    onLanguageSelected: (AppLanguage) -> Unit
) {
    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* ───────── APP IDENTITY ───────── */
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "MeghaLifeAI",
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "Smart digital services for Meghalaya",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            /* ───────── LANGUAGE SELECTION ───────── */
            AppCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                SectionHeader("Select Language")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Choose your preferred language to continue.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    AppLanguage.values().forEach { language ->
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            onClick = { onLanguageSelected(language) }
                        ) {
                            Text(language.displayName)
                        }
                    }
                }
            }
        }
    }
}
