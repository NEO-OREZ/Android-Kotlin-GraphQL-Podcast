package com.neo_orez.PodcastAppGraphql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        SplashLogoID.alpha=0f
        SplashLogoID.animate().setDuration(5000).alpha(1f).withEndAction {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right)
            finish()
        }

        SplashTextID.alpha=0f
        SplashTextID.animate().setDuration(5000).alpha(1f).withEndAction{
            val X = Intent(this, MainActivity::class.java)
            startActivity(X)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out )
            finish()
        }
    }
}