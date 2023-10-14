package com.marketsvrn.login.component

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FakeLoginComponent: LoginComponent {
    private val _loginText = MutableStateFlow("")
    override val loginText: StateFlow<String>
        get() = _loginText

    private val _passwordText = MutableStateFlow("")
    override val passwordText: StateFlow<String>
        get() = _passwordText

    override fun updateLoginText(newText: String) {
        _loginText.update { newText }
    }

    override fun updatePasswordText(newText: String) {
        _passwordText.update { newText }
    }

    override fun tryLogin() {
    }

    override fun navigateBack() {
    }

    override fun navigateRegister() {
    }
}