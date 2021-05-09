package com.mpark.navigationdemo.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersProvider {

    val io: CoroutineDispatcher
        get() = Dispatchers.IO
}