package com.example.logintestingapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.logintestingapp.data.FakeAuthRepository
import com.example.logintestingapp.ui.login.LoginScreen
import com.example.logintestingapp.ui.login.LoginViewModel

import androidx.compose.material3.MaterialTheme
class MainActivity : ComponentActivity() {
    private val viewModel = LoginViewModel(authRepository =
        FakeAuthRepository())
    private var uiState by mutableStateOf(viewModel.uiState)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoginScreen(
                    uiState = uiState,
                    onUsernameChange = {
                        viewModel.onUsernameChange(it)
                        uiState = viewModel.uiState
                    },
                    onPasswordChange = {
                        viewModel.onPasswordChange(it)
                        uiState = viewModel.uiState
                    },
                    onLoginClick = {
                        viewModel.login()
                        uiState = viewModel.uiState
                    }
                )
            }
        }
    }
}