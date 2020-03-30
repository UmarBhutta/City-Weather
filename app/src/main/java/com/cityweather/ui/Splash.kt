package com.cityweather.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.view.animation.AnimationUtils
import com.cityweather.R
import kotlinx.android.synthetic.main.activity_splash.*


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)

        val myanim = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        name.startAnimation(myanim)
    }
}
