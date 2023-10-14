package com.marketsvrn.placeselect.model

import androidx.compose.runtime.Stable
import com.marketsvrn.model.Place

@Stable
data class PlaceUi(
    val id: Int,
    val imageId: String,
    val rating: String,
    val name: String,
    val description: String
) {
    companion object {
        fun getStub(): PlaceUi{
            val placeStub = Place.getStub()
            return PlaceUi(
                id = placeStub.id,
                imageId = placeStub.logo,
                rating = "",
                name = placeStub.name,
                description = placeStub.description
            )
        }
    }
}
