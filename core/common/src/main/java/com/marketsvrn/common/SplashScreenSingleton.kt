package com.marketsvrn.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SplashScreenSingleton {

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean>
        get() = _isReady

    fun setReady(){
        _isReady.update { false }
    }
}