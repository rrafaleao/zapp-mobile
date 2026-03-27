package com.zappshop.app.data.remote

import com.zappshop.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    suspend fun userLogin(
        @Body request: UserLoginRequest
    ): Response<ApiResponse<AuthData>>

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<MobileAuthPayload>>

    // Legacy storefront endpoint compatibility.
    @POST("{slug}/auth/login")
    suspend fun storefrontLogin(
        @Path("slug") slug: String,
        @Body request: LoginRequest
    ): Response<ApiResponse<MobileAuthPayload>>

    @POST("api/v1/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<MobileAuthPayload>>

    // Legacy storefront endpoint compatibility.
    @POST("{slug}/auth/register")
    suspend fun storefrontRegister(
        @Path("slug") slug: String,
        @Body request: RegisterRequest
    ): Response<ApiResponse<MobileAuthPayload>>

    @GET("api/v1/products")
    suspend fun fetchProductsV1(
        @Query("search") search: String? = null,
        @Query("store_slug") storeSlug: String? = null,
        @Query("category_id") categoryId: String? = null,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): Response<ProductsResponse>

    @GET("api/zappshop/product/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): Response<ApiResponse<Product>>

    @GET("api/zappshop/categories")
    suspend fun getCategories(): Response<ApiResponse<List<Category>>>
}
