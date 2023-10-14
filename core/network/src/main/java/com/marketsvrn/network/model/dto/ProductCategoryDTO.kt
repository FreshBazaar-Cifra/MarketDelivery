package com.marketsvrn.network.model.dto

import com.marketsvrn.model.ProductCategory
import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryDTO(
        val id: Int,
        val name: String
) {
    fun asDomainModel(): ProductCategory{
        return with(this) {
            ProductCategory(id, name)
        }
    }
}
