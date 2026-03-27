package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

// ========== REQUISIÇÕES ==========
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    @SerializedName("full_name") val fullName: String,
    val email: String,
    val phone: String? = null,
    val password: String,
    @SerializedName("confirm_password") val confirmPassword: String
)

// ========== RESPOSTAS DA API ==========
data class AuthResponse(
    val success: Boolean,
    val message: String? = null,
    val error: String? = null,
    val data: AuthData? = null
)

data class AuthData(
    @SerializedName(value = "customer_id", alternate = ["user_id"]) val customerId: String?,
    @SerializedName(value = "customer_name", alternate = ["user_name", "full_name"]) val customerName: String?,
    @SerializedName(value = "customer_email", alternate = ["user_email", "email"]) val customerEmail: String?
)

data class ZappShopResponse(
    val success: Boolean,
    val data: List<Product> = emptyList(),
    val error: String? = null
)

// ========== ESTADO DA UI ==========
data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val phone: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

// ========== MODELOS DE PRODUTO E CATEGORIA ==========
data class Product(
    val id: String,
    @SerializedName(value = "name", alternate = ["title"]) val name: String? = null,
    val price: Double,
    val description: String? = null,
    @SerializedName(value = "image", alternate = ["imageUrl", "image_url"]) val image: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("store_name") val storeName: String? = "ZappShop",
    val promotion: Promotion? = null
)

data class Promotion(
    @SerializedName("original_price") val originalPrice: Double,
    @SerializedName("promo_price") val promoPrice: Double,
    @SerializedName("discount_percent") val discountPercent: Int,
    val name: String? = "Oferta Especial"
)

data class Category(
    val id: String? = null,
    val name: String
)