package com.marketsvrn.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.common.Resource
import com.marketsvrn.designsystem.common.ScreenHeaderText
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.User
import com.marketsvrn.profile.component.FakeProfileComponent
import com.marketsvrn.profile.component.ProfileComponent
import com.marketsvrn.profile.ui.buttoncard.ButtonCard
import com.marketsvrn.profile.ui.profilecard.ProfileCard

@Composable
fun ProfileScreen(
    component: ProfileComponent
) {
    val profile = component.profile.collectAsStateWithLifecycle()
    MainProfileScreen(
        modifier = Modifier
            .background(Color(0xFFF5FFF6))
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
            .statusBarsPadding()
            .waterfallPadding(),
        profileResource = profile.value,
        onLogOut = component::onLogOut,
        onReview = component::onReview,
        onSupport = component::onSupport
    )
}

@Composable
private fun MainProfileScreen(
    modifier: Modifier = Modifier,
    profileResource: Resource<User>,
    onLogOut: () -> Unit,
    onReview: () -> Unit,
    onSupport: () -> Unit
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        ScreenHeaderText(text = "Профиль")
        ProfileCard(
            profileResource = profileResource,
            modifier = Modifier
                .fillMaxWidth()
        )
        ButtonCard(
            icon = Icons.Filled.RateReview,
            text = "Оставить отзыв",
            onClick = onReview,
            modifier = Modifier.fillMaxWidth()
        )
        ButtonCard(
            icon = Icons.Filled.SupportAgent,
            text = "Тех. поддержка",
            onClick = onSupport,
            modifier = Modifier.fillMaxWidth()
        )
        ButtonCard(
            icon = Icons.Filled.ExitToApp,
            text = "Выход из аккаунта",
            onClick = onLogOut,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    DeliveryTheme {
        ProfileScreen(component = FakeProfileComponent())
    }
}