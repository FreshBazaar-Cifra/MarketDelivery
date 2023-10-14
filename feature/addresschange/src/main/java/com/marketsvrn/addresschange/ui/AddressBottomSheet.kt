package com.marketsvrn.addresschange.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBottomSheet(
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
        AndroidView(
            factory = {
                MapView(it).apply {
                    map.move(
                        CameraPosition(
                            Point(51.672, 39.1843),
                            14.0f, 0.0f, 0.0f
                        ),
                        Animation(Animation.Type.SMOOTH, 5F),
                        null
                    )
                }
            },
            modifier = modifier
        )
    }

}