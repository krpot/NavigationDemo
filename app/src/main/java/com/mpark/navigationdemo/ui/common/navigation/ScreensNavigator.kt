package com.mpark.navigationdemo.ui.common.navigation

import android.content.Context
import androidx.navigation.NavOptions
import com.mpark.navigationdemo.MainActivity
import com.mpark.navigationdemo.R
import com.mpark.navigationdemo.data.SessionStore
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ScreensNavigator @Inject constructor(
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

    fun navigate(destination: NavDestination) {
        val navOptions = NavOptions.Builder().apply {
            if (destination.isSingleTop) setLaunchSingleTop(true)
            destination.popUpTo?.also { popUpTo ->
                setPopUpTo(popUpTo.navId, popUpTo.isInclusive)
            }
        }.build()

        navController.navigate(
            destination.navId,
            destination.args,
            navOptions
        )
    }

    fun goBack() = navController.popBackStack()
}
