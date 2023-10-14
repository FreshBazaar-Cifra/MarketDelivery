package com.marketsvrn.designsystem.extendedtheme

import androidx.compose.runtime.Composable

// Use with eg. ExtendedTheme.colors.tertiary
object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}