package com.example.logintestingapp.ui.login
import com.example.logintestingapp.data.AuthRepository
import org.junit.Assert.*
import org.junit.Test
class LoginViewModelTest {
    private class SuccessAuthRepository : AuthRepository {
        override fun login(username: String, password: String) = true
    }
    private class FailedAuthRepository : AuthRepository {
        override fun login(username: String, password: String) = false
    }
    @Test
    fun usernameChange_shouldUpdateUsernameState() {
        val viewModel = LoginViewModel(authRepository =
            SuccessAuthRepository())
        viewModel.onUsernameChange("admin")
        assertEquals("admin", viewModel.uiState.username)
    }
    @Test
    fun passwordChange_shouldUpdatePasswordState() {
        val viewModel = LoginViewModel(authRepository =
            SuccessAuthRepository())
        viewModel.onPasswordChange("123456")
        assertEquals("123456", viewModel.uiState.password)
    }

    @Test
    fun login_withEmptyUsername_shouldShowUsernameError() {
        val viewModel = LoginViewModel(authRepository =
            SuccessAuthRepository())
        viewModel.onPasswordChange("123456")
        viewModel.login()
        assertFalse(viewModel.uiState.isLoginSuccess)
        assertEquals("Username tidak boleh kosong",
            viewModel.uiState.errorMessage)
    }
    @Test
    fun login_withEmptyPassword_shouldShowPasswordError() {
        val viewModel = LoginViewModel(authRepository =
            SuccessAuthRepository())
        viewModel.onUsernameChange("admin")
        viewModel.login()
        assertFalse(viewModel.uiState.isLoginSuccess)
        assertEquals("Password tidak boleh kosong",
            viewModel.uiState.errorMessage)
    }
    @Test
    fun login_withValidCredential_shouldShowSuccess() {
        val viewModel = LoginViewModel(authRepository =
            SuccessAuthRepository())
        viewModel.onUsernameChange("admin")
        viewModel.onPasswordChange("123456")
        viewModel.login()
        assertTrue(viewModel.uiState.isLoginSuccess)
        assertNull(viewModel.uiState.errorMessage)
    }

    @Test
    fun login_withInvalidCredential_shouldShowError() {
        val viewModel = LoginViewModel(authRepository =
            FailedAuthRepository())
        viewModel.onUsernameChange("admin")
        viewModel.onPasswordChange("wrongpassword")
        viewModel.login()
        assertFalse(viewModel.uiState.isLoginSuccess)
        assertEquals("Username atau password salah",
            viewModel.uiState.errorMessage)
    }
}