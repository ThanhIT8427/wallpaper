package com.thanh.tobi.wallpaperfirebaseanalytic

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ThanhFirebaseMessaging : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            Log.d("Thanh123", "Message Notification Body: ${it.body}")
        }
    }
}