package com.meghalife.app.screens.tourist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meghalife.app.data.AppLanguage
import com.meghalife.app.language.LanguageManager
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristProfileScreen(
    onSwitchMode: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        t("Profile"),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            /* ───────── APP LANGUAGE ───────── */
            AppCard {

                SectionHeader(t("App Language"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t("Choose your preferred language for the application interface."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppLanguage.values().forEach { language ->
                        OutlinedButton(
                            onClick = {
                                // Persisted via LanguageStore in RootNavHost
                                LanguageManager.change(language)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(language.displayName)
                        }
                    }
                }
            }

            /* ───────── APP MODE ───────── */
            AppCard {

                SectionHeader(t("App Mode"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t("Switch between Tourist and Resident mode based on your current needs."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = onSwitchMode,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Switch to Resident Mode"))
                }
            }

            /* ───────── ABOUT APP ───────── */
            AppCard {

                SectionHeader(t("About App"))

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "MeghaLifeAI",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = t("Smart digital services designed for tourists visiting Meghalaya."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                HorizontalDivider()

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "${t("Version")} 1.0",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
