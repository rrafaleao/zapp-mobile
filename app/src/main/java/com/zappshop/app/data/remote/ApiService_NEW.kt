package com.zappshop.app.data.remote

import com.zappshop.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<AuthData>>

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<AuthData>>

    @GET("api/zappshop/products")
    suspend fun getProducts(
        @Query("search") search: String? = null,
        @Query("category") category: String? = null,
        @Query("sort_by") sortBy: String? = null,
        @Query("page") page: Int = 1
    ): Response<PaginatedResponse<Product>>

    @GET("api/zappshop/product/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): Response<ApiResponse<Product>>

    @GET("api/zappshop/categories")
    suspend fun getCategories(): Response<ApiResponse<List<Category>>>
}
