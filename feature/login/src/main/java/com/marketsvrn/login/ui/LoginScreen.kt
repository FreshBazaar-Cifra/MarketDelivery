package com.marketsvrn.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.designsystem.common.ThemedTextField
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.login.component.FakeLoginComponent
import com.marketsvrn.login.component.LoginComponent

@Composable
fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier
) {
    val login by component.loginText.collectAsStateWithLifecycle()
    val password by component.passwordText.collectAsStateWithLifecycle()
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(ExtendedTheme.colors.greenBackground)
                .wrapContentSize()
                .width(IntrinsicSize.Min),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ThemedTextField(
                    value = login,
                    onValueChange = component::updateLoginText,
                    label = {
                        Text(text = "Логин")
                    },
                    placeholder = {
                        Text("Логин")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false
                    )
                )
                ThemedTextField(
                    value = password,
                    onValueChange = component::updatePasswordText,
                    label = {
                        Text("Пароль")
                    },
                    placeholder = {
                        Text("Пароль")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            component.tryLogin()
                        }
                    ),
                    haveShowHideIcon = true
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Button(
                    onClick = component::tryLogin,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExtendedTheme.colors.brightGreen
                    )
                ) {
                    Text(
                        text = "ВОЙТИ",
                        fontWeight = FontWeight.Black,
                        color = ExtendedTheme.colors.onContainer,
                        fontSize = 18.sp
                    )
                }
                Button(
                    onClick = component::navigateRegister,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExtendedTheme.colors.brightGreen
                    )
                ) {
                    Text(
                        text = "ЗАРЕГИСТРИРОВАТЬСЯ",
                        fontWeight = FontWeight.Black,
                        color = ExtendedTheme.colors.onContainer,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    DeliveryTheme {
        LoginScreen(
            component = FakeLoginComponent(),
            modifier = Modifier.screenModifier()
        )
    }
}