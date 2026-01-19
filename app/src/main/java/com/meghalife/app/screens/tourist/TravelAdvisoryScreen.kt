package com.meghalife.app.screens.tourist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.meghalife.app.ai.*
import com.meghalife.app.data.TravelAdvisorEngine
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelAdvisoryScreen() {

    val context = LocalContext.current
    val advisory = TravelAdvisorEngine.getDefaultAdvisory()
    val aiModel = remember { TravelRiskModel(context) }

    /* ───────── AI INPUT STATE ───────── */
    var selectedMonth by remember { mutableStateOf(7) }
    var selectedWeather by remember { mutableStateOf("Rain") }
    var selectedTerrain by remember { mutableStateOf("Hill") }
    var roadAlert by remember { mutableStateOf(true) }

    /* ───────── AI PREDICTION ───────── */
    val aiRisk = aiModel.predict(
        month = selectedMonth,
        weather = RiskEncoder.weatherToInt(selectedWeather),
        terrain = RiskEncoder.terrainToInt(selectedTerrain),
        roadAlert = if (roadAlert) 1 else 0
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        t("Travel Advisory"),
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            /* ───────── AI INPUTS ───────── */
            AppCard {

                SectionHeader(t("Risk Assessment Inputs"))

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = t("Travel Month"),
                    style = MaterialTheme.typography.titleSmall
                )

                Slider(
                    value = selectedMonth.toFloat(),
                    onValueChange = {
                        selectedMonth = it.toInt().coerceIn(1, 12)
                    },
                    valueRange = 1f..12f,
                    steps = 10
                )

                Text(
                    text = t("Selected Month") + ": $selectedMonth",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = t("Weather Condition"),
                    style = MaterialTheme.typography.titleSmall
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Clear", "Fog", "Rain").forEach { weather ->
                        FilterChip(
                            selected = selectedWeather == weather,
                            onClick = { selectedWeather = weather },
                            label = { Text(t(weather)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = t("Terrain Type"),
                    style = MaterialTheme.typography.titleSmall
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("City", "Hill", "Waterfall").forEach { terrain ->
                        FilterChip(
                            selected = selectedTerrain == terrain,
                            onClick = { selectedTerrain = terrain },
                            label = { Text(t(terrain)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = t("Road Alerts Active"),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Switch(
                        checked = roadAlert,
                        onCheckedChange = { roadAlert = it }
                    )
                }
            }

            /* ───────── AI OUTPUT ───────── */
            AppCard {

                SectionHeader(t("AI Travel Risk Assessment"))

                val riskColor = when (aiRisk) {
                    RiskLevel.HIGH -> MaterialTheme.colorScheme.error
                    RiskLevel.MEDIUM -> MaterialTheme.colorScheme.tertiary
                    RiskLevel.LOW -> MaterialTheme.colorScheme.primary
                }

                Text(
                    text = aiRisk.name,
                    color = riskColor,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = when (aiRisk) {
                        RiskLevel.HIGH ->
                            t("Travel conditions indicate a high level of risk. Non-essential travel should be postponed and local advisories should be followed.")

                        RiskLevel.MEDIUM ->
                            t("Travel conditions present moderate risk. Travel is possible with caution and regular updates from local authorities.")

                        RiskLevel.LOW ->
                            t("Travel conditions are generally safe. Standard precautions are advised.")
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            /* ───────── TRANSPORT ADVICE ───────── */
            AppCard {

                SectionHeader(t("Transport Advice"))

                advisory.vehicleSuggestions.forEach {
                    Text(
                        text = "• $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            /* ───────── IMPORTANT NOTE ───────── */
            AppCard {

                SectionHeader(t("Important Note"))

                Text(
                    text = advisory.advisoryNote,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
