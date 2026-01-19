package com.meghalife.app.service

import android.Manifest
import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.max
import java.util.UUID

class LocationService : Service() {

    companion object {
        const val MODE = "MODE"
        const val BUS_MODE = "BUS"
        const val EMERGENCY_MODE = "EMERGENCY"
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private var lastUpdateTime = 0L
    private var minIntervalMs = 3000L

    override fun onCreate() {
        super.onCreate()

        // 🔐 Permission safety
        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
            return
        }

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        // 🔐 Double-check permission (VERY IMPORTANT)
        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
            return START_NOT_STICKY
        }

        val mode = intent?.getStringExtra(MODE) ?: BUS_MODE

        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: run {
                stopSelf()
                return START_NOT_STICKY
            }

        val dbRef = if (mode == EMERGENCY_MODE) {
            minIntervalMs = 5000L
            FirebaseDatabase.getInstance()
                .getReference("sos_alerts")
                .child(UUID.randomUUID().toString())
        } else {
            minIntervalMs = 3000L
            FirebaseDatabase.getInstance()
                .getReference("buses")
                .child(uid)
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {

                val location = result.lastLocation ?: return
                val now = System.currentTimeMillis()

                // 🚦 Rate limit
                if (now - lastUpdateTime < minIntervalMs) return
                lastUpdateTime = now

                // 🌍 Sanity checks
                val lat = location.latitude
                val lng = location.longitude
                if (lat !in -90.0..90.0 || lng !in -180.0..180.0) return

                val speed =
                    if (location.hasSpeed()) max(0f, location.speed) else 0f

                val data = mapOf(
                    "uid" to uid,
                    "lat" to lat,
                    "lng" to lng,
                    "speed" to speed,
                    "timestamp" to now
                )

                dbRef.setValue(data)
            }
        }

        startForeground(
            if (mode == BUS_MODE) 1 else 2,
            createNotification(mode)
        )

        val interval =
            if (mode == BUS_MODE) 5000L else 10000L

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            interval
        ).build()

        // ✅ THIS LINE IS SAFE NOW
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        return START_STICKY
    }

    override fun onDestroy() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(mode: String): Notification {

        val channelId = "location_tracking"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Live Location",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }

        val title =
            if (mode == BUS_MODE) "Driver Mode Active"
            else "Emergency Active"

        val text =
            if (mode == BUS_MODE) "Sharing bus live location"
            else "Sharing emergency location"

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setOngoing(true)
            .build()
    }
}
