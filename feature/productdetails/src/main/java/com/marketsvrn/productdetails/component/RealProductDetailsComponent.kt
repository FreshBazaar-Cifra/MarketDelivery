package com.marketsvrn.productdetails.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.marketsvrn.common.Resource
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Product
import com.marketsvrn.productdetails.model.ProductUi
import com.marketsvrn.productdetails.subcomponents.estimate.EstimateDialogComponent
import com.marketsvrn.productdetails.subcomponents.estimate.RealEstimateDialogComponent
import com.marketsvrn.productdetails.util.ProductToProductUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealProductDetailsComponent(
    componentContext: ComponentContext,
    private val productId: Int
): ProductDetailsComponent, BaseComponent(componentContext) {
    override val estimateDialogComponent: EstimateDialogComponent =
        RealEstimateDialogComponent(
            componentContext = childContext("EstimateDialog", lifecycle),
            productId = productId
        )
    override val product: StateFlow<Resource<ProductUi>>
        get() = MutableStateFlow(ProductToProductUiMapper().map(Resource.success(Product.getStub())))

}