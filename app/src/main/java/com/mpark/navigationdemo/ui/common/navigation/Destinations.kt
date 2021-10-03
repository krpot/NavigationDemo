package com.mpark.navigationdemo.ui.common.navigation

import androidx.core.os.bundleOf
import com.mpark.navigationdemo.R

object Destinations {

    const val IS_SESSION_EXPIRED = "IsSessionExpired"

    val login: NavDestination by lazy {
        NavDestination(
            navId = R.id.loginFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation)
        )
    }

    val sessionExpired: NavDestination by lazy {
        NavDestination(
            navId = R.id.loginFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(IS_SESSION_EXPIRED to true)
        )
    }

    val logout: NavDestination by lazy {
        NavDestination(
            navId = R.id.welcomeFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation)
        )
    }

    val onboard: NavDestination by lazy {
        NavDestination(
            navId = R.id.onboardFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation)
        )
    }

    val welcome: NavDestination by lazy {
        NavDestination(
            navId = R.id.welcomeFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation)
        )
    }

    val home: NavDestination by lazy {
        NavDestination(
            navId = R.id.navigation_home,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation)
        )
    }

    val dashboard: NavDestination by lazy {
        NavDestination(
            navId = R.id.navigation_dashboard,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.navigation_dashboard)
        )
    }

    val notifications: NavDestination by lazy {
        NavDestination(
            navId = R.id.navigation_notifications,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.navigation_notifications)
        )
    }
}