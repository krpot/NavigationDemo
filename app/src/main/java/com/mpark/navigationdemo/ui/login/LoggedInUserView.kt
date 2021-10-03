package com.mpark.navigationdemo.ui.login

import com.mpark.navigationdemo.domain.AppUser

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String
) {

    companion object {
        fun fromAppUser(appUser: AppUser) =
            when (appUser) {
                is AppUser.NonLoggedUser -> LoggedInUserView(displayName = appUser.displayName)
                is AppUser.LoggedInUser -> LoggedInUserView(displayName = appUser.displayName)
            }
    }
}