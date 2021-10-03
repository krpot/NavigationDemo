package com.mpark.navigationdemo.ui.common.navigation

import com.mpark.navigationdemo.data.SessionStore
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavManager @Inject constructor(
    private val screensNavigator: ScreensNavigator,
    private val sessionStore: SessionStore
) {
    fun start() {
        val destination =
            if (!sessionStore.onBoarded) Destinations.login else Destinations.welcome

        navigate(destination)
    }

    fun goToOnboardOrHome() {
        val destination = getDestinationIdFromSession()
        if (destination == Destinations.login) {
            throw IllegalStateException("Invalid destination id: R.id.loginFragment. It should in login state.")
        }

        screensNavigator.navigate(destination)
    }

    private fun getDestinationIdFromSession(): NavDestination {
        return when {
            !sessionStore.isLoggedIn -> Destinations.login
            sessionStore.isLoggedIn && !sessionStore.onBoarded -> Destinations.onboard
            sessionStore.loginCompleted -> Destinations.home
            else -> throw IllegalStateException("Cannot handle unexpected navigation")
        }
    }

    fun navigate(destination: NavDestination) {
        if (checkSessionExpired()) return
        screensNavigator.navigate(destination)
    }

    fun goBack() = screensNavigator.goBack()

    fun checkSessionExpired(): Boolean {
        if (sessionStore.checkSessionExpired()) {
            screensNavigator.navigate(Destinations.sessionExpired)
            return true
        }

        return false
    }
}