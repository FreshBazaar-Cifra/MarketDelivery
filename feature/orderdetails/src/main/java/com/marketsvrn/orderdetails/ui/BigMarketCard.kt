package com.marketsvrn.orderdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.Market
import kotlin.math.roundToInt

const val CARD_NAME_TEXT_COLOR = 0xFF198A32

@Composable
internal fun BigMarketCard(
    market: Market,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .wrapContentSize()
        /*.wrapContentWidth()*/,
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        border = BorderStroke(2.dp, Color(0xFF198A32)),
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            CardImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .sizeIn(minWidth = 100.dp)
                    .aspectRatio(0.9f, true),
                url = market.images[0]
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.1f)
                    .padding(horizontal = 4.dp)
                    .padding(vertical = 6.dp)
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)

            ) {
                CardNameText(
                    name = market.name,
                    modifier = Modifier,
                    color = Color(CARD_NAME_TEXT_COLOR)
                )
                CardAddressText(
                    address = market.address.getAddressAsString(),
                    modifier = Modifier
                    //.fillMaxWidth()
                )
                CardRating(
                    modifier = Modifier,
                    rating = 4.555f
                )
            }
        }

    }
}

@Composable
private fun CardImage(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize()
                .border(BorderStroke(2.dp, Color(0xFF198A32)), RoundedCornerShape(30.dp))
                .clip(RoundedCornerShape(30.dp))
        )
    }

}

@Composable
private fun CardRating(
    modifier: Modifier = Modifier,
    rating: Float
) {
    val ratingLocal = remember {
        (rating * 10.0).roundToInt() / 10.0
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
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
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
        color = color
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
        fontWeight = FontWeight.W300,
        textAlign = TextAlign.Center,
        lineHeight = 16.sp
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
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center,
        lineHeight = 12.sp,
        color = color
    )
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun BigMarketCardPreview() {
    DeliveryTheme {
        BigMarketCard(
            market = Market.getStub(),
            modifier = Modifier
                .width(834.dp)
                .height(410.dp)
        )
    }
}