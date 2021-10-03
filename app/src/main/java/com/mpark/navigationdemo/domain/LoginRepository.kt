package com.mpark.navigationdemo.domain

import com.mpark.navigationdemo.common.dispatcher.DispatchersProvider
import com.mpark.navigationdemo.common.result.Result
import com.mpark.navigationdemo.data.LoginDataSource
import com.mpark.navigationdemo.data.SessionStore
import kotlinx.coroutines.withContext

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(
    private val dataSource: LoginDataSource,
    private val sessionStore: SessionStore,
    private val dispatcher: DispatchersProvider
) {

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore

    suspend fun getAppUser() = withContext(dispatcher.io) {
        sessionStore.appUser
    }

    suspend fun logout() = withContext(dispatcher.io) {
        dataSource.logout()
        sessionStore.appUser = AppUser.NonLoggedUser()
    }

    suspend fun login(username: String, password: String): Result<AppUser.LoggedInUser> = withContext(dispatcher.io) {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        result
    }

    private fun setLoggedInUser(loggedInUser: AppUser.LoggedInUser) {
        sessionStore.appUser = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}