package com.marketsvrn.productdetails.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.doOnResume
import com.marketsvrn.common.Resource
import com.marketsvrn.data.repository.products.ProductsRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Product
import com.marketsvrn.productdetails.model.ProductUi
import com.marketsvrn.productdetails.subcomponents.estimate.EstimateDialogComponent
import com.marketsvrn.productdetails.subcomponents.estimate.RealEstimateDialogComponent
import com.marketsvrn.productdetails.util.ProductToProductUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.inject

class RealProductDetailsComponent(
    componentContext: ComponentContext,
    private val productId: Int
): ProductDetailsComponent, BaseComponent(componentContext) {
    private val repo: ProductsRepository by inject()


    // Never destroy the lifecycle of a permanent child component!
    private val dialogLifecycle = LifecycleRegistry()
    override val estimateDialogComponent: EstimateDialogComponent =
        RealEstimateDialogComponent(
            componentContext = childContext("EstimateDialog", dialogLifecycle),
            productId = productId
        )
    private val _product: MutableStateFlow<Resource<ProductUi>> = MutableStateFlow(ProductToProductUiMapper().map(Resource.success(Product.getStub())))
    override val product: StateFlow<Resource<ProductUi>>
        get() = _product

    override fun refreshProduct() {
        /*repo.getProductById(productId)
            .onStart {
                _product.value = Resource.loading()
            }
            .onEach {
                _product.value = ProductToProductUiMapper().map(it)
            }
            .catch { ex ->
                ex.localizedMessage?.let { _product.value = Resource.error(ex.message ?: "No error message") }
            }
            .launchIn(ioScope)*/
    }


    init {
        lifecycle.doOnResume {
            refreshProduct()
        }
        lifecycle.doOnDestroy {
            dialogLifecycle.destroy()
        }
    }
}