package com.marketsvrn.placeselect.util

import com.marketsvrn.common.AbstractListResourceMapper
import com.marketsvrn.model.Place
import com.marketsvrn.placeselect.model.PlaceUi

class PlaceToPlaceUiMapper: AbstractListResourceMapper<Place, PlaceUi>(
    mapListItem = {
        PlaceUi(it.id, it.logo, "", it.name, it.description)
    }
)