package com.mpark.navigationdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mpark.navigationdemo.databinding.ActivityMainBinding
import com.mpark.navigationdemo.ui.common.navigation.ScreensNavigator
import com.mpark.navigationdemo.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )
    )

    private lateinit var screensNavigator: ScreensNavigator

    val navigator: ScreensNavigator
        get() = screensNavigator

    private val loginViewModel: LoginViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        setupNavigation()
        setContentView(binding.root)
    }

    val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupNavigation() {
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun finish() {
        loginViewModel.logout()
        super.finish()
    }

}