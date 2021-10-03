package com.mpark.navigationdemo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.mpark.navigationdemo.R
import com.mpark.navigationdemo.domain.LoginRepository
import com.mpark.navigationdemo.common.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.security.auth.login.LoginException

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val mutableLoginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = mutableLoginForm

    private val mutableLoginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = mutableLoginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            // can be launched in a separate asynchronous job
            when (val result = loginRepository.login(username, password)) {
                is Result.Success -> {
                    mutableLoginResult.postValue(
                        LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
                    )
                }
                is Result.Error -> {
                    if (result.exception is LoginException) {
                        return@launch mutableLoginResult.postValue(
                            LoginResult(error = R.string.invalid_username_or_password_failure)
                        )
                    }

                    mutableLoginResult.postValue(LoginResult(error = R.string.login_failed))
                }
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            mutableLoginForm.postValue(LoginFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            mutableLoginForm.postValue(LoginFormState(passwordError = R.string.invalid_password))
        } else {
            mutableLoginForm.postValue(LoginFormState(isDataValid = true))
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 3
    }

    fun logout() {
        viewModelScope.launch {
            loginRepository.logout()
        }
    }
}