package com.mpark.navigationdemo.ui.common.base

import androidx.fragment.app.Fragment
import com.mpark.navigationdemo.MainActivity
import com.mpark.navigationdemo.ui.common.navigation.NavManager
import com.mpark.navigationdemo.ui.common.navigation.ScreensNavigator
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var navManager: NavManager
//    protected val screensNavigator: ScreensNavigator by lazy {
//        (requireActivity() as MainActivity).navigator
//    }

}