package com.mpark.navigationdemo.common.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.mpark.navigationdemo.NavigateDemoApp
import com.mpark.navigationdemo.common.dispatcher.DispatchersProvider
import com.mpark.navigationdemo.data.LoginDataSource
import com.mpark.navigationdemo.domain.LoginRepository
import com.mpark.navigationdemo.data.SessionStore

class AppModule(
    private val app: NavigateDemoApp
) {

    private val appContext: Context
        get() = app.applicationContext

    val sessionStore: SessionStore by lazy {
        SessionStore(preferences = PreferenceManager.getDefaultSharedPreferences(appContext))
    }

    val dispatchers: DispatchersProvider by lazy {
        DispatchersProvider()
    }

    val loginRepository: LoginRepository by lazy {
        LoginRepository(
            dataSource = loginDataSource,
            sessionStore = sessionStore,
            dispatcher = dispatchers
        )
    }

    private val loginDataSource: LoginDataSource by lazy {
        LoginDataSource()
    }
}