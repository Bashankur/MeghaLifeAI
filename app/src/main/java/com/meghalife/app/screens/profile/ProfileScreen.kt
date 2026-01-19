package com.meghalife.app.screens.profile

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
fun ProfileScreen(
    onSwitchMode: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(t("Profile")) }
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

            /* 🌐 LANGUAGE SETTINGS */
            AppCard(modifier = Modifier.fillMaxWidth()) {

                SectionHeader(t("Language Settings"))

                Spacer(modifier = Modifier.height(12.dp))

                AppLanguage.values().forEach { lang ->
                    OutlinedButton(
                        onClick = { LanguageManager.change(lang) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(lang.displayName)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            /* 🔁 APP MODE */
            AppCard(modifier = Modifier.fillMaxWidth()) {

                SectionHeader(t("App Mode"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "Switch between Resident and Tourist mode " +
                                "to access different features."
                    ),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onSwitchMode,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Switch Mode"))
                }
            }

            /* ℹ️ ABOUT APP */
            AppCard(modifier = Modifier.fillMaxWidth()) {

                SectionHeader(t("About App"))

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "MeghaLifeAI",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = t(
                        "Smart services for residents and tourists of Meghalaya."
                    ),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "${t("Version")} 1.0",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
