package com.zappshop.app.data.repository

import com.zappshop.app.data.local.SessionManager
import com.zappshop.app.data.model.AuthResponse
import com.zappshop.app.data.model.User
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val session: SessionManager
) {
    suspend fun login(email: String, password: String): Result<AuthResponse> {
        val fakeUser = User(1, "Usuário Teste", email)
        val fakeResponse = AuthResponse("fake-token-123", fakeUser)
        session.saveSession(fakeResponse.token, fakeUser.name, fakeUser.email)
        return Result.success(fakeResponse)
    }

    suspend fun register(name: String, email: String, password: String): Result<AuthResponse> {
        val fakeUser = User(1, name, email)
        val fakeResponse = AuthResponse("fake-token-123", fakeUser)
        session.saveSession(fakeResponse.token, fakeUser.name, fakeUser.email)
        return Result.success(fakeResponse)
    }

    suspend fun logout() = session.clearSession()
    fun getToken() = session.token
    fun getUserName() = session.userName
}