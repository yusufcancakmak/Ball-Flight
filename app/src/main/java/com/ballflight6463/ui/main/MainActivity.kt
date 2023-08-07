package com.ballflight6463.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ballflight6463.R
import com.ballflight6463.databinding.ActivityMainBinding
import com.ballflight6463.ui.setting.SettingFragment
import com.ballflight6463.utils.BaseInjectionActivity
import com.ballflight6463.utils.BaseInjectionFragment
import com.ballflight6463.utils.Constans
import com.ballflight6463.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseInjectionActivity<ActivityMainBinding,MainViewModel>() {
    private lateinit var navController: NavController
    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
    override fun onCreate(savedInstanceState: Bundle?) {

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val dm = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        Constans.SCREEN_WIDTH = dm.widthPixels
        Constans.SCREEN_HEIGHT = dm.heightPixels
        super.onCreate(savedInstanceState)

        navController=(supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController


        Utils.startMusic(this)
    }
}