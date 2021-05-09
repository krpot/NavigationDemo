package com.mpark.navigationdemo

import android.app.Application
import com.mpark.navigationdemo.common.di.AppModule

class NavigateDemoApp: Application() {

    private lateinit var mutableAppModule: AppModule
    val appModule: AppModule
        get() = mutableAppModule


    override fun onCreate() {
        super.onCreate()
        mutableAppModule = AppModule(this)
    }
}
