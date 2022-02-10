package com.zero.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.zero.myapplication.R
import com.zero.myapplication.root.App
import com.zero.myapplication.ui.login.LoginActivity
import com.zero.myapplication.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (App.sessions.isLogin()) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }
                finish()
            }, 2000L
        )
    }
}