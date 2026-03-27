package com.zappshop.app.data.repository

import com.google.gson.JsonParser
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
    val token: Flow<String?> = session.token
    val userName: Flow<String?> = session.userName

    suspend fun login(email: String, password: String): Result<AuthData> {
        return try {
            val response = api.login(request = LoginRequest(email = email.trim(), password = password)
            )

            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                val payload = response.body()!!.data!!
                val id = payload.customerId
                val name = payload.customerName
                val email = payload.customerEmail

                if (id.isNullOrBlank() || name.isNullOrBlank() || email.isNullOrBlank()) {
                    return Result.failure(Exception("Resposta de login invalida da API"))
                }

                val authData = AuthData(
                    customerId = id,
                    customerName = name,
                    customerEmail = email
                )
                session.saveSession(
                    token = id,
                    name = name,
                    email = email
                )
                Result.success(authData)
            } else {
                Result.failure(Exception(parseApiError(response.errorBody()?.string(), response.body()?.error ?: "Falha no login")))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, phone: String?, password: String): Result<AuthData> {
        return try {
            val response = api.register(request = RegisterRequest(
                    fullName = name.trim(),
                    email = email.trim(),
                    phone = phone,
                    password = password,
                    confirmPassword = password
                )
            )

            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                val payload = response.body()!!.data!!
                val id = payload.customerId
                val nameValue = payload.customerName
                val emailValue = payload.customerEmail

                if (id.isNullOrBlank() || nameValue.isNullOrBlank() || emailValue.isNullOrBlank()) {
                    return Result.failure(Exception("Resposta de cadastro invalida da API"))
                }

                val authData = AuthData(
                    customerId = id,
                    customerName = nameValue,
                    customerEmail = emailValue
                )
                session.saveSession(
                    token = id,
                    name = nameValue,
                    email = emailValue
                )
                Result.success(authData)
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