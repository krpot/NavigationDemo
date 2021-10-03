package com.mpark.navigationdemo.ui.login.welcome

import androidx.lifecycle.*
import com.mpark.navigationdemo.domain.LoginRepository
import com.mpark.navigationdemo.ui.login.LoggedInUserView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    fun getAppUser(): LiveData<LoggedInUserView> = liveData {
        val loggedInUserView = LoggedInUserView.fromAppUser(loginRepository.getAppUser())
        emit(loggedInUserView)
    }
}