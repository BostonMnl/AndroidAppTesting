package com.example.logintestingapp.ui.login
import androidx.lifecycle.ViewModel
import com.example.logintestingapp.data.AuthRepository
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState = LoginUiState()
        private set
    fun onUsernameChange(value: String) {
        uiState = uiState.copy(username = value, errorMessage = null)
    }
    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value, errorMessage = null)
    }
    fun login() {
        if (uiState.username.isBlank()) {
            uiState = uiState.copy(
                isLoginSuccess = false,
                errorMessage = "Username tidak boleh kosong"
            )
            return
        }
        if (uiState.password.isBlank()) {
            uiState = uiState.copy(
                isLoginSuccess = false,
                errorMessage = "Password tidak boleh kosong"
            )
            return
        }
        val result = authRepository.login(
            username = uiState.username,
            password = uiState.password
        )
        uiState = if (result) {
            uiState.copy(isLoginSuccess = true, errorMessage = null)
        } else {
            uiState.copy(
                isLoginSuccess = false,
                errorMessage = "Username atau password salah"
            )
        }
    }
}