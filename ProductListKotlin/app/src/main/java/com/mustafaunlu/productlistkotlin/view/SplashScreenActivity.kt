package com.mustafaunlu.productlistkotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mustafaunlu.productlistkotlin.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        supportActionBar?.hide()
        Handler().postDelayed({
            val intent= Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

            }
}