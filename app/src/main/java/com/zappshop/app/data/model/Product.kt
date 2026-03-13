package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String, // Alterado para String para suportar UUID

    @SerializedName("title")
    val name: String, // Mapeado para 'title' do seu SQL

    val description: String?,

    val price: Double,

    @SerializedName("image_url")
    val imageUrl: String?,

    val stock: Int = 0,

    @SerializedName("store_name")
    val storeName: String?,

    @SerializedName("store_id")
    val storeId: String?, // Alterado para String (UUID da tabela stores)

    @SerializedName("store_logo")
    val storeLogo: String?,

    @SerializedName("category_name")
    val categoryName: String?,

    val active: Boolean = true,

    val promotion: Promotion?
)

data class Promotion(
    val name: String,

    @SerializedName("original_price")
    val originalPrice: Double,

    @SerializedName("promo_price")
    val promoPrice: Double,

    @SerializedName("discount_percent")
    val discountPercent: Int,

    @SerializedName("end_date")
    val endDate: String?
)