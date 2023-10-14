package com.marketsvrn.placeselect.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marketsvrn.designsystem.common.CustomSearchBar
import com.marketsvrn.designsystem.common.ErrorView
import com.marketsvrn.designsystem.common.LoadingView
import com.marketsvrn.designsystem.common.ResourceView
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.Market
import com.marketsvrn.placeselect.component.FakePlaceSelectComponent
import com.marketsvrn.placeselect.component.PlaceSelectComponent
import com.marketsvrn.placeselect.model.PlaceUi
import com.marketsvrn.placeselect.ui.util.BigMarketCard
import com.marketsvrn.placeselect.ui.util.PlacesGrid

@Composable
fun PlaceSelectScreen(
    component: PlaceSelectComponent
) {
    val market by component.market.collectAsStateWithLifecycle()
    val places = component.placesPager.collectAsLazyPagingItems()

    val searchQuery = remember { mutableStateOf("") }

    ResourceView(
        containerColor = ExtendedTheme.colors.greenBackground,
        modifier = Modifier.fillMaxSize(),
        paddingValues = WindowInsets.statusBars.asPaddingValues(),
        resource = market,
        loadingView = {
            LoadingView(
                modifier = Modifier
                    .fillMaxSize()
            )
        },
        errorView = {
            ErrorView(
                message = market.message!!,
                onRetry = component::retryMarketLoading,
                modifier = Modifier
                    .fillMaxSize()
            )
        },
        successView = {
            PlaceSelectMainView(
                places = places,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .padding(top = 16.dp),
                market = market.data!!,
                searchQuery = searchQuery,
                onSearchQueryChange = {
                    searchQuery.value = it
                },
                onSearch = {

                },
                onSelectPlace = component::selectPlace
            )
        }
    )
}

@Composable
private fun PlaceSelectMainView(
    modifier: Modifier = Modifier,
    places: LazyPagingItems<PlaceUi>,
    market: Market,
    onSearch: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSelectPlace: (Int) -> Unit,
    searchQuery: State<String>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BigMarketCard(
                market = market,
                modifier = Modifier
                    .fillMaxWidth()
            )
            ProductSearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = searchQuery,
                onSearch = onSearch,
                onQueryChange = onSearchQueryChange
            )
        }
        PlacesGrid(
            modifier = Modifier
                .weight(1f, true)
                .fillMaxWidth(),
            places = places,
            onSelectPlace = onSelectPlace
        )
    }
}

@Composable
fun ProductSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    query: State<String>
) {
    CustomSearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        placeholderText = "Поиск точки"
    )
}

@Preview
@Composable
private fun PlaceSelectPreview() {
    DeliveryTheme {
        PlaceSelectScreen(component = FakePlaceSelectComponent())
    }
}