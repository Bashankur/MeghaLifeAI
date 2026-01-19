package com.meghalife.app.screens.safety

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.PrimaryButton
import com.meghalife.app.ui.components.SectionHeader
import com.meghalife.app.ui.theme.DangerRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SafetyScreen() {

    val context = LocalContext.current

    /* ───────── PERMISSION STATE ───────── */
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasLocationPermission = granted
        }

    /* ───────── LOCATION CLIENT ───────── */
    val fusedLocationClient =
        remember { LocationServices.getFusedLocationProviderClient(context) }

    /* ───────── FIREBASE SOS ───────── */
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val sosRef =
        FirebaseDatabase.getInstance().getReference("sos_alerts")

    /* ───────── UI ───────── */
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(t("Safety")) }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* 🚨 SOS EMERGENCY */
            AppCard(modifier = Modifier.fillMaxWidth()) {

                SectionHeader(t("Emergency SOS"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "Use SOS only in real emergencies. " +
                                "Your live location will be shared with authorities."
                    ),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(20.dp))

                PrimaryButton(
                    text = t("Send SOS Alert"),
                    color = DangerRed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {

                    if (!hasLocationPermission) {
                        permissionLauncher.launch(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        return@PrimaryButton
                    }

                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location ->

                            val data = mapOf(
                                "uid" to uid,
                                "lat" to location?.latitude,
                                "lng" to location?.longitude,
                                "timestamp" to System.currentTimeMillis()
                            )

                            sosRef.push().setValue(data)
                        }
                }
            }

            /* 📍 LOCATION SHARING */
            AppCard(modifier = Modifier.fillMaxWidth()) {

                SectionHeader(t("Location Sharing"))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t(
                        "Quickly share your current location with trusted contacts."
                    ),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {

                        if (!hasLocationPermission) {
                            permissionLauncher.launch(
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                            return@OutlinedButton
                        }

                        shareCurrentLocation(
                            context = context,
                            fusedLocationClient = fusedLocationClient
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Share Location"))
                }
            }

            /* 📞 EMERGENCY CALLS */
            AppCard(modifier = Modifier.fillMaxWidth()) {

                SectionHeader(t("Emergency Numbers"))

                Spacer(modifier = Modifier.height(12.dp))

                EmergencyCallButton(
                    label = t("Police"),
                    number = "100"
                )

                EmergencyCallButton(
                    label = t("Ambulance"),
                    number = "108"
                )

                EmergencyCallButton(
                    label = t("Fire"),
                    number = "101"
                )

                EmergencyCallButton(
                    label = t("Women Helpline"),
                    number = "181"
                )
            }
        }
    }
}

/* ───────── LOCATION SHARE HELPER ───────── */
private fun shareCurrentLocation(
    context: android.content.Context,
    fusedLocationClient: com.google.android.gms.location.FusedLocationProviderClient
) {

    val hasPermission =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    if (!hasPermission) return

    try {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->

                val link =
                    if (location != null) {
                        "https://maps.google.com/?q=${location.latitude},${location.longitude}"
                    } else {
                        "https://maps.google.com"
                    }

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "📍 My current location:\n$link"
                    )
                }

                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        "Share location via"
                    )
                )
            }
    } catch (_: SecurityException) {
        // Permission revoked while app is running
    }
}

/* ───────── REUSABLE CALL BUTTON ───────── */
@Composable
private fun EmergencyCallButton(
    label: String,
    number: String
) {
    val context = LocalContext.current

    OutlinedButton(
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_DIAL, "tel:$number".toUri())
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text("$label ($number)")
    }
}
