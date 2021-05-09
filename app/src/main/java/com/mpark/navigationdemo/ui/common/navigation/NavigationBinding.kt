package com.mpark.navigationdemo.ui.common.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mpark.navigationdemo.R
import com.mpark.navigationdemo.databinding.ActivityMainBinding

class NavigationBinding(
    private val activity: AppCompatActivity,
) {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(activity.layoutInflater)
    }

    private val navView get() = binding.navView
    private val toolbar get() = binding.toolbar

    private val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )
    )

    val navController: NavController by lazy {
        activity.findNavController(R.id.nav_host_fragment_activity_main)
    }

    fun setup() {
        navView.setupWithNavController(navController)
        setupDestinationChangedListener()
        activity.setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    toolbar.isVisible = navController.previousBackStackEntry != null
                    navView.isGone = true
                }
                R.id.welcomeFragment,
                R.id.onboardFragment -> {
                    toolbar.isGone = true
                    navView.isGone = true
                }
                else -> {
                    toolbar.isVisible = true
                    navView.isVisible = true
                }
            }
            true
        }
    }

    fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}