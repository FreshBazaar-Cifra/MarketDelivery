package com.marketsvrn.marketselect.model

import androidx.compose.runtime.Stable

@Stable
data class MarketUi(
    val id: Int,
    val name: String,
    val time: String,
    val address: String,
    val imageId: String
)
