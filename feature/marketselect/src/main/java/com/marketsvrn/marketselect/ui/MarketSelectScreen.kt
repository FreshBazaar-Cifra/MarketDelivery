package com.marketsvrn.marketselect.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.marketsvrn.designsystem.common.CustomSearchBar
import com.marketsvrn.designsystem.common.LazyVerticalGridHelper
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.marketselect.component.FakeMarketSelectComponent
import com.marketsvrn.marketselect.component.MarketSelectComponent
import com.marketsvrn.marketselect.ui.marketcard.MarketCard

@Composable
private fun PromoteText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = 34.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 34.sp
    )
}

@Composable
fun MarketSelectScreen(
    component: MarketSelectComponent
) {
    val markets = component.marketsPager.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ExtendedTheme.colors.greenBackground)
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PromoteText(
                text = "Только свежее и натуральное",
                color = ExtendedTheme.colors.darkGreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    )
                    .padding(top = 10.dp)
            )
            MarketSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 6.dp
                    )
            )
        }
        LazyVerticalGridHelper(
            pagingItems = markets,
            modifier = Modifier.weight(1f, true),
            key = {
                it.id
            }
        ) { market ->
            MarketCard(
                market = market,
                modifier = Modifier,
                onClick = component::selectMarket
            )
        }
    }
}

@Composable
fun MarketSearchBar(
    modifier: Modifier = Modifier
) {
    val searchText = remember {
        mutableStateOf("")
    }
    CustomSearchBar(
        query = searchText,
        onQueryChange = {
            searchText.value = it
        },
        onSearch = {},
        placeholderText = "Поиск рынка",
        modifier = modifier
    )
}

@Preview(
    showBackground = true, showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun MarketSelectScreenPreview() {
    DeliveryTheme {
        MarketSelectScreen(component = FakeMarketSelectComponent())
    }
}
