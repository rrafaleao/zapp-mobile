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
    val phone: String? = null,
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
    @SerializedName("customer_email") val customerEmail: String,
    @SerializedName("redirect_url") val redirectUrl: String? = null
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
    @SerializedName("title") val name: String,
    val price: Double,
    val description: String?,
    val image: String?, // Mantido para compatibilidade
    @SerializedName("image_url") val imageUrl: String? = null, // Adicionado para ProductCard/Detail
    @SerializedName("store_id") val storeId: String? = null,
    @SerializedName("store_name") val storeName: String? = "ZappShop", // Adicionado
    @SerializedName("store_logo") val storeLogo: String? = null,
    @SerializedName("category_name") val categoryName: String? = null,
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
    val id: String? = null,
    val name: String,
    @SerializedName("product_count") val productCount: Int? = null
)