package com.marketsvrn.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.onboarding.component.FakeOnBoardingComponent
import com.marketsvrn.onboarding.component.OnBoardingComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    component: OnBoardingComponent,
    modifier: Modifier = Modifier
) {
    val page by component.page.collectAsState()
    val pagerState = rememberPagerState {
        3
    }
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = modifier
    ) {

    }
}

@Preview
@Composable
fun OnBoardingPreview() {
    DeliveryTheme {
        OnBoardingScreen(component = FakeOnBoardingComponent())
    }
}