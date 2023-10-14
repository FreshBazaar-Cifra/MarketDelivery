package com.marketsvrn.delivery.navigation.primary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arkivanov.essenty.parcelable.Parcelable
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavConfig
import com.marketsvrn.designsystem.util.NoIndication

data class BottomNavBarItem<C : Parcelable>(
    val selected: Boolean,
    val onClick: () -> Unit,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val labelText: String,
    val accessibilityText: String? = null,
    val config: C
)

@Composable
fun PrimaryNavBar(
    items: List<BottomNavBarItem<out PrimaryNavConfig>>,
    currentDestination: PrimaryNavConfig,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.White)
            //.padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
            .height(60.dp)
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            AddItem(
                item = item,
                currentDestination = currentDestination,
                navigate = item.onClick
            )
        }
    }

}

@Composable
fun AddItem(
    item: BottomNavBarItem<out PrimaryNavConfig>,
    currentDestination: PrimaryNavConfig,
    navigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selected = item.config == currentDestination

    if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else Color.Transparent

    val contentColor =
        if (selected) Color(0xFF9CE500) else Color(0xFFAFAFAF)

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .fillMaxHeight()
            .aspectRatio(1f)
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .clickable(
                onClick = {
                    navigate()
                },
                indication = NoIndication(),
                interactionSource = interactionSource
            )
    ) {
        Icon(
            imageVector = item.selectedIcon,
            contentDescription = item.accessibilityText,
            tint = contentColor,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}
