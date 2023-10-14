package com.marketsvrn.productdetails.component

import com.marketsvrn.common.Resource
import com.marketsvrn.productdetails.model.ProductUi
import com.marketsvrn.productdetails.subcomponents.estimate.EstimateDialogComponent
import kotlinx.coroutines.flow.StateFlow

interface ProductDetailsComponent {
    fun onMakeEstimate(estimate: Int) {

    }
    val estimateDialogComponent: EstimateDialogComponent
    val product: StateFlow<Resource<ProductUi>>
}