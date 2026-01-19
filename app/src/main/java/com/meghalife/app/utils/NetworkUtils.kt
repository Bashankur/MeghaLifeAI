package com.meghalife.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Checks whether the device currently has an active internet connection.
 *
 * This function is used for:
 * - Offline fallbacks
 * - Translation handling
 * - Network-safe feature toggling
 *
 * @param context Application or Activity context
 * @return true if internet connectivity is available, false otherwise
 */
fun isOnline(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager
            ?: return false

    val network = connectivityManager.activeNetwork ?: return false

    val capabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasCapability(
        NetworkCapabilities.NET_CAPABILITY_INTERNET
    )
}
