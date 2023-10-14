package com.marketsvrn.login.component

import android.widget.Toast
import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.common.Resource.Status.ERROR
import com.marketsvrn.common.Resource.Status.SUCCESS
import com.marketsvrn.data.repository.user.UserRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.LoginData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.get
import org.koin.core.component.inject

class RealLoginComponent(
    private val onLogin: () -> Unit,
    private val onNavigateRegister: () -> Unit,
    componentContext: ComponentContext
) : LoginComponent, BaseComponent(componentContext) {
    private val repo: UserRepository by inject()

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
        repo
            .login(
                LoginData(
                    _loginText.value,
                    _passwordText.value
                )
            )
            .onEach {
                if (it.status == SUCCESS) {
                    navigateBack()
                    return@onEach
                }
                if (it.status == ERROR) {
                    mainScope.launch {
                        Toast.makeText(get(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    return@onEach
                }
            }
            .launchIn(ioScope)
    }

    override fun navigateBack() {
        onLogin()
    }

    override fun navigateRegister() {
        onNavigateRegister()
    }
}