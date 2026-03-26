package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    @SerializedName("full_name")
    val fullName: String,
    val email: String,
    val phone: String? = null,
    val password: String,
    @SerializedName("confirm_password")
    val confirmPassword: String
)

data class StorefrontAuthPayload(
    @SerializedName("customer_id")
    val customerId: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("customer_email")
    val customerEmail: String,
    @SerializedName("redirect_url")
    val redirectUrl: String?
)