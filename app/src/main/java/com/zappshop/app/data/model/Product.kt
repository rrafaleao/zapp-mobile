package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    @SerializedName("image_url") val imageUrl: String?,
    val stock: Int,
    @SerializedName("store_name") val storeName: String,
    @SerializedName("store_id") val storeId: Int
)