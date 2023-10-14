package com.marketsvrn.logincontinue.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.logincontinue.component.FakeLoginContinueComponent
import com.marketsvrn.logincontinue.component.LoginContinueComponent

@Composable
fun LoginContinueScreen(
    component: LoginContinueComponent,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement
            .spacedBy(10.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .width(IntrinsicSize.Max)
    ) {
        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Войти",
                fontSize = 22.sp
            )
        }
        FilledTonalButton(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Продолжить без входа",
                fontSize = 22.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContinueScreenPreview() {
    DeliveryTheme {
        LoginContinueScreen(
            component = FakeLoginContinueComponent(),
            modifier = Modifier.fillMaxSize()
        )
    }
}