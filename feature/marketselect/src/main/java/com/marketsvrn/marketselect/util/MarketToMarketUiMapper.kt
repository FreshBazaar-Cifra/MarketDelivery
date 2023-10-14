package com.marketsvrn.marketselect.util

import com.marketsvrn.common.AbstractListResourceMapper
import com.marketsvrn.marketselect.model.MarketUi
import com.marketsvrn.model.Market

class MarketToMarketUiMapper: AbstractListResourceMapper<Market, MarketUi>(
    mapListItem = {
        MarketUi(
            id = it.id,
            name = it.name,
            address = it.address.getAddressAsString(),
            time = "Завтра с 10:00",
            imageId = it.images[0]
        )
    }
)