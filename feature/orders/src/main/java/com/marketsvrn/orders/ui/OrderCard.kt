package com.marketsvrn.orders.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.Order
import com.marketsvrn.orders.R
import kotlin.math.roundToInt

val CORNER_SHAPE = RoundedCornerShape(18.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OrderCard(
    order: Order,
    selectOrder: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val roundedTotal = remember {
        order.total.roundToInt()
    }
    val dateString = remember {
        with(order.date) {
            val dayOfMonthPadded = dayOfMonth.toString().padStart(2, '0')
            val monthNumberPadded = monthNumber.toString().padStart(2, '0')
            val yearPadded = year.toString().padStart(4, '0')
            var hourPadded: String
            var minutePadded: String
            with(time){
                hourPadded = hour.toString().padStart(2, '0')
                minutePadded = minute.toString().padStart(2, '0')
            }
            "$dayOfMonthPadded.$monthNumberPadded.$yearPadded в $hourPadded:$minutePadded"
        }
    }
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        elevation = CardDefaults.outlinedCardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        border = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
        shape = CORNER_SHAPE,
        onClick = {
            selectOrder(order.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End)
        ) {
            CardImage(
                url = order.market.images[0],
                shape = CORNER_SHAPE,
                borderStroke = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
                modifier = Modifier
                    .width(115.dp)
                    .aspectRatio(1f, true)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp)
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 6.dp)
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    CardUpperRow(
                        text = order.market.name,
                        iconId = R.drawable.geoicon,
                        modifier = Modifier
                        .fillMaxWidth()
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    CardTotalText(
                        modifier = Modifier.weight(1f),
                        text = "$roundedTotal ₽"
                    )
                    CardDateText(dateString)
                }
            }
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
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .border(borderStroke, shape)
                .clip(shape)
        )
    }

}

@Composable
private fun CardUpperIcon(
    iconId: Int,
    modifier: Modifier = Modifier
){
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = null,
        modifier = modifier,
        tint = ExtendedTheme.colors.onContainer
    )
}

@Composable
private fun CardUpperRow(
    text: String,
    iconId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CardUpperIcon(iconId = iconId)
        Text(
            text = text,
            modifier = modifier,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Start,
            style = TextStyle(
                lineBreak = LineBreak.Heading,
            ),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 18.sp,
            color = ExtendedTheme.colors.onContainer
        )
    }
}

@Composable
private fun CardDateText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Start,
        style = TextStyle(
            lineBreak = LineBreak.Heading,
        ),
        overflow = TextOverflow.Ellipsis,
        lineHeight = 14.sp,
        color = ExtendedTheme.colors.onContainer
    )
}

@Composable
private fun CardTotalText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        lineHeight = 16.sp,
        color = ExtendedTheme.colors.darkGreen
    )
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun BigMarketCardPreview() {
    DeliveryTheme {
        OrderCard(
            order = Order.getStub(),
            modifier = Modifier
                .width(400.dp),
            selectOrder = {}
        )
    }
}