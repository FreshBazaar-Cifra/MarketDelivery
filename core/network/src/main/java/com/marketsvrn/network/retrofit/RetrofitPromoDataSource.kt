package com.marketsvrn.network.retrofit

import com.marketsvrn.network.api.PromoApi
import com.marketsvrn.network.datasource.PromoDataSource
import com.marketsvrn.network.util.BaseDataSource

class RetrofitPromoDataSource(
    private val api: PromoApi
) : PromoDataSource, BaseDataSource() {

}