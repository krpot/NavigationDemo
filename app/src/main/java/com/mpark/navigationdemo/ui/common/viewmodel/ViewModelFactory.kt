package com.mpark.navigationdemo.ui.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpark.navigationdemo.common.di.AppModule
import com.mpark.navigationdemo.ui.login.LoginViewModel
import com.mpark.navigationdemo.ui.login.welcome.WelcomeViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory(
    private val appModule: AppModule
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = appModule.loginRepository
            ) as T
        }

        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(
                loginRepository = appModule.loginRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}