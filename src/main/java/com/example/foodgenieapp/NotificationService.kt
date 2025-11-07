package com.example.foodgenieapp

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class NotificationService(private val context: Context) {

    companion object {
        const val CHANNEL_ID = "foodgenie_channel"
        const val ORDER_NOTIFICATION_ID = 1
        const val PROMOTION_NOTIFICATION_ID = 2

        // Notification permission (required for Android 13+)
        private const val NOTIFICATION_PERMISSION = "android.permission.POST_NOTIFICATIONS"
    }

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "FoodGenie Notifications", // Use direct string instead of resource
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Order updates and promotions" // Use direct string
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                NOTIFICATION_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            // Permission not required for older Android versions
            true
        }
    }

    fun showOrderNotification(title: String, message: String) {
        if (!hasNotificationPermission()) {
            // Log or handle the case where permission is not granted
            println("Notification permission not granted")
            return
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_dialog_info) // Use system icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        try {
            with(NotificationManagerCompat.from(context)) {
                notify(ORDER_NOTIFICATION_ID, builder.build())
            }
        } catch (e: SecurityException) {
            // Handle SecurityException if permission was revoked
            println("SecurityException: ${e.message}")
        } catch (e: Exception) {
            println("Notification failed: ${e.message}")
        }
    }

    fun showPromotionalNotification(title: String, message: String) {
        if (!hasNotificationPermission()) {
            println("Notification permission not granted")
            return
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_dialog_email) // Use system icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        try {
            with(NotificationManagerCompat.from(context)) {
                notify(System.currentTimeMillis().toInt(), builder.build())
            }
        } catch (e: SecurityException) {
            println("SecurityException: ${e.message}")
        } catch (e: Exception) {
            println("Notification failed: ${e.message}")
        }
    }
}