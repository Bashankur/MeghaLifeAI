package com.meghalife.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModeSelectionScreen(
    onResident: () -> Unit,
    onTourist: () -> Unit,
    onDriver: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        /* ───────── INTRO ───────── */
        Text(
            text = "Welcome to MeghaLifeAI",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Select how you want to use the app.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        /* ───────── RESIDENT MODE ───────── */
        AppCard {
            SectionHeader("Resident Mode")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Access public services, transport updates, and emergency support.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onResident,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue as Resident")
            }
        }

        /* ───────── TOURIST MODE ───────── */
        AppCard {
            SectionHeader("Tourist Mode")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Explore places, travel advisories, safety, and local transport.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onTourist,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue as Tourist")
            }
        }

        /* ───────── DRIVER MODE ───────── */
        AppCard {
            SectionHeader("Driver Mode")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Share live location to improve public transport visibility.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onDriver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue as Driver")
            }
        }
    }
}
