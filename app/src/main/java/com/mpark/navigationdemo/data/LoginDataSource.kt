package com.mpark.navigationdemo.data

import com.mpark.navigationdemo.domain.AppUser.LoggedInUser
import com.mpark.navigationdemo.common.result.Result
import java.io.IOException
import javax.security.auth.login.LoginException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val fakeUser = UserDatabase.users.find { user ->
                user.id == username && user.password == password
            }?.toLoggedInUser()

            if (fakeUser != null) {
                return Result.Success(fakeUser)
            }

            return Result.Error(LoginException())

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
    }
}