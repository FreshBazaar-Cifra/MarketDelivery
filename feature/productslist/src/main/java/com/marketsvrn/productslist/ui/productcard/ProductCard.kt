package com.marketsvrn.productslist.ui.productcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.productslist.model.ProductUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductCard(
    product: ProductUi,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    onBuyClick: (Int) -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        onClick = {
            onClick(product.id)
        },
        border = BorderStroke(width = 0.dp, color = Color.Unspecified),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 4.dp)
        ) {
            CardImage(
                url = product.image,
                shape = RoundedCornerShape(30.dp),
                borderStroke = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
                modifier = Modifier
                    .clip(CardDefaults.elevatedShape)
                    .aspectRatio(1f, false)
                    .height(160.dp)
            )
            //CardRating(rating = 4.55f)
            CardNameText(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
                name = product.name
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
            ) {
                CardWeightText(
                    address = product.weight,
                    modifier = Modifier.weight(1f)
                )
                CardRating(
                    estimate = product.estimate,
                    modifier = Modifier.wrapContentWidth()
                )
            }

            CardBuyButton(
                text = product.price,
                onClick = {
                    onBuyClick(product.id)
                },
                modifier = Modifier.height(30.dp)
            )
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
    estimate: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color(0xFF9CE500)
        )
        Text(text = estimate)
    }
}

@Composable
private fun CardNameText(
    modifier: Modifier = Modifier,
    name: String
) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Start,
        style = TextStyle(
            textAlign = TextAlign.Start
        ),
        lineHeight = 18.sp,
        color = ExtendedTheme.colors.onContainer,
        maxLines = 2
    )
}

@Composable
private fun CardWeightText(
    address: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = address,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Start,
        lineHeight = 16.sp,
        maxLines = 2
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardBuyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Button(
            onClick = onClick,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = ExtendedTheme.colors.brightGreen
            ),
            modifier = modifier,
            contentPadding = PaddingValues(
                horizontal = 6.dp, vertical = 2.dp
            )
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                color = ExtendedTheme.colors.onContainer
            )
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = ExtendedTheme.colors.onContainer
            )
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    DeliveryTheme {
        ProductCard(product = ProductUi.getStub(), onClick = {}, onBuyClick = {})
    }
}
