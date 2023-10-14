package com.marketsvrn.productslist.model

import androidx.compose.runtime.Stable

@Stable
data class ProductUi(
    val id: Int,
    val image: String,
    val name: String,
    val weight: String,
    val price: String,
    val estimate: String
) {
    companion object {
        fun getStub(): ProductUi {
            return ProductUi(
                id = 1,
                image = "",
                name = "Название товара",
                weight = "500 гр.",
                price = "500 ₽",
                estimate = "5"
            )
        }
    }
}
