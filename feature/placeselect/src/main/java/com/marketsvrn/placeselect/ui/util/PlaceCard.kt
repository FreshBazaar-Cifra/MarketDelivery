package com.marketsvrn.placeselect.ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.placeselect.model.PlaceUi
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceCard(
    place: PlaceUi,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .wrapContentHeight(),
        elevation = CardDefaults.outlinedCardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        onClick = { onClick(place.id) },
        border = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            PlaceCardImage(
                url = place.imageId,
                shape = RoundedCornerShape(30.dp),
                borderStroke = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
                modifier = Modifier
                    .aspectRatio(1.19f, false)
                    //.height(160.dp)
            )
            PlaceCardRating(rating = 2.88f)
            PlaceCardNameText(
                modifier = Modifier.padding(horizontal = 6.dp),
                name = place.name
            )
            Spacer(modifier = Modifier.height(2.dp))
            PlaceCardDescriptionText(
                text = place.description,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .padding(bottom = 12.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun PlaceCardImage(
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

@Composable
fun PlaceCardNameText(
    modifier: Modifier = Modifier,
    name: String
) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        style = TextStyle(
            textAlign = TextAlign.Center
        ),
        textAlign = TextAlign.Center,
        lineHeight = 26.sp,
        color = ExtendedTheme.colors.darkGreen
    )
}

@Composable
fun PlaceCardDescriptionText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center,
        style = TextStyle(
            textAlign = TextAlign.Center
        ),
        lineHeight = 16.sp,
        color = ExtendedTheme.colors.onContainer,
        maxLines = 2,
        minLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun PlaceCardRating(
    modifier: Modifier = Modifier,
    rating: Float
) {
    val ratingLocal = remember {
        (rating * 10.0).roundToInt() / 10.0
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = ExtendedTheme.colors.brightGreen
        )
        Text(text = "$ratingLocal")
    }
}

@Preview
@Composable
fun PlaceCardPreview() {
    DeliveryTheme {
        PlaceCard(
            place = PlaceUi.getStub(),
            onClick = {},
            modifier = Modifier.width(200.dp)
        )
    }
}
