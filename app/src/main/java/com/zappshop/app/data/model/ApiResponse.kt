package com.zappshop.app.data.model

data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val error: String? = null
)

data class Pagination(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val pages: Int,
    val has_next: Boolean,
    val has_prev: Boolean
)

data class ProductsResponse(
    val success: Boolean,
    val data: List<Product>?,
    val pagination: Pagination?,
    val error: String? = null
)