package com.marketsvrn.delivery.navigation.favorites.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.marketsvrn.delivery.navigation.favorites.component.FavoritesNavComponent
import com.marketsvrn.favorites.ui.FavoritesScreen

@Composable
fun FavoritesNavContent(
    component: FavoritesNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Children(
        stack = stack,
        modifier = modifier,
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is FavoritesNavComponent.Child.FavoritesChild -> FavoritesScreen(
                component = child.component
            )
        }
    }
}