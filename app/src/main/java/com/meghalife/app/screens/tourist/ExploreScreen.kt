package com.meghalife.app.screens.tourist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@Composable
fun ExploreScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        /* ───────── HEADER ───────── */
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = t("Explore Meghalaya"),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = t(
                    "Discover natural attractions, local culture, food spots, and nearby experiences across Meghalaya."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        /* ───────── EXPLORE HIGHLIGHTS ───────── */
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionHeader(t("What You Can Explore"))

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = t("• Scenic viewpoints, waterfalls, and natural attractions"),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = t("• Local cafés, food spots, and markets"),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = t("• Cultural places and heritage sites"),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = t("• Transport options and travel-related information"),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        /* ───────── MAP GUIDANCE ───────── */
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionHeader(t("Interactive Map"))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = t(
                    "Use the interactive map to search for places, filter by category, and get directions to your destination."
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
