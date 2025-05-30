package com.example

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.AuthRepository
import com.example.models.UserLoginParams
import com.example.models.UserRegisterParams
import com.example.models.UserTokenDTO
import com.example.util.TokenManager
import com.example.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AuthEvent {
    data class Login(val username: String, val password: String): AuthEvent
    data class Register(val username: String, val email: String, val password: String): AuthEvent
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
): ViewModel() {

    private val _authState: MutableStateFlow<String?> = MutableStateFlow(tokenManager.getToken())
    val authState: StateFlow<String?> = _authState.asStateFlow()

    fun handleEvent(event: AuthEvent) {
        when(event) {
            is AuthEvent.Login -> {
                invokeLogin(
                    username = event.username,
                    password = event.password
                )
            }
            is AuthEvent.Register -> invokeRegister(
                username = event.username,
                email = event.email,
                password = event.password
            )
        }
    }

    private fun invokeLogin(
        username: String,
        password: String
    ) {
        val userLoginParams = UserLoginParams(username = username, password = password)
        viewModelScope.launch {
            repository.login(userLoginParams = userLoginParams).collect { response ->
                response.toUserTokenDTO().token?.let { result ->
                    tokenManager.saveToken(token = result)
                    _authState.update { result }
                }
            }
        }
    }

    private fun invokeRegister(
        username: String,
        email: String,
        password: String
    ) {
        val userRegisterParams = UserRegisterParams(username = username, email = email, password = password)
        viewModelScope.launch {
            repository.register(userRegisterParams = userRegisterParams).collect { response ->
                response.toUserTokenDTO().token?.let { result ->
                    tokenManager.saveToken(token = result)
                    _authState.update { result }
                }
            }
        }
    }

    private fun RequestResult<UserTokenDTO>.toUserTokenDTO(): UserTokenDTO {
        return if(this is RequestResult.Success) {
            UserTokenDTO(token = data?.token)
        } else UserTokenDTO(token = null)
    }
}