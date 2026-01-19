package com.meghalife.app.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.meghalife.app.language.t
import com.meghalife.app.service.LocationService
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.PrimaryButton
import com.meghalife.app.ui.components.SectionHeader
import com.meghalife.app.ui.permissions.RequestLocationPermission
import com.meghalife.app.ui.theme.DangerRed
import com.meghalife.app.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverScreen(
    onSwitchMode: () -> Unit
) {

    val context = LocalContext.current
    val auth = remember { FirebaseAuth.getInstance() }

    /* ───────── AUTH (ANONYMOUS LOGIN) ───────── */
    LaunchedEffect(Unit) {
        if (auth.currentUser == null) {
            auth.signInAnonymously()
        }
    }

    val driverId = auth.currentUser?.uid ?: "unknown_driver"

    /* ───────── UI STATE ───────── */
    var isSharing by remember { mutableStateOf(false) }
    var requestPermission by remember { mutableStateOf(false) }

    /* ───────── PERMISSION HANDLER ───────── */
    if (requestPermission) {
        RequestLocationPermission {
            requestPermission = false
        }
    }

    /* ───────── SERVICE HELPERS ───────── */
    fun startLocationService() {
        val intent = Intent(context, LocationService::class.java).apply {
            putExtra(LocationService.MODE, LocationService.BUS_MODE)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
        isSharing = true
    }

    fun stopLocationService() {
        context.stopService(Intent(context, LocationService::class.java))
        isSharing = false
    }

    /* ───────── UI ───────── */
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        t("Driver Mode"),
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

            /* ───────── DRIVER INFO ───────── */
            AppCard {
                SectionHeader(t("Driver Information"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t("Assigned Driver ID"),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = driverId.take(8) + "…",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            /* ───────── LOCATION STATUS ───────── */
            AppCard {
                SectionHeader(t("Location Status"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text =
                        if (isSharing)
                            t("Live location sharing is active.")
                        else
                            t("Live location sharing is inactive."),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            /* ───────── LOCATION CONTROL ───────── */
            AppCard {
                SectionHeader(t("Location Sharing"))

                Spacer(Modifier.height(16.dp))

                PrimaryButton(
                    text =
                        if (isSharing)
                            t("Stop Location Sharing")
                        else
                            t("Start Location Sharing"),
                    color =
                        if (isSharing) DangerRed else PrimaryGreen,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {

                    if (isSharing) {
                        stopLocationService()
                        return@PrimaryButton
                    }

                    val hasPermission =
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED

                    if (hasPermission) {
                        startLocationService()
                    } else {
                        requestPermission = true
                    }
                }
            }

            /* ───────── SWITCH MODE ───────── */
            AppCard {
                SectionHeader(t("App Mode"))

                Spacer(Modifier.height(8.dp))

                Text(
                    text = t("Switch to Tourist or Resident mode."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = onSwitchMode,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Switch Mode"))
                }
            }

            /* ───────── NOTICE ───────── */
            Text(
                text = t("Location sharing must remain enabled during active duty hours."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
