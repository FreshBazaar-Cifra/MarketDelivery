package com.marketsvrn.designsystem.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marketsvrn.common.Resource
import com.marketsvrn.common.Resource.Status.ERROR
import com.marketsvrn.common.Resource.Status.LOADING
import com.marketsvrn.common.Resource.Status.NOT_LOADING
import com.marketsvrn.common.Resource.Status.SUCCESS

@Composable
fun <T> ResourceView(
    modifier: Modifier = Modifier,
    loadingView: @Composable () -> Unit = {
        LoadingView(modifier = Modifier.fillMaxSize())
    },
    errorView: @Composable (String?) -> Unit,
    successView: @Composable (T) -> Unit,
    notLoadingView: @Composable () -> Unit = {},
    resource: Resource<T>,
    containerColor: Color = Color.Unspecified,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val resourceRemembered = remember(resource) { resource }
    Column(
        modifier = modifier
            //.width(IntrinsicSize.Max)
            //.height(IntrinsicSize.Max)
            .background(containerColor)
            .padding(paddingValues)
    ) {
        when (resourceRemembered.status) {
            LOADING -> {
                loadingView()
            }

            SUCCESS -> {
                resourceRemembered.data?.let { successView(it) }
            }

            ERROR -> {
                errorView(resourceRemembered.message)
            }

            NOT_LOADING -> {
                notLoadingView()
            }
        }
    }
}