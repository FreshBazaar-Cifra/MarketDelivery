package com.marketsvrn.model

data class Attribute(
    val key: String,
    val value: String,
    val productId: Int
) {
    companion object {
        fun getStub(): Attribute {
            return Attribute(
                key = "",
                value = "Из натуральных ингредиентов",
                productId = 0
            )
        }
    }
}
