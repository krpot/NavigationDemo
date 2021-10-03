package com.mpark.navigationdemo.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mpark.navigationdemo.domain.AppUser
import java.util.concurrent.TimeUnit

class SessionStore(
    val preferences: SharedPreferences
) {

    companion object {
        private const val KEY_DISPLAY_USER_NAME = "key_display_user_name"
        private const val KEY_ONBOARDED = "key_onboarded"
    }

    private var accessToken: String = ""
    private var loggedInUserId: String = ""
    private var lastLoginTime: Long = 0L

    private var displayUserName: String
        get() = preferences.getString(KEY_DISPLAY_USER_NAME, "") ?: ""
        set(value) {
            preferences.edit {
                putString(KEY_DISPLAY_USER_NAME, value)
            }
        }

    var appUser: AppUser
        get() {
            if (accessToken.isNotBlank() && loggedInUserId.isNotBlank()) {
                return AppUser.LoggedInUser(
                    userId = loggedInUserId,
                    displayName = displayUserName,
                    accessToken = accessToken
                )
            }

            return AppUser.NonLoggedUser(displayUserName)
        }
        set(value) {
            when (value) {
                is AppUser.NonLoggedUser -> {
                    accessToken = ""
                }
                is AppUser.LoggedInUser -> {
                    loggedInUserId = value.userId
                    displayUserName = value.displayName
                    accessToken = value.accessToken
                    lastLoginTime = System.currentTimeMillis()
                }
            }
        }

    var onBoarded: Boolean
        get() = preferences.getBoolean(KEY_ONBOARDED, false)
        set(value) {
            preferences.edit {
                putBoolean(KEY_ONBOARDED, value)
            }
        }

    val isLoggedIn: Boolean
        get() = appUser is AppUser.LoggedInUser

    val loginCompleted: Boolean
        get() = isLoggedIn && onBoarded

    fun checkSessionExpired(): Boolean {
        // User session automatically will be expired in i minute
        val sessionTimeSurpassed = System.currentTimeMillis() - lastLoginTime > TimeUnit.MINUTES.toMillis(1)
        val isExpired = isLoggedIn && sessionTimeSurpassed
        if (isExpired) {
            accessToken = ""
            appUser = AppUser.NonLoggedUser(displayUserName)
        }
        return isExpired
    }

}