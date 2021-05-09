package com.mpark.navigationdemo.ui.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mpark.navigationdemo.MainActivity
import com.mpark.navigationdemo.common.di.AppModule
import com.mpark.navigationdemo.ui.common.navigation.ScreensNavigator

abstract class BaseFragment: Fragment() {

    protected lateinit var appModule: AppModule
    protected lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    private fun inject() {
        appModule = (requireActivity() as MainActivity).appModule
        screensNavigator = (requireActivity() as MainActivity).navigator
    }
}