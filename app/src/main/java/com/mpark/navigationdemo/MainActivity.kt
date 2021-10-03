package com.mpark.navigationdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.mpark.navigationdemo.databinding.ActivityMainBinding
import com.mpark.navigationdemo.ui.common.navigation.Destinations
import com.mpark.navigationdemo.ui.common.navigation.NavManager
import com.mpark.navigationdemo.ui.common.navigation.ScreensNavigator
import com.mpark.navigationdemo.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )
    )

    val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    @Inject
    lateinit var navManager: NavManager

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

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupNavigation() {
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.apply {
            navView.setupWithNavController(navController)
            navView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_home -> navManager.navigate(Destinations.home)
                    R.id.navigation_dashboard -> navManager.navigate(Destinations.dashboard)
                    R.id.navigation_notifications -> navManager.navigate(Destinations.notifications)
                }
                true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        navManager.start()
    }

    override fun finish() {
        loginViewModel.logout()
        super.finish()
    }
}
