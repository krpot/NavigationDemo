package com.mpark.navigationdemo.common.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.mpark.navigationdemo.common.dispatcher.DispatchersProvider
import com.mpark.navigationdemo.data.LoginDataSource
import com.mpark.navigationdemo.data.SessionStore
import com.mpark.navigationdemo.domain.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun sessionStore(@ApplicationContext appContext: Context): SessionStore =
        SessionStore(preferences = PreferenceManager.getDefaultSharedPreferences(appContext))

    @Provides
    fun dispatchers(): DispatchersProvider = DispatchersProvider()

    @Provides
    @Singleton
    fun loginRepository(
        loginDataSource: LoginDataSource,
        sessionStore: SessionStore,
        dispatchers: DispatchersProvider,
    ): LoginRepository =
        LoginRepository(
            dataSource = loginDataSource,
            sessionStore = sessionStore,
            dispatcher = dispatchers
        )

    @Provides
    @Singleton
    fun loginDataSource(): LoginDataSource = LoginDataSource()
}