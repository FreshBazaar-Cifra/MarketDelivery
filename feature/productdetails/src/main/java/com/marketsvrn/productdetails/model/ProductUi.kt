package com.marketsvrn.productdetails.model

import androidx.compose.runtime.Immutable
import com.marketsvrn.model.ProductCategory

@Immutable
data class ProductUi(
    val id: Int,
    val placeId: Int,
    val description: String,
    val images: List<String>,
    val name: String,
    val price: Float,
    val weight: String,
    val category: ProductCategory,
    val estimate: Float?,
    val attributes: List<String>,
    val manufacturer: String
)
