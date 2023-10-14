package com.marketsvrn.profilesettings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.profilesettings.component.FakeProfileSettingsComponent
import com.marketsvrn.profilesettings.component.ProfileSettingsComponent

@Composable
fun ProfileSettingsScreen(
    component: ProfileSettingsComponent
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Настройки профиля")
    }
}

@Preview
@Composable
fun ProfileSettingsPreview() {
    DeliveryTheme {
        ProfileSettingsScreen(component = FakeProfileSettingsComponent())
    }
}