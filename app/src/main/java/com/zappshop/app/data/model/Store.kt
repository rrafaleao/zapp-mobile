package com.zappshop.app.data.model
import com.google.gson.annotations.SerializedName

data class Store(
    val id: Int,
    val name: String,
    val slug: String,
    @SerializedName("logo_url") val logoUrl: String?,
    val description: String?
)