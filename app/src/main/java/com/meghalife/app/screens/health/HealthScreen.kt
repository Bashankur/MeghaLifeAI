package com.meghalife.app.screens.health

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader
import androidx.core.net.toUri

/* ───────────────── DATA MODEL ───────────────── */
data class Hospital(
    val name: String,
    val phone: String,
    val lat: Double,
    val lng: Double
)

/* ───────────────── VERIFIED HOSPITAL DATA (SHILLONG) ───────────────── */
private val hospitals = listOf(
    Hospital("NEIGRIHMS Hospital", "03642221111", 25.6029, 91.8973),
    Hospital("Civil Hospital Shillong", "03642225555", 25.5750, 91.8827),
    Hospital("Bethany Hospital", "03642223041", 25.5854, 91.8799),
    Hospital("Nazareth Hospital", "03642222062", 25.5668, 91.8909),
    Hospital("Woodland Hospital", "03642223846", 25.5733, 91.8787),
    Hospital(
        "Ganesh Das Govt. Maternity & Child Health Hospital",
        "03642222368",
        25.5747,
        91.8844
    ),
    Hospital(
        "Dr. H. Gordon Roberts Hospital (KJP Synod)",
        "03642225333",
        25.5850,
        91.8775
    ),
    Hospital("Supercare Hospital Shillong", "03642220000", 25.5664, 91.8728)
)

/* ───────────────── MAIN SCREEN ───────────────── */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen() {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(t("Health")) }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            /* 🚑 EMERGENCY AMBULANCE */
            item {
                AppCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SectionHeader(t("Emergency Help"))

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_DIAL,
                                "tel:108".toUri()
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(t("Call Ambulance (108)"))
                    }
                }
            }

            /* 🏥 HOSPITAL HEADER */
            item {
                SectionHeader(t("Nearby Hospitals"))
            }

            /* 🏥 HOSPITAL LIST */
            items(hospitals) { hospital ->
                HospitalCard(hospital)
            }
        }
    }
}

/* ───────────────── HOSPITAL CARD ───────────────── */
@Composable
private fun HospitalCard(hospital: Hospital) {

    val context = LocalContext.current

    val callIntent = Intent(
        Intent.ACTION_DIAL,
        "tel:${hospital.phone}".toUri()
    )

    val navigationIntent = Intent(
        Intent.ACTION_VIEW,
        "google.navigation:q=${hospital.lat},${hospital.lng}".toUri()
    ).apply {
        setPackage("com.google.android.apps.maps")
    }

    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = hospital.name,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    context.startActivity(callIntent)
                }
            ) {
                Text(t("Call Hospital"))
            }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    context.startActivity(navigationIntent)
                }
            ) {
                Text(t("Navigate"))
            }
        }
    }
}
