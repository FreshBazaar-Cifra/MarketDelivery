package com.marketsvrn.productslist.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.ProductCategory
import com.marketsvrn.productslist.R

@Composable
fun CategoriesRow(
    onSelectSort: () -> Unit,
    modifier: Modifier = Modifier,
    categories: List<ProductCategory>
) {
    val sortSelected = remember {
        mutableStateOf(false)
    }
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            SortFilterChip(
                selected = sortSelected.value,
                onSelect = onSelectSort
            )
        }
        items(count = categories.size){
            CustomFilterChip(
                selected = true,
                onSelect = {

                },
                onUnselect = {

                },
                text = categories[it].name
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortFilterChip(
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        IconButton(
            onClick = onSelect,
            modifier = modifier
                .clip(CircleShape)
                .background(ExtendedTheme.colors.brightGreen)
                .height(36.dp)
                .widthIn(min = 60.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .widthIn(min = 50.dp),
                propagateMinConstraints = true
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrows),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .widthIn(min = 50.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun SortFilterChipPreview() {
    DeliveryTheme {
        SortFilterChip(selected = true, onSelect = { /*TODO*/ })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFilterChip(
    selected: Boolean,
    onSelect: () -> Unit,
    onUnselect: () -> Unit,
    text: String
) {
    FilterChip(
        selected = selected,
        onClick = {
            if (selected) onUnselect() else onSelect()
        },
        label = {
            TextInCustomChip(text = text)
        },
        shape = CircleShape,
        modifier = Modifier.height(36.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = ExtendedTheme.colors.brightGreen,
            containerColor = ExtendedTheme.colors.brightGreen.copy(alpha = .25f)
        )
    )
}

@Composable
fun TextInCustomChip(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CategoriesRowPreview() {
    DeliveryTheme {
        CategoriesRow(
            onSelectSort = { /*TODO*/ },
            categories = listOf(
                ProductCategory(0, "Мясо"),
                ProductCategory(1, "Молочные продукты"),
                ProductCategory(2, "Минеральная вода"),
            )
        )
    }
}