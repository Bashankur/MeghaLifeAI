package com.meghalife.app.screens.tourist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelScreen() {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        t("Travel"),
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

            /* ───────── PAGE INTRO ───────── */
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = t("Getting Around Meghalaya"),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = t(
                        "Essential transport information for tourists, including buses, shared taxis, and road safety guidance."
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            /* ───────── BUS ROUTES ───────── */
            AppCard {
                SectionHeader(t("Bus Routes"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "State and private buses operate between major towns such as Shillong, Tura, Jowai, and Nongpoh."
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = t("Primary bus terminal: ISBT Mawiong, Shillong"),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            /* ───────── SHARED TAXIS ───────── */
            AppCard {
                SectionHeader(t("Shared Taxis"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "Shared taxis are the most common and flexible mode of travel for tourists."
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = t("Pickup points: Police Bazaar and ISBT Mawiong"),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = t("Shillong to Cherrapunji: ₹200 – ₹300"),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = t("Shillong to Dawki: ₹300 – ₹500"),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            /* ───────── LOCAL TRANSPORT HELP ───────── */
            AppCard {
                SectionHeader(t("Local Transport Assistance"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "For bookings and local travel assistance, tourists may contact verified local drivers."
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "+91 9XXXX XXXXX",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "+91 8XXXX XXXXX",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            /* ───────── ROAD CONDITIONS ───────── */
            AppCard {
                SectionHeader(t("Road Conditions & Safety"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "Most tourist routes remain accessible throughout the year. " +
                                "However, weather conditions can change rapidly in hilly regions."
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = t("Night travel after 7:00 PM is not recommended on hilly routes."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
