package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int,
    val placeId: Int,
    val description: String,
    val images: List<String>,
    val name: String,
    val price: Float,
    val weight: Int,
    val category: ProductCategoryDTO,
    val estimate: Float?,
    val attributes: List<ProductAttributeDTO>,
    val manufacturer: String
) {
    fun asDomainModel(): Product =
        Product(
            id,
            placeId,
            description,
            images,
            name,
            price,
            weight,
            category.asDomainModel(),
            estimate,
            attributes.map {
                it.asDomainModel()
            },
            manufacturer
        )
}
