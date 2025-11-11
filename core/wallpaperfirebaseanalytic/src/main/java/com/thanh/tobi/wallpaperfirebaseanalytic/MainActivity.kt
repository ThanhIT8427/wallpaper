package com.thanh.tobi.wallpaperfirebaseanalytic

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.thanh.tobi.wallpaperfirebaseanalytic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this) {}
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAnalytics = Firebase.analytics
        binding.apply {
            binding.btnEvenFace.setOnClickListener {
                sendEvent(btnEvenFace.text.toString())
            }

            binding.btnEvenYT.setOnClickListener {
                sendEvent(btnEvenYT.text.toString())
            }

            binding.btnEvenIns.setOnClickListener {
                sendEvent(btnEvenIns.text.toString())
            }

            binding.btnEvenTiktok.setOnClickListener {
                sendEvent(btnEvenTiktok.text.toString())
            }


            binding.btnUserFoodApple.setOnClickListener {
                sendUserProperties("Apple")
            }

            binding.btnUserFoodMango.setOnClickListener {
                sendUserProperties("Mango")
            }

            binding.btnUserFoodBanana.setOnClickListener {
                sendUserProperties("Banana")
            }

            binding.btnUserFoodTomato.setOnClickListener {
                sendUserProperties("Tomato")
            }
        }
        loadAd()
        logRemoteConfig()
    }

    fun sendEvent(even: String) {
        firebaseAnalytics.logEvent("cus_share_image", Bundle().apply {
            putString("app", even)
        })
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.log("User opened Main screen")
        crashlytics.recordException(Exception("Thanh123"))
    }

    fun sendUserProperties(favoriteFood: String) {
        firebaseAnalytics.setUserProperty("favorite_food", favoriteFood)
        Log.d("Thanh123", "Is success")
    }

    fun loadAd() {
        val bannerRequest = AdRequest.Builder().build()
        binding.bannerAd.loadAd(bannerRequest)
    }

    fun logRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val data = remoteConfig.getString("userName")
                    Log.d("Thanh123", data)
                } else {
                    Log.d("Thanh123", "Load fail")
                }
            }
    }
}