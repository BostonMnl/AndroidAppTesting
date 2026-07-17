package com.example.logintestingapp.ui.login
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.material3.MaterialTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun loginScreen_shouldDisplayAllComponents() {
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen(
                    uiState = LoginUiState(),
                    onUsernameChange = {},
                    onPasswordChange = {},
                    onLoginClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText("Login Screen").assertExists()
        composeTestRule.onNodeWithTag("usernameField").assertExists()
        composeTestRule.onNodeWithTag("passwordField").assertExists()
        composeTestRule.onNodeWithTag("loginButton").assertExists()
    }
    @Test
    fun loginScreen_whenSuccess_shouldDisplaySuccessMessage() {
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen(
                    uiState = LoginUiState(
                        username = "admin",
                        password = "123456",
                        isLoginSuccess = true
                    ),
                    onUsernameChange = {},
                    onPasswordChange = {},
                    onLoginClick = {}
                )
            }
        }
        composeTestRule.onNodeWithTag("successMessage").assertExists()
        composeTestRule.onNodeWithText("Login berhasil").assertExists()
    }
    @Test
    fun loginScreen_whenError_shouldDisplayErrorMessage() {
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen(
                    uiState = LoginUiState(
                        errorMessage = "Username atau password salah"
                    ),
                    onUsernameChange = {},
                    onPasswordChange = {},
                    onLoginClick = {}
                )
            }
        }
        composeTestRule.onNodeWithTag("errorMessage").assertExists()
        composeTestRule.onNodeWithText("Username atau password salah").assertExists()
    }

    @Test
    fun usernameField_shouldAcceptInput() {
        composeTestRule.setContent {
            var uiState by remember { mutableStateOf(LoginUiState()) }
            MaterialTheme {
                LoginScreen(
                    uiState = uiState,
                    onUsernameChange = { uiState = uiState.copy(username =
                        it) },
                    onPasswordChange = { uiState = uiState.copy(password =
                        it) },
                    onLoginClick = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("usernameField").performTextInput("admin")
        composeTestRule.onNodeWithText("admin").assertExists()
    }
    @Test
    fun loginButton_shouldBeClickable() {
        var isClicked = false
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen(
                    uiState = LoginUiState(),
                    onUsernameChange = {},
                    onPasswordChange = {},
                    onLoginClick = { isClicked = true }
                )
            }
        }
        composeTestRule.onNodeWithTag("loginButton").performClick()
        assert(isClicked)
    }
    @Test
    fun loginScreen_fullFlowSuccess_shouldDisplaySuccessMessage() {
        composeTestRule.setContent {
            var uiState by remember { mutableStateOf(LoginUiState()) }
            MaterialTheme {
                LoginScreen(
                    uiState = uiState,
                    onUsernameChange = { uiState = uiState.copy(username =
                        it) },
                    onPasswordChange = { uiState = uiState.copy(password =
                        it) },
                    onLoginClick = {
                        uiState = if (uiState.username == "admin" &&
                            uiState.password == "123456") {
                            uiState.copy(isLoginSuccess = true, errorMessage =
                                null)
                        } else {
                            uiState.copy(
                                isLoginSuccess = false,
                                errorMessage = "Username atau password salah"
                            )
                        }
                    }
                )
            }
        }

        composeTestRule.onNodeWithTag("usernameField").performTextInput("admin")

        composeTestRule.onNodeWithTag("passwordField").performTextInput("123456")
        composeTestRule.onNodeWithTag("loginButton").performClick()
        composeTestRule.onNodeWithText("Login berhasil").assertExists()
    }
    @Test
    fun loginScreen_fullFlowFailed_shouldDisplayErrorMessage() {
        composeTestRule.setContent {
            var uiState by remember { mutableStateOf(LoginUiState()) }
            MaterialTheme {
                LoginScreen(
                    uiState = uiState,
                    onUsernameChange = { uiState = uiState.copy(username =
                        it) },
                    onPasswordChange = { uiState = uiState.copy(password =
                        it) },
                    onLoginClick = {
                        uiState = if (uiState.username == "admin" &&
                            uiState.password == "123456") {
                            uiState.copy(isLoginSuccess = true, errorMessage =
                                null)
                        } else {
                            uiState.copy(
                                isLoginSuccess = false,
                                errorMessage = "Username atau password salah"
                            )
                        }
                    }
                )
            }
        }

        composeTestRule.onNodeWithTag("usernameField").performTextInput("admin")

        composeTestRule.onNodeWithTag("passwordField").performTextInput("wrong")
        composeTestRule.onNodeWithTag("loginButton").performClick()
        composeTestRule.onNodeWithText("Username atau password salah").assertExists()
    }
}