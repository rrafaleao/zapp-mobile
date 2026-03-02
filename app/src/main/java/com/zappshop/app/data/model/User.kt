package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String
)

data class AuthResponse(
    val token: String,
    val user: User
)