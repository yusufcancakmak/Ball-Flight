package com.ballflight6463.core

import android.content.Context
import androidx.room.Room
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.ballflight6463.local.AppDatabase
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp
import java.util.*


@HiltAndroidApp
class BaseApplication: LocalizationApplication(){
    companion object {
        lateinit var db: AppDatabase
    }
    override fun getDefaultLanguage(context: Context): Locale =Locale.getDefault()
    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
            .setContext(this)
            .setMode(MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        initRoom()
    }

    private fun initRoom() {
        db= Room.databaseBuilder(applicationContext,AppDatabase::class.java,"best_db").build()
    }

}