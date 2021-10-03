package com.mpark.navigationdemo.ui.common.navigation

import android.content.Context
import androidx.navigation.NavOptions
import com.mpark.navigationdemo.MainActivity
import com.mpark.navigationdemo.R
import com.mpark.navigationdemo.data.SessionStore
import dagger.hilt.android.qualifiers.ActivityContext

class ScreensNavigator(
    @ActivityContext private val context: Context,
    private val sessionStore: SessionStore
) {

    private val clearBackStack by lazy {
        NavOptions.Builder()
            .setPopUpTo(R.id.mobile_navigation, false)
            .build()
    }

    private val navController get() = (context as MainActivity).navController

    private fun goToLoginOrOnboardFlow() {
        val destinationId =
            if (!sessionStore.onBoarded) R.id.loginFragment else R.id.welcomeFragment

        val navOptions = NavOptions.Builder()
            .setPopUpTo(navController.graph.startDestination, true)
            .build()
        navController.navigate(destinationId, null, navOptions)
    }


    private fun getDestinationIdFromSession(): Int {
        return when {
            !sessionStore.isLoggedIn -> R.id.loginFragment
            sessionStore.isLoggedIn && !sessionStore.onBoarded -> R.id.onboardFragment
            sessionStore.loginCompleted -> R.id.navigation_home
            else -> throw IllegalStateException("Cannot handle unexpected navigation")
        }
    }

    private fun goToOnboard() {
        navController.navigate(R.id.onboardFragment, null, clearBackStack)
    }

    fun onboardToHome() {
        navController.navigate(R.id.navigation_home, null, clearBackStack)
    }

    fun goToLogin() {
        navController.navigate(R.id.loginFragment)
    }

    private fun loginToHome() {
        navController.navigate(R.id.navigation_home, null, clearBackStack)
    }

    fun goToOnboardOrHome() {
        when (getDestinationIdFromSession()) {
            R.id.loginFragment -> throw IllegalStateException("Invalid destination id: R.id.loginFragment. It shoud in login state.")
            R.id.onboardFragment -> goToOnboard()
            R.id.navigation_home -> loginToHome()
        }
    }
}
