package com.example.logintestingapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login Screen")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = uiState.username,
            onValueChange = onUsernameChange,
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().testTag("usernameField")
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().testTag("passwordField")
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth().testTag("loginButton")
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.isLoginSuccess) {
            Text(
                text = "Login berhasil", modifier =
                Modifier.testTag("successMessage"),
                color = Color.White
            )
        }
        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage,
                modifier = Modifier.testTag("errorMessage"),
                color = Color.White
            )
        }
        Text(
            text = "hello",
            color = Color.White
        )
    }
}