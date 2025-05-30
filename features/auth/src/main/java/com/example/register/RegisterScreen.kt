package com.example.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.AuthEvent
import com.example.core.ui.TitleText
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
internal fun RegisterScreen(
    onRegistrationComplete: (String, String, String) -> Unit,
) {
    RegisterScreenContent(
        onRegistrationComplete = onRegistrationComplete
    )
}

@Composable
private fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    onRegistrationComplete: (String, String, String) -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText("Регистрация")
        Spacer(modifier = Modifier.size(40.dp))
        OutlinedTextField(
            value = username,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
                focusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
                errorContainerColor = RemapAppTheme.colorScheme.brandBackground,
                disabledContainerColor = RemapAppTheme.colorScheme.brandBackground,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            ),
            onValueChange = {
                username = it
            },
            label = { Text(text = "Имя пользователя") },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            value = email,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
                focusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
                errorContainerColor = RemapAppTheme.colorScheme.brandBackground,
                disabledContainerColor = RemapAppTheme.colorScheme.brandBackground,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            ),
            onValueChange = {
                email = it
            },
            label = { Text(text = "Почта") },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Пароль") },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = null
                )
            },
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
                focusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
                errorContainerColor = RemapAppTheme.colorScheme.brandBackground,
                disabledContainerColor = RemapAppTheme.colorScheme.brandBackground,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(60.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = RemapAppTheme.colorScheme.brandActive
            ),
            onClick = {
                onRegistrationComplete(
                    username, email, password
                )
            }
        ) {
            Text(text = "Зарегистрироваться", style = RemapAppTheme.typography.subheading1)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenContentPreview() {
    RemapTheme {
        RegisterScreenContent(
            modifier = Modifier,
            onRegistrationComplete = { _, _, _ ->}
        )
    }
}