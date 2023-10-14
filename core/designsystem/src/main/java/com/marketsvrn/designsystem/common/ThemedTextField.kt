package com.marketsvrn.designsystem.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    haveShowHideIcon: Boolean = false
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = ExtendedTheme.colors.darkGreen
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    val colors = TextFieldDefaults.colors(
        // TODO цвета
        focusedContainerColor = ExtendedTheme.colors.container,
        unfocusedContainerColor = ExtendedTheme.colors.container,
        disabledContainerColor = ExtendedTheme.colors.container,
        errorContainerColor = ExtendedTheme.colors.container
    )
    val shape = remember {
        RoundedCornerShape(12.dp)
    }
    val showField = remember { mutableStateOf(!haveShowHideIcon) }
    var composableF: @Composable (() -> Unit)? = null
    if (haveShowHideIcon) {
        composableF = {
            IconButton(onClick = {
                showField.value = !showField.value
            }) {
                Icon(
                    imageVector = if (showField.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = if (showField.value) "Show Password" else "Hide Password"
                )
            }
        }
    }
    val visualTransformation = remember(showField.value) {
        if (showField.value) VisualTransformation.None else PasswordVisualTransformation()
    }

    BasicTextField(
        value = value,
        modifier = modifier
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = 16.dp
            ),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = false,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(ExtendedTheme.colors.darkGreen),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = null,
                leadingIcon = null,
                trailingIcon = composableF,
                prefix = null,
                suffix = null,
                supportingText = null,
                singleLine = true,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                container = {
                    ContainerBox(
                        shape,
                        borderThickness = 2.dp
                    )
                }
            )
        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun ContainerBox(
    shape: Shape,
    borderThickness: Dp
) {
    Box(
        Modifier
            .border(BorderStroke(borderThickness, ExtendedTheme.colors.darkGreen), shape)
            .background(
                ExtendedTheme.colors.container, shape
            )
    )
}

