package com.tempo.task.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.time.Duration
import java.util.concurrent.TimeUnit

class Utils {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        CLEAR
    }

    companion object {


        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }


        fun formatStringToAgo(
            format: String,
            wholeDuration: Duration,
            specificDuration: Long
        ): String {
            return java.lang.String.format(
                format,
                specificDuration,
                TimeUnit.MILLISECONDS.convert(
                    wholeDuration.toNanos() % 1000000000,
                    TimeUnit.NANOSECONDS
                )
            )
        }
    }

}