package com.marketsvrn.marketselect.ui.marketcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.marketselect.model.MarketUi
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MarketCard(
    market: MarketUi,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        onClick = {
            onClick(market.id)
        },
        border = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            CardImage(
                url = market.imageId,
                shape = RoundedCornerShape(30.dp),
                borderStroke = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
                modifier = Modifier
                    .clip(CardDefaults.elevatedShape)
                    .aspectRatio(1.19f, false)
                    .height(160.dp)
            )
            //CardRating(rating = 4.55f)
            CardNameText(
                name = market.name,
                modifier = Modifier.padding(horizontal = 6.dp),
                color = ExtendedTheme.colors.darkGreen
            )
            Spacer(modifier = Modifier.height(4.dp))
            CardAddressText(
                address = market.address,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            CardOpenTimeText(
                text = market.time,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
private fun CardImage(
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
        )
    }

}

@Suppress("unused")
@Composable
private fun CardRating(
    modifier: Modifier = Modifier,
    rating: Float
) {
    val ratingLocal = remember {
        (rating * 10.0).roundToInt() / 10.0
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color(0xFF9CE500)
        )
        Text(text = "$ratingLocal")
    }
}

@Composable
private fun CardNameText(
    modifier: Modifier = Modifier,
    name: String,
    color: Color
) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 22.sp,
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center,
        lineHeight = 26.sp,
        color = color,
        maxLines = 2
    )
}

@Composable
private fun CardAddressText(
    address: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = address,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center,
        lineHeight = 16.sp,
        maxLines = 2
    )
}

@Composable
private fun CardOpenTimeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        lineHeight = 12.sp,
        color = color
    )
}
