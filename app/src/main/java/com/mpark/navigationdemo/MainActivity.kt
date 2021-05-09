package com.mpark.navigationdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mpark.navigationdemo.common.di.AppModule
import com.mpark.navigationdemo.databinding.ActivityMainBinding
import com.mpark.navigationdemo.ui.common.navigation.NavigationBinding
import com.mpark.navigationdemo.ui.common.navigation.ScreensNavigator
import com.mpark.navigationdemo.ui.common.viewmodel.ViewModelFactory
import com.mpark.navigationdemo.ui.login.LoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var screensNavigator: ScreensNavigator

    val navigator: ScreensNavigator
        get() = screensNavigator

    val appModule: AppModule by lazy {
        (application as NavigateDemoApp).appModule
    }

    private val loginViewModel by viewModels<LoginViewModel> { ViewModelFactory(appModule) }

    private val navBinding by lazy { NavigationBinding(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = navBinding.binding
        setContentView(binding.root)

        setupToolbar()
        setupScreensNavigator()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupScreensNavigator() {
        screensNavigator = ScreensNavigator(
            navBinding = navBinding,
            sessionStore = appModule.sessionStore
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return screensNavigator.onSupportNavigateUp() || super.onSupportNavigateUp()
    }

    override fun finish() {
        loginViewModel.logout()
        super.finish()
    }

}