package com.marketsvrn.productdetails.util

import com.marketsvrn.common.AbstractResourceMapper
import com.marketsvrn.model.Attribute
import com.marketsvrn.model.Product
import com.marketsvrn.productdetails.model.ProductUi

class ProductToProductUiMapper : AbstractResourceMapper<Product, ProductUi>(
    map = {
        with(it) {
            ProductUi(
                id,
                placeId,
                description,
                images,
                name,
                price,
                getWeightString(weight),
                category,
                estimate,
                getAttributes(attributes),
                manufacturer
            )
        }

    }
) {
    companion object {
        private fun getWeightString(weight: Int): String{
            if (weight < 1000) {
                return "$weight г"
            }
            return "${weight.floorDiv(1000)}.${weight.rem(1000)}"
        }
        private fun getAttributes(attributes: List<Attribute>): List<String>{
            return attributes.map {
                " •   ${it.value}"
            }
        }
    }
}