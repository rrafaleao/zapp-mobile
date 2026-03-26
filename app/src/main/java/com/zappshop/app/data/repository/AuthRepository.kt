package com.zappshop.app.data.repository

import com.google.gson.JsonParser
import com.zappshop.app.BuildConfig
import com.zappshop.app.data.local.SessionManager
import com.zappshop.app.data.model.*
import com.zappshop.app.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val session: SessionManager,
    private val api: ApiService
) {
    private val storeSlug = BuildConfig.STORE_SLUG

    val token: Flow<String?> = session.token
    val userName: Flow<String?> = session.userName

    suspend fun login(email: String, password: String): Result<AuthData> {
        return try {
            val response = api.login(
                slug = storeSlug,
                request = LoginRequest(email = email.trim(), password = password)
            )

            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                val payload = response.body()!!.data!!
                session.saveSession(
                    token = payload.customerId,
                    name = payload.customerName,
                    email = payload.customerEmail
                )
                Result.success(payload)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), response.body()?.error ?: "Falha no login")))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, phone: String?, password: String): Result<AuthData> {
        return try {
            val response = api.register(
                slug = storeSlug,
                request = RegisterRequest(
                    fullName = name.trim(),
                    email = email.trim(),
                    phone = phone,
                    password = password,
                    confirmPassword = password
                )
            )

            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                val payload = response.body()!!.data!!
                session.saveSession(
                    token = payload.customerId,
                    name = payload.customerName,
                    email = payload.customerEmail
                )
                Result.success(payload)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), response.body()?.error ?: "Erro no cadastro")))
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