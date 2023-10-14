package com.marketsvrn.model

data class ProductCategory(
    val id: Int,
    val name: String
) {
    companion object {
        fun getStub(): ProductCategory {
            return ProductCategory(
                id = 0,
                name = "Молочная продукция"
            )
        }
    }
}
