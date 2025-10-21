package com.example.thanhvip123wallpaper

import android.net.Uri
import android.os.Bundle
import android.transition.Transition
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val videoUri = "android.resource://${packageName}/${R.raw.wallpaper_1}".toUri()
        val videoView =  findViewById<VideoView>(R.id.vvLiveWallpaper)

        window.sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                videoView.setVideoURI(videoUri)
                val mediaController = MediaController(this@DetailActivity)
                mediaController.setAnchorView(videoView)
                videoView.setMediaController(mediaController)
            }
            override fun onTransitionEnd(transition: Transition) {
                videoView.start()
            }
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionResume(transition: Transition) {}
        })

    }
}