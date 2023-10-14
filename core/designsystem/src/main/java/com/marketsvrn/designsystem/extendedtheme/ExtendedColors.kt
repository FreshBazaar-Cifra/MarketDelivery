package com.marketsvrn.designsystem.extendedtheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val grayText: Color,
    val greenBackground: Color,
    val brightGreen: Color,
    val darkGreen: Color,
    val onContainer: Color,
    val container: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        grayText = Color.Unspecified,
        greenBackground = Color.Unspecified,
        brightGreen = Color.Unspecified,
        darkGreen = Color.Unspecified,
        onContainer = Color.Unspecified,
        container = Color.Unspecified,
    )
}

val GreenBackgroundLight = Color(0xFFF5FFF6)
val BrightGreenLight = Color(0xFF9CE500)
val DarkGreenLight = Color(0xFF198A32)
val OnContainerLight = Color(0xFF000000)
val ContainerLight = Color(0xFFFFFFFF)
val GrayTextLight = Color(0xFF7A807B)
val GreenBackgroundDark = Color(0xFFF5FFF6)
val BrightGreenDark = Color(0xFFF5FFF6)
val DarkGreenDark = Color(0xFFF5FFF6)
val OnContainerDark = Color(0xFFF5FFF6)
val ContainerDark = Color(0xFFF5FFF6)
val GrayTextDark = Color(0xFFFFFFFF)

val extendedColorsLight = ExtendedColors(
    grayText = GrayTextLight,
    greenBackground = GreenBackgroundLight,
    brightGreen = BrightGreenLight,
    darkGreen = DarkGreenLight,
    onContainer = OnContainerLight,
    container = ContainerLight,
)

val extendedColorsDark = ExtendedColors(
    grayText = GrayTextDark,
    greenBackground = GreenBackgroundDark,
    brightGreen = BrightGreenDark,
    darkGreen = DarkGreenDark,
    onContainer = OnContainerDark,
    container = ContainerDark,
)