package com.meghalife.app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@Composable
fun TransportScreen(
    onLiveMapClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        /* ───────── PAGE HEADER ───────── */

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = t("Transport"),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = t("Track buses and plan your daily travel"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        /* ───────── LIVE BUS TRACKING ───────── */

        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLiveMapClick() }
        ) {

            SectionHeader(t("Live Bus Tracking"))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = t("See buses moving in real time"),
                style = MaterialTheme.typography.bodySmall
            )
        }

        /* ───────── BUS ROUTES ───────── */

        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            SectionHeader(t("Bus Routes"))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = t("Explore all available routes"),
                style = MaterialTheme.typography.bodySmall
            )
        }

        /* ───────── NEARBY STOPS ───────── */

        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            SectionHeader(t("Nearby Stops"))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = t("Find stops close to you"),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
