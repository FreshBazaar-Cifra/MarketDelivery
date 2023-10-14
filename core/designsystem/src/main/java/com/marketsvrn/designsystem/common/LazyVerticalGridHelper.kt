package com.marketsvrn.designsystem.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun<T : Any> LazyVerticalGridHelper(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<T>,
    key: (T) -> Int,
    card: @Composable (T) -> Unit
) {
    if (pagingItems.itemCount > 0) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            modifier = modifier,
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                count = pagingItems.itemCount,
                key = {
                    key(pagingItems[it]!!)
                }
            ) { index ->
                pagingItems[index]?.let { pagingItem ->
                    card(pagingItem)
                }
            }
        }
    } else {
        when (pagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                LoadingView(
                    modifier = modifier.fillMaxSize()
                )
            }

            is LoadState.Error -> {
                ErrorView(
                    modifier = modifier.fillMaxSize(),
                    message = (pagingItems.loadState.refresh as LoadState.Error).error.message.toString(),
                    onRetry = pagingItems::refresh
                )
            }

            else -> {}
        }
    }
}


