package com.marketsvrn.orderdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    url: String,
    shape: Shape,
    borderStroke: BorderStroke
) {
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(80.dp)
                .border(borderStroke, shape)
                .clip(shape)
                .aspectRatio(1f)
        )
    }

}