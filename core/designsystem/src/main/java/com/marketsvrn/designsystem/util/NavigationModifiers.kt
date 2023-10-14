package com.marketsvrn.designsystem.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme

fun Modifier.screenModifier() =
    composed {
        this
            .fillMaxSize()
            .background(ExtendedTheme.colors.greenBackground)
    }