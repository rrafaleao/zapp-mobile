package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

// Requisições
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    @SerializedName("full_name") val fullName: String,
    val email: String,
    val phone: String,
    val password: String,
    @SerializedName("confirm_password") val confirmPassword: String
)

// Respostas da API
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

// Estado da UI (Para os ViewModels)
data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val phone: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

// Modelos Base
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val description: String?,
    val image: String?, // Mantido para compatibilidade
    @SerializedName("image_url") val imageUrl: String? = null, // Adicionado para ProductCard/Detail
    @SerializedName("store_name") val storeName: String? = "ZappShop", // Adicionado
    val promotion: Promotion? = null // Adicionado para ProductDetail
)

// Procure a data class Promotion dentro do seu arquivo AuthModels.kt e substitua por esta:
data class Promotion(
    @SerializedName("original_price") val originalPrice: Double,
    @SerializedName("promo_price") val promoPrice: Double, // Adicionado para a tela de detalhes
    @SerializedName("discount_percent") val discountPercent: Int, // Adicionado para a tela de detalhes
    val name: String? = "Oferta Especial" // Adicionado para a tela de detalhes
)

data class Category(
    val id: String,
    val name: String
)