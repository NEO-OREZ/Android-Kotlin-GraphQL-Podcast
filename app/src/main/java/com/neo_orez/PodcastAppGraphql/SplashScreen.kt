package com.neo_orez.PodcastAppGraphql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neo_orez.PodcastAppGraphql.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var bindingSplash: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_splash_screen)
        /////binding
        bindingSplash = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = bindingSplash.root
        setContentView(view)
        ////////////

        bindingSplash.SplashLogoID.alpha=0f
        bindingSplash.SplashLogoID.animate().setDuration(1000).alpha(1f).withEndAction {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right)
            finish()
        }

        bindingSplash.SplashTextID.alpha=0f
        bindingSplash.SplashTextID.animate().setDuration(1000).alpha(1f).withEndAction{
            val X = Intent(this, MainActivity::class.java)
            startActivity(X)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out )
            finish()
        }
    }
}