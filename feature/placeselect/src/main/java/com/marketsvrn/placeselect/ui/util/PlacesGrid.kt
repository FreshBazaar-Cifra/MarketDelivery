package com.marketsvrn.placeselect.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.marketsvrn.designsystem.common.LazyVerticalGridHelper
import com.marketsvrn.placeselect.model.PlaceUi

@Composable
fun PlacesGrid(
    modifier: Modifier = Modifier,
    places: LazyPagingItems<PlaceUi>,
    onSelectPlace: (Int) -> Unit
) {
    LazyVerticalGridHelper(
        pagingItems = places,
        modifier = modifier,
        key = {
            it.id
        }
    ) { place ->
        PlaceCard(
            place = place,
            modifier = Modifier,
            onClick = {
                onSelectPlace(place.id)
            }
        )
    }
}