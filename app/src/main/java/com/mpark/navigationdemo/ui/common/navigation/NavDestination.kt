package com.mpark.navigationdemo.ui.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes

data class NavDestination(
    @IdRes val navId: Int,
    val args: Bundle? = null,
    val isSingleTop: Boolean = false,
    val popUpTo: NavPopUpTo? = null
)

data class NavPopUpTo(
    @IdRes val navId: Int,
    val isInclusive: Boolean = false
)
