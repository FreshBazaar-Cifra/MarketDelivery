package com.marketsvrn.productdetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.util.NoIndication
import com.marketsvrn.productdetails.subcomponents.estimate.RealEstimateDialogComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstimateDialog(
    selectEstimate: (Int) -> Unit,
    onDismiss: () -> Unit,
    visible: Boolean
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val indication = remember {
        NoIndication()
    }
    if (visible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(dismissOnClickOutside = true),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable(interactionSource, indication = indication) {
                    onDismiss()
                }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(52.dp),
                tonalElevation = AlertDialogDefaults.TonalElevation,
                color = ExtendedTheme.colors.container
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max)
                        .padding(20.dp)
                        .padding(horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Оцените товар",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        repeat(5) {
                            IconButton(onClick = { selectEstimate(it + 1) }) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = ExtendedTheme.colors.brightGreen,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                        }
                    }
                }
                
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstimateDialog(
    component: RealEstimateDialogComponent
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val indication = remember {
        NoIndication()
    }
    val visible by component.visible.collectAsStateWithLifecycle()
    if (visible) {
        AlertDialog(
            onDismissRequest = component::hideDialog,
            properties = DialogProperties(dismissOnClickOutside = true),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable(interactionSource, indication = indication) {
                    component.hideDialog()
                }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(52.dp),
                tonalElevation = AlertDialogDefaults.TonalElevation,
                color = ExtendedTheme.colors.container
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max)
                        .padding(20.dp)
                        .padding(horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Оцените товар",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        repeat(5) {
                            IconButton(onClick = { component.selectEstimate(it + 1) }) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = ExtendedTheme.colors.brightGreen,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}