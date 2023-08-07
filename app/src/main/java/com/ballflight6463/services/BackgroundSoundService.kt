package com.ballflight6463.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.ballflight6463.R

class BackgroundSoundService:Service(){
    private lateinit var player: MediaPlayer
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.aloneagain)
        player.isLooping = true
        player.setVolume(100f,100f)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        player.release()
    }
}