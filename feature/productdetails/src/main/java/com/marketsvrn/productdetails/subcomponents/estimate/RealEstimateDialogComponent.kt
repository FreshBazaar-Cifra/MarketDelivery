package com.marketsvrn.productdetails.subcomponents.estimate

import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.designsystem.util.BaseComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealEstimateDialogComponent(
    componentContext: ComponentContext,
    private val productId: Int,
): EstimateDialogComponent, BaseComponent(componentContext) {
    private val _visible = MutableStateFlow(false)
    val visible: StateFlow<Boolean>
        get() = _visible

    override fun showDialog(){
        _visible.value = true
    }
    override fun hideDialog(){
        _visible.value = false
    }
    override fun selectEstimate(estimate: Int){

    }
}