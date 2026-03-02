package com.zappshop.app.data.remote

import com.zappshop.app.data.model.AuthResponse
import com.zappshop.app.data.model.LoginRequest
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Auth
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    // Produtos
    @GET("api/zappshop/products")
    suspend fun getProducts(
        @Query("page") page: Int = 1,
        @Query("search") search: String? = null,
        @Query("store_id") storeId: Int? = null,
        @Header("Authorization") token: String? = null
    ): Response<List<Product>>

    @GET("api/zappshop/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int,
        @Header("Authorization") token: String? = null
    ): Response<Product>
}