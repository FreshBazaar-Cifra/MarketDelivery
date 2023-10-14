package com.marketsvrn.designsystem.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marketsvrn.designsystem.R

val PROMOTE_TEXT_COLOR = Color(0xFF198A32)
val SEARCH_BAR_COLOR = Color(0xFF9CE500)
val SEARCH_BAR_BG_COLOR = Color(0xFFFFFFFF)
val SEARCH_BAR_TEXT_STYLE = TextStyle(
    color = SEARCH_BAR_COLOR,
    fontSize = 24.sp
)

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    query: State<String>,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    placeholderText: String
) {
    val queryVal by query
    OutlinedTextField(
        value = queryVal,
        onValueChange = onQueryChange,
        textStyle = SEARCH_BAR_TEXT_STYLE,
        placeholder = {
            Text(
                text = placeholderText,
                style = SEARCH_BAR_TEXT_STYLE,
                fontWeight = FontWeight.SemiBold
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                tint = PROMOTE_TEXT_COLOR,
                contentDescription = null,
                modifier = Modifier.wrapContentSize().offset(x = 5.dp)
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(queryVal)
            }
        ),
        modifier = modifier
            .border(2.dp, SEARCH_BAR_COLOR, RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SEARCH_BAR_BG_COLOR,
            unfocusedContainerColor = SEARCH_BAR_BG_COLOR,
            errorContainerColor = SEARCH_BAR_BG_COLOR,
            disabledContainerColor = SEARCH_BAR_BG_COLOR
        ),
        singleLine = true
    )
}