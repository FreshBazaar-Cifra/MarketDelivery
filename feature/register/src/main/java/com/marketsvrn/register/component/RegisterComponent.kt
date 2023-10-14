package com.marketsvrn.register.component

import kotlinx.coroutines.flow.StateFlow

interface RegisterComponent {
    val firstNameText: StateFlow<String>
    val lastNameText: StateFlow<String>
    val loginText: StateFlow<String>
    val passwordText: StateFlow<String>
    fun updateFirstNameText(newText: String)
    fun updateLastNameText(newText: String)
    fun updateLoginText(newText: String)
    fun updatePasswordText(newText: String)
    fun tryRegister()
    fun navigateLogin()
}