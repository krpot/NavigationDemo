package com.mpark.navigationdemo.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mpark.navigationdemo.MainActivity
import com.mpark.navigationdemo.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay

class SplashMainActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY_TIME = 2000L
    }

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            delay(SPLASH_DELAY_TIME)
            goToMain()
            finish()
        }
    }

    private val mainActivityIntent: Intent
        get() = Intent(this@SplashMainActivity, MainActivity::class.java)

    private fun goToMain() {
        startActivity(mainActivityIntent)
    }
}