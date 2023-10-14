package com.marketsvrn.productdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.designsystem.common.LoadingView
import com.marketsvrn.designsystem.common.ResourceView
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.productdetails.component.FakeProductDetailsComponent
import com.marketsvrn.productdetails.component.ProductDetailsComponent
import com.marketsvrn.productdetails.model.ProductUi

@Composable
fun ProductDetailsScreen(
    component: ProductDetailsComponent,
    modifier: Modifier = Modifier
) {
    val product by component.product.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(ExtendedTheme.colors.greenBackground)
    ) {
        ResourceView(
            loadingView = {
                LoadingView(modifier = Modifier.fillMaxSize())
            },
            errorView = {},
            successView = {
                ProductDetailsView(
                    product = it,
                    modifier = Modifier.fillMaxSize(),
                    onEstimateAction = component::onMakeEstimate
                )
            },
            resource = product
        )
    }
}

@Composable
fun ProductDetailsView(
    modifier: Modifier = Modifier,
    product: ProductUi,
    onEstimateAction: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    val openDialog = remember { mutableStateOf(false) }
    EstimateDialog(
        selectEstimate = {
            onEstimateAction(it)
        },
        onDismiss = {
            openDialog.value = false
        },
        visible = openDialog.value
    )
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            ProductPhotosPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 20.dp)
                    .padding(bottom = 10.dp),
                images = product.images
            )
            EstimateBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                estimate = product.estimate,
                onEstimateAction = {
                    openDialog.value = true
                }
            )
            DetailsCard(
                modifier = Modifier.fillMaxWidth(),
                product = product
            )
        }
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ExtendedTheme.colors.brightGreen
            ),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "400₽+",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = ExtendedTheme.colors.onContainer
            )
        }
    }
}

@Composable
fun DetailsCard(
    modifier: Modifier,
    product: ProductUi
) {
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .padding(horizontal = 10.dp)
            .padding(vertical = 10.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.outlinedCardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        border = BorderStroke(2.dp, ExtendedTheme.colors.darkGreen),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            UpperDetailsCardRow(
                selectedFavorite = true,
                onSelectFavorite = {

                },
                weight = product.weight,
                name = product.name
            )
            AttributesList(
                attributes = product.attributes,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
            )
            DescriptionText(
                description = product.description,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
            )
            ManufacturerRow(
                manufacturer = product.manufacturer,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }
}

@Composable
fun ManufacturerRow(
    modifier: Modifier = Modifier,
    manufacturer: String
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
    ) {
        Text(
            text = "Производитель",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = manufacturer,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    description: String
) {
    Text(
        text = description,
        fontSize = 18.sp,
        modifier = modifier
    )
}

@Composable
fun AttributesList(
    modifier: Modifier = Modifier,
    attributes: List<String>
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
    ) {
        attributes.forEach {
            Text(
                text = it,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun UpperDetailsCardRow(
    modifier: Modifier = Modifier,
    selectedFavorite: Boolean,
    onSelectFavorite: () -> Unit,
    weight: String,
    name: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = weight,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = ExtendedTheme.colors.grayText
            )
            IconButton(
                onClick = onSelectFavorite,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ExtendedTheme.colors.brightGreen)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = ExtendedTheme.colors.container
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsPreview() {
    DeliveryTheme {
        ProductDetailsScreen(component = FakeProductDetailsComponent())
    }
}