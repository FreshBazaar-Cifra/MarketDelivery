package com.marketsvrn.delivery.navigation.favorites.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface FavoritesNavConfig: Parcelable {
    @Parcelize
    data object Favorites : FavoritesNavConfig
}