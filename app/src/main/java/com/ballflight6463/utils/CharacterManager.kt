package com.ballflight6463.utils

import android.content.Context
import android.preference.PreferenceManager

object CharacterManager {
    private const val KEY_CHARACTER_POSITION = "character_position"
    var score:Int = 0
    var bestScore:Int = 0

    fun saveCharacterPosition(context: Context, position: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putInt(KEY_CHARACTER_POSITION, position)
        editor.apply()
    }

    fun loadCharacterPosition(context: Context): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(KEY_CHARACTER_POSITION, 0) // 0, varsayılan karakter pozisyonu
    }
}