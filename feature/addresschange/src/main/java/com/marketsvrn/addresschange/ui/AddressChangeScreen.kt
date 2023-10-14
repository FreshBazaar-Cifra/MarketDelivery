package com.marketsvrn.addresschange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.marketsvrn.addresschange.component.AddressChangeComponent
import com.marketsvrn.designsystem.common.ScreenHeaderText

@Composable
fun AddressChangeScreen(
    component: AddressChangeComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ScreenHeaderText(text = "Адрес")
    }
}