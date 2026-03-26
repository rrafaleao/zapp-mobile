package com.zappshop.app.data.repository

import com.google.gson.JsonParser
import com.zappshop.app.BuildConfig
import com.zappshop.app.data.local.SessionManager
import com.zappshop.app.data.model.AuthData
import com.zappshop.app.data.model.LoginRequest
import com.zappshop.app.data.model.RegisterRequest
import com.zappshop.app.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val session: SessionManager,
    private val api: ApiService
) {
    private val defaultSlug = BuildConfig.STORE_SLUG

    val token: Flow<String?> = session.token
    val userName: Flow<String?> = session.userName

    suspend fun login(email: String, password: String): Result<AuthData> {
        return try {
            val response = api.login(defaultSlug, LoginRequest(email.trim(), password))
            val body = response.body()
            if (response.isSuccessful && body?.success == true && body.data != null) {
                session.saveSession(
                    token = body.data.customerId,
                    name = body.data.customerName,
                    email = body.data.customerEmail
                )
                Result.success(body.data)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), body?.error ?: "Falha no login")))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, phone: String, pass: String): Result<AuthData> {
        return try {
            val response = api.register(
                defaultSlug,
                RegisterRequest(
                    fullName = name.trim(),
                    email = email.trim(),
                    phone = phone.ifBlank { null },
                    password = pass,
                    confirmPassword = pass
                )
            )
            val body = response.body()
            if (response.isSuccessful && body?.success == true && body.data != null) {
                session.saveSession(
                    token = body.data.customerId,
                    name = body.data.customerName,
                    email = body.data.customerEmail
                )
                Result.success(body.data)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), body?.error ?: "Erro no cadastro")))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        session.clearSession()
    }

    private fun parseApiError(errorBody: String?, fallback: String): String {
        if (!errorBody.isNullOrBlank()) {
            try {
                val root = JsonParser.parseString(errorBody).asJsonObject
                if (root.has("error") && !root.get("error").isJsonNull) {
                    return root.get("error").asString
                }
                if (root.has("errors") && root.get("errors").isJsonObject) {
                    val first = root.getAsJsonObject("errors").entrySet().firstOrNull()?.value
                    if (first != null && !first.isJsonNull) {
                        return first.asString
                    }
                }
            } catch (_: Exception) {
            }
        }
        return fallback
    }
}