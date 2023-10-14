package com.marketsvrn.login.component

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {
    val loginText: StateFlow<String>
    val passwordText: StateFlow<String>
    fun updateLoginText(newText: String)
    fun updatePasswordText(newText: String)
    fun tryLogin()
    fun navigateBack()
    fun navigateRegister()
}