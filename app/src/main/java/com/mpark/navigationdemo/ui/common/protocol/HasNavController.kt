package com.mpark.navigationdemo.ui.common.protocol

import android.os.Bundle
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mpark.navigationdemo.R
import com.mpark.navigationdemo.ui.common.navigation.Destinations
import com.mpark.navigationdemo.ui.common.navigation.NavManager
import com.mpark.navigationdemo.ui.common.navigation.NavUi

interface HasNavController {
    val navController: NavController
}

interface HasNavUi {
    val toolbar: MaterialToolbar
    val bottomNavView: BottomNavigationView
}

interface HasNavManager {
    var navManager: NavManager
}

interface NavigationAware : HasNavController, HasNavUi, HasNavManager {

    fun setupBottomNavigation() {
        bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, _, arguments ->
            if (navManager.checkSessionExpired()) return@addOnDestinationChangedListener

            bindNavUiFrom(arguments)
        }
    }

    private fun bindNavUiFrom(bundle: Bundle?) {
        val navUi: NavUi = (bundle?.getSerializable(Destinations.NAV_UI) as? NavUi) ?: NavUi.default
        toolbar.isVisible = navUi.hasToolbar
        bottomNavView.isVisible = navUi.hasBottomView
    }
}