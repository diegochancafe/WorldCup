package com.diegochancafe.worldcup.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.diegochancafe.worldcup.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    // --
    private lateinit var appContext: Context
    private lateinit var viewBinding: ActivitySplashBinding

    // --
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        // --
        appContext = this
        // --
        Handler(Looper.getMainLooper()).postDelayed({
            changeActivity()
        }, 3000)
        // --
        supportActionBar?.hide()
    }

    // --
    private fun changeActivity() {
        // --
        startActivity(Intent(appContext, LoginActivity::class.java))
        finish()
    }
}