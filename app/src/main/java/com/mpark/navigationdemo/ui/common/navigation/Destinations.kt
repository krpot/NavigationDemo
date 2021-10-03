package com.mpark.navigationdemo.ui.common.navigation

import androidx.core.os.bundleOf
import com.mpark.navigationdemo.R

object Destinations {

    const val NAV_UI = "NavUi"
    const val IS_SESSION_EXPIRED = "IsSessionExpired"

    val login: NavDestination by lazy {
        NavDestination(
            navId = R.id.loginFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(NAV_UI to NavUi.noNavUi)
        )
    }

    val sessionExpired: NavDestination by lazy {
        NavDestination(
            navId = R.id.loginFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(
                NAV_UI to NavUi.noNavUi,
                IS_SESSION_EXPIRED to true
            )
        )
    }

    val logout: NavDestination by lazy {
        NavDestination(
            navId = R.id.welcomeFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(NAV_UI to NavUi.noNavUi)
        )
    }

    val onboard: NavDestination by lazy {
        NavDestination(
            navId = R.id.onboardFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(NAV_UI to NavUi.noNavUi)
        )
    }

    val welcome: NavDestination by lazy {
        NavDestination(
            navId = R.id.welcomeFragment,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(NAV_UI to NavUi.noNavUi)
        )
    }

    val home: NavDestination by lazy {
        NavDestination(
            navId = R.id.navigation_home,
            isSingleTop = true,
            popUpTo = NavPopUpTo(R.id.mobile_navigation),
            args = bundleOf(NAV_UI to NavUi.mainMenu("Home"))
        )
    }
}