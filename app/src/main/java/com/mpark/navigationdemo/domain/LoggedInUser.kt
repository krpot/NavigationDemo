package com.mpark.navigationdemo.domain

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
sealed class AppUser {
    data class NonLoggedUser(
        val displayName: String = ""
    ) : AppUser()

    data class LoggedInUser(
        val userId: String,
        val displayName: String,
        val accessToken: String
    ) : AppUser()
}


