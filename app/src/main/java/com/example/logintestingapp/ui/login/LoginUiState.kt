package com.example.logintestingapp.ui.login
data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoginSuccess: Boolean = false,
    val errorMessage: String? = null
)