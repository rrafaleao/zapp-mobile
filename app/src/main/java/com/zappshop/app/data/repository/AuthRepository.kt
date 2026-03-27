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
            val normalizedEmail = email.trim().lowercase()
            val userRequest = UserLoginRequest(email = normalizedEmail, password = password)
            val storeRequest = LoginRequest(storeSlug = storeSlug, email = normalizedEmail, password = password)

            val userResponse = api.userLogin(request = userRequest)
            if (userResponse.isSuccessful && userResponse.body()?.success == true && userResponse.body()?.data != null) {
                val payload = userResponse.body()!!.data!!
                val id = payload.customerId
                val name = payload.customerName
                val payloadEmail = payload.customerEmail

                if (id.isNullOrBlank() || name.isNullOrBlank() || payloadEmail.isNullOrBlank()) {
                    return Result.failure(Exception("Resposta de login de usuario invalida da API"))
                }

                val authData = AuthData(
                    customerId = id,
                    customerName = name,
                    customerEmail = payloadEmail
                )
                session.saveSession(
                    token = id,
                    name = name,
                    email = payloadEmail
                )
                return Result.success(authData)
            }

            val response = api.login(request = storeRequest)
            val finalResponse = if (response.isSuccessful) {
                response
            } else if (response.code() == 401 || response.code() == 404) {
                api.storefrontLogin(slug = storeSlug, request = storeRequest)
            } else {
                response
            }

            if (finalResponse.isSuccessful && finalResponse.body()?.success == true && finalResponse.body()?.data != null) {
                val payload = finalResponse.body()!!.data!!
                val customer = payload.customer
                val id = customer?.id
                val name = customer?.fullName
                val payloadEmail = customer?.email
                val tokenValue = payload.token

                if (id.isNullOrBlank() || name.isNullOrBlank() || payloadEmail.isNullOrBlank() || tokenValue.isNullOrBlank()) {
                    return Result.failure(Exception("Resposta de login invalida da API"))
                }

                val authData = AuthData(
                    customerId = id,
                    customerName = name,
                    customerEmail = payloadEmail
                )
                session.saveSession(
                    token = tokenValue,
                    name = name,
                    email = payloadEmail
                )
                Result.success(authData)
            } else {
                val fallbackMessage = if (finalResponse.code() == 404) {
                    "Loja nao encontrada (store_slug='$storeSlug'). Verifique o slug da loja no BuildConfig."
                } else {
                    "Falha no login"
                }
                Result.failure(
                    Exception(
                        parseApiError(
                            finalResponse.errorBody()?.string(),
                            finalResponse.body()?.error ?: fallbackMessage
                        )
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, phone: String?, password: String): Result<AuthData> {
        return try {
            val request = RegisterRequest(
                storeSlug = storeSlug,
                fullName = name.trim(),
                email = email.trim().lowercase(),
                phone = phone,
                password = password,
                confirmPassword = password
            )
            val response = api.register(request = request)
            val finalResponse = if (response.isSuccessful) {
                response
            } else if (response.code() == 404) {
                api.storefrontRegister(slug = storeSlug, request = request)
            } else {
                response
            }

            if (finalResponse.isSuccessful && finalResponse.body()?.success == true && finalResponse.body()?.data != null) {
                val payload = finalResponse.body()!!.data!!
                val customer = payload.customer
                val id = customer?.id
                val nameValue = customer?.fullName
                val emailValue = customer?.email
                val tokenValue = payload.token

                if (id.isNullOrBlank() || nameValue.isNullOrBlank() || emailValue.isNullOrBlank() || tokenValue.isNullOrBlank()) {
                    return Result.failure(Exception("Resposta de cadastro invalida da API"))
                }

                val authData = AuthData(
                    customerId = id,
                    customerName = nameValue,
                    customerEmail = emailValue
                )
                session.saveSession(
                    token = tokenValue,
                    name = nameValue,
                    email = emailValue
                )
                Result.success(authData)
            } else {
                val fallbackMessage = if (finalResponse.code() == 404) {
                    "Loja nao encontrada (store_slug='$storeSlug'). Verifique o slug da loja no BuildConfig."
                } else {
                    "Erro no cadastro"
                }
                Result.failure(
                    Exception(
                        parseApiError(
                            finalResponse.errorBody()?.string(),
                            finalResponse.body()?.error ?: fallbackMessage
                        )
                    )
                )
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