package com.marketsvrn.productslist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.marketsvrn.designsystem.common.CustomSearchBar
import com.marketsvrn.designsystem.common.LazyVerticalGridHelper
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.Market
import com.marketsvrn.model.Place
import com.marketsvrn.productslist.component.FakeProductsListComponent
import com.marketsvrn.productslist.component.ProductsListComponent
import com.marketsvrn.productslist.ui.bigplacecard.BigPlaceCard
import com.marketsvrn.productslist.ui.categories.CategoriesRow
import com.marketsvrn.productslist.ui.productcard.ProductCard

@Composable
fun ProductsListScreen(
    component: ProductsListComponent
) {
    val searchQuery = component.searchQuery.collectAsStateWithLifecycle()
    val products = component.productsPager.collectAsLazyPagingItems()
    val categories by component.categories.collectAsStateWithLifecycle()
    val selectedSortIndex by component.selectedSortIndex.collectAsStateWithLifecycle()
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    SortBottomSheet(
        onSelectSortBy = {
            component.selectSortBy(it)
        },
        onDismiss = {
        },
        selectedIndex = selectedSortIndex,
        show = showBottomSheet
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(ExtendedTheme.colors.greenBackground)
            .statusBarsPadding()
            .padding(top = 20.dp)
    ) {
        BigPlaceCard(
            place = Place.getStub(),
            market = Market.getStub(),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        CustomSearchBar(
            query = searchQuery,
            onQueryChange = component::onSearchQueryChange,
            onSearch = {
                component.search()
            },
            placeholderText = "Поиск товара",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )
        CategoriesRow(
            onSelectSort = {
                showBottomSheet.value = !showBottomSheet.value
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            categories = categories
        )
        LazyVerticalGridHelper(
            pagingItems = products,
            modifier = Modifier
                .weight(1f, true)
                .padding(horizontal = 10.dp),
            key = {
                it.id
            }
        ) { market ->
            ProductCard(
                product = market,
                modifier = Modifier,
                onClick = component::selectProduct,
                onBuyClick = {

                }
            )
        }
    }
}



@Preview
@Composable
fun ProductsListPreview() {
    DeliveryTheme {
        ProductsListScreen(component = FakeProductsListComponent())
    }
}