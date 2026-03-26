package com.zappshop.app.data.remote

import com.zappshop.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("{slug}/auth/login")
    suspend fun login(
        @Path("slug") slug: String,
        @Body request: LoginRequest
    ): Response<AuthResponse>

    @POST("{slug}/auth/register")
    suspend fun register(
        @Path("slug") slug: String,
        @Body request: RegisterRequest
    ): Response<AuthResponse>

    @GET("api/zappshop/products")
    suspend fun getProducts(
        @Query("search") search: String? = null,
        @Query("page") page: Int = 1
    ): Response<ZappShopResponse>

    @GET("api/zappshop/product/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): Response<AuthResponse>

    @GET("api/zappshop/categories")
    suspend fun getCategories(): Response<List<Category>>
}