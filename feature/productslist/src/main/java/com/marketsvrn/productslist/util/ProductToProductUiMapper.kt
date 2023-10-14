package com.marketsvrn.productslist.util

import com.marketsvrn.common.AbstractListResourceMapper
import com.marketsvrn.model.Product
import com.marketsvrn.productslist.model.ProductUi
import kotlin.math.roundToInt

class ProductToProductUiMapper : AbstractListResourceMapper<Product, ProductUi>(
    mapListItem = {
        val priceRounded = it.price.roundToInt()
        ProductUi(
            id = it.id,
            image = it.images[0],
            name = it.name,
            weight = "${it.weight}",
            price = "$priceRounded ₽",
            estimate = "${(it.estimate * 10.0).roundToInt() / 10.0}"
        )
    }
)