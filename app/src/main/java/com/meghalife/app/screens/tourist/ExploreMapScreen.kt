package com.meghalife.app.screens.tourist

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.meghalife.app.R
import com.meghalife.app.data.TouristPOI
import com.meghalife.app.data.touristPOIs
import com.meghalife.app.language.t
import com.meghalife.app.ui.components.AppCard
import com.meghalife.app.ui.components.SectionHeader
import com.meghalife.app.ui.components.TransportInfoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreMapScreen() {

    val context = LocalContext.current

    /* ───────── UI STATE ───────── */
    val categories = listOf("All", "Waterfall", "Nature", "Culture")
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }
    var selectedPOI by remember { mutableStateOf<TouristPOI?>(null) }

    /* ───────── FILTER + SEARCH ───────── */
    val visiblePOIs = remember(selectedCategory, searchQuery) {
        touristPOIs
            .filter {
                selectedCategory == "All" ||
                        it.category.equals(selectedCategory, ignoreCase = true)
            }
            .filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.category.contains(searchQuery, ignoreCase = true)
            }
    }

    /* ───────── MAP CAMERA ───────── */
    val shillong = LatLng(25.5788, 91.8933)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(shillong, 12f)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        t("Explore Meghalaya"),
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
        ) {

            /* 🔍 SEARCH + FILTER (GROUPED) */
            AppCard(
                modifier = Modifier.padding(16.dp)
            ) {

                SectionHeader(t("Find Places"))

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(t("Search places or categories")) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(t(category)) }
                        )
                    }
                }
            }

            /* 🗺️ MAP */
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                visiblePOIs.forEach { poi ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(poi.lat, poi.lng)
                        ),
                        title = poi.name,
                        snippet = poi.category,
                        onClick = {
                            selectedPOI = poi
                            true
                        }
                    )
                }
            }
        }
    }

    /* ───────── POI DETAIL BOTTOM SHEET ───────── */
    selectedPOI?.let { poi ->

        ModalBottomSheet(
            onDismissRequest = { selectedPOI = null }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Image(
                    painter = painterResource(
                        poi.images.firstOrNull()
                            ?: R.drawable.poi_placeholder
                    ),
                    contentDescription = poi.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                Text(
                    text = poi.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = t(poi.category),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = poi.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = {
                        val uri =
                            "google.navigation:q=${poi.lat},${poi.lng}".toUri()
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, uri).apply {
                                setPackage("com.google.android.apps.maps")
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(t("Navigate"))
                }

                TransportInfoCard(
                    info = poi.transportInfo,
                    contacts = poi.transportContacts
                )
            }
        }
    }
}
