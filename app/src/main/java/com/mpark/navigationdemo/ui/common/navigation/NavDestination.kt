package com.mpark.navigationdemo.ui.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import java.io.Serializable

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

data class NavUi(
    val title: String,
    val hasToolbar: Boolean = true,
    val hasBottomView: Boolean = true
) : Serializable {
    companion object {
        fun mainMenu(title: String): NavUi {
            return NavUi(title = title)
        }

        fun toolbarAndNoBottomView(title: String): NavUi {
            return NavUi(title = title, hasBottomView = false)
        }

        val noNavUi: NavUi = NavUi(title = "", hasToolbar = false, hasBottomView = false)

        val default: NavUi = mainMenu("NavigationDemo")
    }
}