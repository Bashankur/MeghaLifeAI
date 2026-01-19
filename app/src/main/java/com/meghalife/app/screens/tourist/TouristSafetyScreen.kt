package com.meghalife.app.screens.tourist

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.PrimaryButton
import com.meghalife.app.ui.components.SectionHeader
import com.meghalife.app.ui.permissions.RequestLocationPermission
import com.meghalife.app.ui.theme.DangerRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristSafetyScreen() {

    val context = LocalContext.current
    val locationClient =
        remember { LocationServices.getFusedLocationProviderClient(context) }

    /* 🔐 Permission trigger */
    var requestPermission by remember { mutableStateOf(false) }

    /* 🔁 Permission handler */
    if (requestPermission) {
        RequestLocationPermission {
            requestPermission = false
            shareCurrentLocation(context, locationClient)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        t("Safety & Emergency"),
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
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* ───────── EMERGENCY SOS ───────── */
            AppCard {

                SectionHeader(t("Emergency Assistance"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t(
                        "Use this option only in case of immediate danger or life-threatening situations."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                PrimaryButton(
                    text = t("Call Emergency (112)"),
                    color = DangerRed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    dial(context, "112")
                }
            }

            /* ───────── POLICE ───────── */
            AppCard {

                SectionHeader(t("Police Assistance"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t(
                        "Contact local police for safety concerns, lost items, or assistance."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { dial(context, "100") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Call Police"))
                }
            }

            /* ───────── MEDICAL ───────── */
            AppCard {

                SectionHeader(t("Medical Help"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t(
                        "Call emergency medical services for accidents or health emergencies."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { dial(context, "108") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Call Ambulance"))
                }
            }

            /* ───────── LOCATION SHARING ───────── */
            AppCard {

                SectionHeader(t("Share Location"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t(
                        "Share your live location with trusted contacts so they can assist you if needed."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {

                        val hasPermission =
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED

                        if (hasPermission) {
                            shareCurrentLocation(context, locationClient)
                        } else {
                            requestPermission = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Share Live Location"))
                }
            }
        }
    }
}

/* ───────────────── HELPER FUNCTIONS ───────────────── */

/** Open dialer safely */
private fun dial(context: Context, number: String) {
    context.startActivity(
        Intent(Intent.ACTION_DIAL, "tel:$number".toUri())
    )
}

/** Share current GPS location via text */
private fun shareCurrentLocation(
    context: Context,
    locationClient: FusedLocationProviderClient
) {

    val hasPermission =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    if (!hasPermission) return

    try {
        locationClient.lastLocation
            .addOnSuccessListener { location ->

                val mapsLink =
                    location?.let {
                        "https://maps.google.com/?q=${it.latitude},${it.longitude}"
                    } ?: "https://maps.google.com"

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "📍 My current location:\n$mapsLink"
                    )
                }

                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        ("Share location via")
                    )
                )
            }
    } catch (_: SecurityException) {
        // Permission revoked while app running — fail silently
    }
}
