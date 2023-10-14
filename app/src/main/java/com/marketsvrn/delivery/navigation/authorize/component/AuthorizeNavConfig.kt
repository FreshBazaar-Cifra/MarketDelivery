package com.marketsvrn.delivery.navigation.authorize.component

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed interface AuthorizeNavConfig: Parcelable {
    @Parcelize
    data object Login : AuthorizeNavConfig
    @Parcelize
    data object Register : AuthorizeNavConfig
}