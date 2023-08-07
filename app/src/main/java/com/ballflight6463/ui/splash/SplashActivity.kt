package com.ballflight6463.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import com.ballflight6463.R
import com.ballflight6463.databinding.ActivitySplashBinding
import com.ballflight6463.ui.main.MainActivity
import com.ballflight6463.utils.BaseInjectionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseInjectionActivity<ActivitySplashBinding, SplashViewModel>(){
    private lateinit var ivbottom:ImageView
    override fun getLayoutRes(): Int =R.layout.activity_splash

    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ivbottom=findViewById(R.id.iv_bottom)

        val timer=object :CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
                if(p0<1000L)
                    ivbottom.setImageResource(R.drawable.ic_splash_3)
                else if (p0 in 1001..1999)
                    ivbottom.setImageResource(R.drawable.ic_splash_2)
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        }
        timer.start()
    }
}