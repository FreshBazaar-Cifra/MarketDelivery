package com.marketsvrn.designsystem.util

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

abstract class BaseComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext {
    // The scope is automatically cancelled when the component is destroyed
    val ioScope = componentContext.coroutineScope(Dispatchers.IO)
    val mainScope = componentContext.coroutineScope(Dispatchers.Main)
}