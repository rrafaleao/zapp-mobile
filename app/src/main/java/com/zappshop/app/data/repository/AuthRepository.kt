package com.zappshop.app.data.repository

import com.google.gson.JsonParser
import com.zappshop.app.BuildConfig
import com.zappshop.app.data.local.SessionManager
import com.zappshop.app.data.model.AuthResponse
import com.zappshop.app.data.model.LoginRequest
import com.zappshop.app.data.model.RegisterRequest
import com.zappshop.app.data.model.User
import com.zappshop.app.data.remote.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val session: SessionManager,
    private val api: ApiService
) {
    private val storeSlug = BuildConfig.STORE_SLUG

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.login(
                slug = storeSlug,
                request = LoginRequest(email = email.trim(), password = password)
            )

            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                val payload = response.body()!!.data!!
                val user = User(
                    id = payload.customerId,
                    name = payload.customerName,
                    email = payload.customerEmail
                )

                val authResponse = AuthResponse(token = payload.customerId, user = user)
                session.saveSession(authResponse.token, user.name, user.email)
                Result.success(authResponse)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), response.body()?.error)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.register(
                slug = storeSlug,
                request = RegisterRequest(
                    fullName = name.trim(),
                    email = email.trim(),
                    phone = null,
                    password = password,
                    confirmPassword = password
                )
            )

            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                val payload = response.body()!!.data!!
                val user = User(
                    id = payload.customerId,
                    name = payload.customerName,
                    email = payload.customerEmail
                )

                val authResponse = AuthResponse(token = payload.customerId, user = user)
                session.saveSession(authResponse.token, user.name, user.email)
                Result.success(authResponse)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), response.body()?.error)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() = session.clearSession()
    fun getToken() = session.token
    fun getUserName() = session.userName

    private fun parseApiError(errorBody: String?, fallbackError: String?): String {
        if (!errorBody.isNullOrBlank()) {
            try {
                val root = JsonParser.parseString(errorBody).asJsonObject

                if (root.has("error") && !root.get("error").isJsonNull) {
                    return root.get("error").asString
                }

                if (root.has("errors") && root.get("errors").isJsonObject) {
                    val errorsObj = root.getAsJsonObject("errors")
                    val firstError = errorsObj.entrySet().firstOrNull()?.value
                    if (firstError != null && !firstError.isJsonNull) {
                        return firstError.asString
                    }
                }
            } catch (_: Exception) {
                // Keep fallback below.
            }
        }

        return fallbackError ?: "Erro ao autenticar"
    }
}