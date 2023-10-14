package com.marketsvrn.productdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductPhotosPager(
    modifier: Modifier = Modifier,
    images: List<String>
) {
    val pagerState = rememberPagerState {
        images.size
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier,
            //pageSpacing = 10.dp
        ) { page: Int ->
            ImagePage(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .aspectRatio(4 / 3f)
                    .clip(RoundedCornerShape(24.dp)),
                image = images[page]
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .padding(vertical = 10.dp),
            pageCount = pagerState.pageCount,
            spacing = 2.dp,
            indicatorWidth = 14.dp,
            indicatorHeight = 14.dp,
            pagerState = pagerState,
            activeColor = ExtendedTheme.colors.onContainer,
            inactiveColor = ExtendedTheme.colors.grayText
        )
    }
}

@Composable
fun ImagePage(
    modifier: Modifier = Modifier,
    image: String
) {
    AsyncImage(
        model = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .border(BorderStroke(2.dp, ExtendedTheme.colors.darkGreen), RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
    )

}