package com.marketsvrn.orderdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.designsystem.common.ErrorView
import com.marketsvrn.designsystem.common.ResourceView
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.model.Position
import com.marketsvrn.orderdetails.component.FakeOrderDetailsComponent
import com.marketsvrn.orderdetails.component.OrderDetailsComponent
import kotlin.math.roundToInt

@Composable
fun OrderDetailsScreen(
    component: OrderDetailsComponent,
    modifier: Modifier = Modifier
) {
    val order by component.order.collectAsStateWithLifecycle()
    val verticalScroll = rememberScrollState()
    ResourceView(
        errorView = {
            ErrorView(message = it ?: "No error message", onRetry = { component.refreshOrders() })
        },
        successView = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .statusBarsPadding()
                    .padding(top = 20.dp)
                    .padding(horizontal = 10.dp)
                    .verticalScroll(verticalScroll),
            ) {
                BigMarketCard(market = it.market)
                OrderProducts(positions = it.positions)
                TotalSupplementaryText()
                TotalPriceText(text = it.total)
                AddressCommentRatingCard(
                    addressString = it.address.getAddressAsString(),
                    comment = "нет",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        resource = order
    )

}

@Composable
fun OrderProducts(
    modifier: Modifier = Modifier,
    positions: List<Position>,

    ) {
    OutlinedCard(
        modifier = modifier
            .wrapContentSize(),
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        border = BorderStroke(2.dp, Color(0xFF198A32)),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            positions.forEach{
                ProductCard(
                    position = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun ProductCard(
    position: Position,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.End)
    ) {
        ProductImage(
            url = position.product.images[0],
            shape = RoundedCornerShape(18.dp),
            borderStroke = BorderStroke(Dp.Unspecified, Color.Unspecified)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ProductNameText(
                    name = position.product.name,
                    modifier = Modifier.fillMaxWidth()
                )
                ProductWeightText(weight = "${position.product.weight} гр.")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.End)
            ) {
                Text(text = "2 шт.", fontSize = 14.sp)
                ProductPriceText(price = "${position.product.price.roundToInt()}₽")
            }

        }
    }
}

@Composable
fun ProductNameText(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        fontSize = 14.sp,
        modifier = modifier
    )
}

@Composable
fun ProductWeightText(
    weight: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = weight,
        fontSize = 14.sp,
        modifier = modifier,
        color = ExtendedTheme.colors.brightGreen
    )
}

@Composable
fun ProductPriceText(
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = price,
        fontSize = 14.sp,
        modifier = modifier,
        color = ExtendedTheme.colors.darkGreen
    )
}

@Composable
fun TotalSupplementaryText(
    modifier: Modifier = Modifier
) {
    Text(
        text = "ИТОГО",
        modifier = modifier,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TotalPriceText(
    modifier: Modifier = Modifier,
    text: Float
) {
    Text(
        text = "${text.roundToInt()}₽",
        modifier = modifier,
        fontSize = 50.sp,
        fontWeight = FontWeight.Black,
        color = ExtendedTheme.colors.darkGreen
    )
}

@Composable
fun AddressCommentRatingCard(
    modifier: Modifier = Modifier,
    addressString: String,
    comment: String
) {
    val addressStr = remember {
        "Адрес: $addressString"
    }
    val commentStr = remember {
        "Комментарий: $comment"
    }
    OutlinedCard(
        modifier = modifier,
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        border = BorderStroke(2.dp, Color(0xFF198A32)),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ){
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = addressStr,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = commentStr,
                lineHeight = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
        }

    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=4000px,dpi=440")
@Composable
fun OrderDetailsPreview() {
    DeliveryTheme {
        OrderDetailsScreen(
            component = FakeOrderDetailsComponent(),
            modifier = Modifier.screenModifier()
        )
    }
}