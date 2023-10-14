package com.marketsvrn.register.component

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FakeRegisterComponent: RegisterComponent {
    private val _firstNameText = MutableStateFlow("")
    private val _lastNameText = MutableStateFlow("")
    private val _loginText = MutableStateFlow("")
    private val _passwordText = MutableStateFlow("")

    override val firstNameText: StateFlow<String>
        get() = _firstNameText
    override val lastNameText: StateFlow<String>
        get() = _lastNameText
    override val loginText: StateFlow<String>
        get() = _loginText
    override val passwordText: StateFlow<String>
        get() = _passwordText

    override fun updateLoginText(newText: String) {
        _loginText.update { newText }
    }

    override fun updatePasswordText(newText: String) {
        _passwordText.update { newText }
    }

    override fun updateFirstNameText(newText: String) {
        _firstNameText.update { newText }
    }

    override fun updateLastNameText(newText: String) {
        _lastNameText.update { newText }
    }

    override fun tryRegister() {
    }

    override fun navigateLogin() {
    }


}