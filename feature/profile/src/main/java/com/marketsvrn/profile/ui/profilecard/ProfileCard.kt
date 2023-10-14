package com.marketsvrn.profile.ui.profilecard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marketsvrn.common.Resource
import com.marketsvrn.designsystem.common.ResourceView
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.model.User

@Composable
internal fun ProfileCard(
    modifier: Modifier = Modifier,
    profileResource: Resource<User>
) {
    ResourceView(
        loadingView = { /*TODO*/ },
        errorView = {},
        successView = {
            ProfileCardLoaded(
                user = it,
                modifier = modifier
            )
        },
        resource = profileResource,
        modifier = modifier
    )
}

@Composable
internal fun ProfileCardLoaded(
    user: User,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        border = BorderStroke(2.dp, Color(0xFF198A32)),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            CardImage(
                url = "",
                shape = RectangleShape,
                borderStroke = BorderStroke(Dp.Unspecified, Color.Black),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .wrapContentWidth()
                    .aspectRatio(0.9f, false)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.1f)
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)
            ) {
                CardNameText(
                    text = "${user.firstName} ${user.lastName}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    color = Color.Black
                )
                UnderlinedField(
                    text = user.email,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = Icons.Filled.Email,
                    noValueText = "Не указан email"
                    //.fillMaxWidth()
                )
                UnderlinedField(
                    text = user.phone,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = Icons.Filled.Phone,
                    noValueText = "Не указан телефон"
                    //.fillMaxWidth()
                )
                UnderlinedField(
                    text = null,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = Icons.Filled.House,
                    noValueText = "Нет города"
                    //.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun UnderlinedField(
    modifier: Modifier = Modifier,
    text: String?,
    leadingIcon: ImageVector,
    noValueText: String
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
            Text(text = text ?: noValueText)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            color = Color(0xFF9CE500)
        )
    }
}

@Composable
private fun CardNameText(
    text: String,
    modifier: Modifier,
    color: Color
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,

        lineHeight = 24.sp,
        color = color
    )
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
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = null,
            modifier = modifier
                .fillMaxSize()
                .border(borderStroke, shape)
                .clip(shape)
        )
    }

}

@Preview
@Composable
private fun ProfileCardPreview() {
    DeliveryTheme {
        ProfileCard(profileResource = Resource.success(User.getStub()))
    }
}