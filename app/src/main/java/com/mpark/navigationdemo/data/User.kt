package com.mpark.navigationdemo.data

import com.mpark.navigationdemo.data.model.AppUser
import kotlin.random.Random

data class User(
    val id: String,
    val displayName: String,
    val password: String
) {

    fun toLoggedInUser(): AppUser.LoggedInUser {
        return AppUser.LoggedInUser(
            userId = this.id,
            displayName = this.displayName,
            accessToken = Random.nextInt().toString()
        )
    }
}