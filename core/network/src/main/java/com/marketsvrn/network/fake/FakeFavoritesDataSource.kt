package com.marketsvrn.network.fake

import com.marketsvrn.common.Resource
import com.marketsvrn.network.datasource.FavoriteDataSource
import com.marketsvrn.network.model.dto.ProductDTO
import com.marketsvrn.network.util.BaseDataSource
import retrofit2.Response

class FakeFavoritesDataSource: FavoriteDataSource, BaseDataSource() {
    override suspend fun getFavorites(): Resource<List<ProductDTO>> {
        return getResult {
            Response.success(List(10) {
                FakeModels.FAKE_PRODUCT_DTO.copy(
                    id = it
                )
            })
        }
    }

    override suspend fun deleteFavorite(productId: Int): Resource<String> {
        return getResult {
            Response.success("Успешно")
        }
    }

    override suspend fun addFavorite(productId: Int): Resource<String> {
        return getResult {
            Response.success("Успешно")
        }
    }
}