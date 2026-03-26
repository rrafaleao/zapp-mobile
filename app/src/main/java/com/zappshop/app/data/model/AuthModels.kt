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
    @SerializedName("customer_id") val customerId: String,
    @SerializedName("customer_name") val customerName: String,
    @SerializedName("customer_email") val customerEmail: String
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
    val name: String,
    val price: Double,
    val description: String? = null,
    val image: String? = null,
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
    val id: String,
    val name: String
)