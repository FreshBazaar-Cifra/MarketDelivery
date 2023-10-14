package com.marketsvrn.favorites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.designsystem.common.ErrorView
import com.marketsvrn.designsystem.common.LoadingView
import com.marketsvrn.designsystem.common.ResourceView
import com.marketsvrn.designsystem.common.ScreenHeaderText
import com.marketsvrn.favorites.component.FakeFavoritesComponent
import com.marketsvrn.favorites.component.FavoritesComponent

@Composable
fun FavoritesScreen(
    component: FavoritesComponent,
    modifier: Modifier = Modifier
) {
    val ordersSorted = component.favorites.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .statusBarsPadding()
            .padding(top = 20.dp)
            .padding(horizontal = 16.dp)
            .waterfallPadding()
    ) {
        ScreenHeaderText(text = "Избранное")
        ResourceView(
            loadingView = {
                LoadingView(
                    modifier = Modifier.fillMaxSize()
                )
            },
            errorView = {
                ErrorView(
                    message = it!!,
                    onRetry = {
                        component.refreshFavorites()
                    },
                    modifier = Modifier.fillMaxSize()
                )
            },
            successView = {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(it.size) { index ->
                        FavoriteProductCard(
                            product = it[index],
                            modifier = Modifier
                                .fillMaxWidth(),
                            selectOrder = component::selectFavorite
                        )

                    }
                }
            },
            resource = ordersSorted.value,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    FavoritesScreen(component = FakeFavoritesComponent())
}