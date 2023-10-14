package com.marketsvrn.designsystem.util

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

class NoIndication: Indication {
    private inner class DefaultIndicationInstance : IndicationInstance {

        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return remember(interactionSource) {
            DefaultIndicationInstance()
        }
    }
}