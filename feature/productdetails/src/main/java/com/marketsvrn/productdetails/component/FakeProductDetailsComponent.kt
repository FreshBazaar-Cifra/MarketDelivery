package com.marketsvrn.productdetails.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import com.marketsvrn.productdetails.model.ProductUi
import com.marketsvrn.productdetails.subcomponents.estimate.EstimateDialogComponent
import com.marketsvrn.productdetails.subcomponents.estimate.FakeEstimateDialogComponent
import com.marketsvrn.productdetails.util.ProductToProductUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProductDetailsComponent: ProductDetailsComponent {
    override val estimateDialogComponent: EstimateDialogComponent
        get() = FakeEstimateDialogComponent()
    override val product: StateFlow<Resource<ProductUi>>
        get() = MutableStateFlow(ProductToProductUiMapper().map(Resource.success(Product.getStub())))
}