package com.mpark.navigationdemo.ui.common.base

import androidx.fragment.app.Fragment
import com.mpark.navigationdemo.MainActivity
import com.mpark.navigationdemo.ui.common.navigation.ScreensNavigator

abstract class BaseFragment : Fragment() {

    protected val screensNavigator: ScreensNavigator by lazy {
        (requireActivity() as MainActivity).navigator
    }

}