package com.marketsvrn.register.ui

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
import androidx.compose.material3.FilledTonalButton
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
import com.marketsvrn.register.component.FakeRegisterComponent
import com.marketsvrn.register.component.RegisterComponent

@Composable
fun RegisterScreen(
    component: RegisterComponent,
    modifier: Modifier = Modifier
) {
    val login by component.loginText.collectAsStateWithLifecycle()
    val password by component.passwordText.collectAsStateWithLifecycle()
    val firstName by component.firstNameText.collectAsStateWithLifecycle()
    val lastName by component.lastNameText.collectAsStateWithLifecycle()
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
                    value = firstName,
                    onValueChange = component::updateFirstNameText,
                    label = {
                        Text(text = "Имя")
                    },
                    placeholder = {
                        Text(text = "Имя")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true
                    )
                )
                ThemedTextField(
                    value = lastName,
                    onValueChange = component::updateLastNameText,
                    label = {
                        Text(text = "Фамилия")
                    },
                    placeholder = {
                        Text(text = "Фамилия")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true
                    )
                )
                ThemedTextField(
                    value = login,
                    onValueChange = component::updateLoginText,
                    label = {
                        Text(text = "Логин")
                    },
                    placeholder = {
                        Text(text = "Логин")
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
                            component.tryRegister()
                        }
                    ),
                    haveShowHideIcon = false
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Button(
                    onClick = component::tryRegister,
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
                FilledTonalButton(
                    onClick = component::navigateLogin,
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
            }

        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    DeliveryTheme {
        RegisterScreen(
            component = FakeRegisterComponent(),
            modifier = Modifier.screenModifier()
        )
    }
}