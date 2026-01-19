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
fun PlanScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // 🧠 Page Title
        Text(
            text = t("Plan Your Trip"),
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = t("Smart itineraries designed for Meghalaya."),
            style = MaterialTheme.typography.bodyMedium
        )

        // 🤖 AI Planner Overview
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            SectionHeader(t("AI Trip Planner"))

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = t("Get personalized travel plans based on your time, interests, and weather."),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // 📅 Itinerary Options
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            SectionHeader(t("Upcoming Itineraries"))

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = t("• 1-day express itinerary"),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = t("• 2-day balanced trip"),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = t("• 3-day immersive experience"),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // 🚧 Coming Soon Notice
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            SectionHeader(t("Coming Soon"))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = t("AI-powered trip planning will be available in a future update."),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
