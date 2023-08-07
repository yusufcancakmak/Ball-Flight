package com.ballflight6463.utils

import android.content.Context
import android.content.Intent
import com.ballflight6463.R
import com.ballflight6463.services.BackgroundSoundService
import com.pixplicity.easyprefs.library.Prefs

class Utils {
    companion object {
        var soundActive: Boolean = Prefs.getBoolean("soundActive", true)
        var musicActive: Boolean = Prefs.getBoolean("musicActive", false)
        var language: String = Prefs.getString("language", "en")
        var characterPosition = 0
        var playerName = ""
        var playerCoins = 0
        var ball_buy = 0

        fun startMusic(context: Context) {
            val intent = Intent(context, BackgroundSoundService::class.java)
            if (musicActive) {
                context.startService(intent)
            } else {
                context.stopService(intent)
            }
        }
        fun isSoundActive(context: Context): Boolean {
            return Prefs.getBoolean("soundActive", true)
        }

        fun isMusicActive(context: Context): Boolean {
            return Prefs.getBoolean("musicActive", true)
        }

        fun setSoundActive(context: Context, isActive: Boolean) {
            Prefs.putBoolean("soundActive", isActive)
        }

        fun setMusicActive(context: Context, isActive: Boolean) {
            Prefs.putBoolean("musicActive", isActive)
        }
    }
    fun getCharacterDrawableResource(): Int {
        return when (characterPosition) {
            0 -> R.drawable.ic_ball1
            1 -> R.drawable.ic_ball2
            2 -> R.drawable.ic_ball3
            3 -> R.drawable.ic_ball4
            4 -> R.drawable.ic_ball5
            else -> R.drawable.ic_ball1
        }
    }

}