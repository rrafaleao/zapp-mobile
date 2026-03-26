package com.zappshop.app.data.repository

import com.zappshop.app.data.model.*
import com.zappshop.app.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {

    private val api = RetrofitInstance.api
    private val defaultSlug = "zappshop"

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    suspend fun login(email: String, password: String): Result<AuthData> {
        return try {
            val response = api.login(defaultSlug, LoginRequest(email, password))
            val body = response.body()
            if (response.isSuccessful && body?.success == true && body.data != null) {
                _token.value = body.data.customerId
                _userName.value = body.data.customerName
                Result.success(body.data)
            } else {
                Result.failure(Exception(body?.error ?: "Falha no login"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, phone: String, pass: String): Result<AuthData> {
        return try {
            val response = api.register(defaultSlug, RegisterRequest(name, email, phone, pass, pass))
            val body = response.body()
            if (response.isSuccessful && body?.success == true && body.data != null) {
                _token.value = body.data.customerId
                _userName.value = body.data.customerName
                Result.success(body.data)
            } else {
                Result.failure(Exception(body?.error ?: "Erro no cadastro"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        _token.value = null
        _userName.value = null
    }
}