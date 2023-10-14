package com.marketsvrn.register.component

import android.content.Context
import android.widget.Toast
import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.common.Resource.Status.ERROR
import com.marketsvrn.common.Resource.Status.SUCCESS
import com.marketsvrn.data.repository.user.UserRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.RegisterData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class RealRegisterComponent(
    private val onRegister: () -> Unit,
    private val onNavigateLogin: () -> Unit,
    componentContext: ComponentContext
) : RegisterComponent, BaseComponent(componentContext) {
    private val repo: UserRepository by inject()
    private val context: Context by inject()

    private val _loginText = MutableStateFlow("")
    private val _passwordText = MutableStateFlow("")
    private val _firstNameText = MutableStateFlow("")
    private val _lastNameText = MutableStateFlow("")
    override val loginText: StateFlow<String>
        get() = _loginText

    override val passwordText: StateFlow<String>
        get() = _passwordText

    override val firstNameText: StateFlow<String>
        get() = _firstNameText
    override val lastNameText: StateFlow<String>
        get() = _lastNameText

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
        repo
            .register(
                RegisterData(
                    _loginText.value,
                    _passwordText.value,
                    _firstNameText.value,
                    _lastNameText.value
                )
            )
            .onEach {
                if (it.status == SUCCESS) {
                    onRegister()
                }
                if (it.status == ERROR) {
                    mainScope.launch {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .launchIn(ioScope)
    }

    override fun navigateLogin() {
        onNavigateLogin()
    }
}