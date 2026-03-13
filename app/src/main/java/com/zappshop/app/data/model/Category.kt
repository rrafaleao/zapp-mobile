package com.zappshop.app.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    val name: String,
    @SerializedName("product_count") val productCount: Int = 0
)