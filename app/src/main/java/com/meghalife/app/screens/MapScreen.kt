package com.meghalife.app.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.meghalife.app.data.BusRepository
import com.meghalife.app.data.LiveBus
import com.meghalife.app.language.t
import com.meghalife.app.network.DirectionsApi
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader
import com.meghalife.app.utils.DelayStatus
import com.meghalife.app.utils.calculateDelayStatus
import com.meghalife.app.utils.decodePolyline
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val SHILLONG = LatLng(25.5788, 91.8933)
private const val DEFAULT_ZOOM = 13f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onBack: () -> Unit
) {

    val context = LocalContext.current

    /* ───────── Permission ───────── */

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

    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    /* ───────── Bus Data ───────── */

    val repository = remember { BusRepository() }
    val liveBuses by repository.buses.collectAsState()

    /* ───────── Map Camera ───────── */

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(SHILLONG, DEFAULT_ZOOM)
    }

    /* ───────── Selection State ───────── */

    var selectedBus by remember { mutableStateOf<LiveBus?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    /* ───────── Directions API (NO API KEY EXPOSED) ───────── */

    val directionsApi = remember {
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApi::class.java)
    }

    var routePolyline by remember { mutableStateOf<List<LatLng>>(emptyList()) }
    var routeDistanceMeters by remember { mutableStateOf(0) }
    var routeDurationSeconds by remember { mutableStateOf(0) }

    /* ───────── Fetch ETA when bus selected ───────── */

    LaunchedEffect(selectedBus) {
        selectedBus?.let { bus ->
            try {
                val response = directionsApi.getDirections(
                    origin = "${bus.lat},${bus.lng}",
                    destination = "${SHILLONG.latitude},${SHILLONG.longitude}"
                )

                val route = response.routes.firstOrNull() ?: return@LaunchedEffect

                routePolyline =
                    decodePolyline(route.overview_polyline.points)

                routeDistanceMeters =
                    route.legs.first().distance.value

                routeDurationSeconds =
                    route.legs.first().duration.value

            } catch (_: Exception) {
                // Network / quota failure — fail silently
            }
        }
    }

    /* ───────── UI ───────── */

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(t("Live Bus Map")) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = hasLocationPermission
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true
            )
        ) {

            /* Route Polyline */
            if (routePolyline.isNotEmpty()) {
                Polyline(
                    points = routePolyline,
                    width = 10f
                )
            }

            /* Live Bus Markers */
            liveBuses.forEach { bus ->
                Marker(
                    state = MarkerState(
                        position = LatLng(bus.lat, bus.lng)
                    ),
                    title = bus.id,
                    onClick = {
                        selectedBus = bus
                        showBottomSheet = true
                        true
                    }
                )
            }
        }
    }

    /* ───────── Bottom Sheet ───────── */

    if (showBottomSheet && selectedBus != null) {

        val bus = selectedBus!!

        val expectedEtaMin = routeDurationSeconds / 60
        val actualEtaMin =
            if (bus.speed > 0f)
                ((routeDistanceMeters / bus.speed) / 60).toInt()
            else
                expectedEtaMin

        val delayStatus =
            calculateDelayStatus(expectedEtaMin, actualEtaMin)

        val statusText = when (delayStatus) {
            DelayStatus.EARLY -> t("Running Early")
            DelayStatus.ON_TIME -> t("On Time")
            DelayStatus.DELAYED -> t("Delayed")
        }

        val statusColor = when (delayStatus) {
            DelayStatus.EARLY -> Color(0xFFFFA000)
            DelayStatus.ON_TIME -> Color(0xFF2E7D32)
            DelayStatus.DELAYED -> Color(0xFFC62828)
        }

        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                AppCard {
                    SectionHeader(t("Bus Information"))
                    Text("${t("Bus ID")}: ${bus.id.take(8)}")
                    Text(statusText, color = statusColor)
                }

                AppCard {
                    SectionHeader(t("Estimated Arrival"))
                    Text("${t("Google ETA")}: $expectedEtaMin min")
                    Text("${t("Live ETA")}: $actualEtaMin min")
                    Text(
                        "${t("Distance")}: ${
                            "%.1f".format(routeDistanceMeters / 1000f)
                        } km"
                    )
                }

                Button(
                    onClick = { showBottomSheet = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Close"))
                }
            }
        }
    }
}
