package com.marketsvrn.model

data class Position(
    val product: Product,
    val count: Int,
) {
    companion object {
        fun getStub(): Position {
            return Position(product = Product.getStub(), 5)
        }
    }
}
