package com.zappshop.app.data.model

data class User(
    val id: String,
    val name: String,
    val email: String
)

data class AuthResponse(
    val token: String,
    val user: User
)