package com.zappshop.app.data.remote

import com.zappshop.app.data.model.ApiResponse
import com.zappshop.app.data.model.Category
import com.zappshop.app.data.model.PaginatedResponse
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.model.Store
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/zappshop/products")
    suspend fun getProducts(
        @Query("search") search: String? = null,
        @Query("category") category: String? = null,
        @Query("store_id") storeId: Int? = null,
        @Query("min_price") minPrice: Double? = null,
        @Query("max_price") maxPrice: Double? = null,
        @Query("sort_by") sortBy: String? = null,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): Response<PaginatedResponse<Product>>

    @GET("api/zappshop/product/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): Response<ApiResponse<Product>>

    @GET("api/zappshop/featured")
    suspend fun getFeaturedProducts(
        @Query("limit") limit: Int = 10
    ): Response<ApiResponse<List<Product>>>

    @GET("api/zappshop/stores")
    suspend fun getStores(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): Response<PaginatedResponse<Store>>

    @GET("api/zappshop/store/{slug}")
    suspend fun getStoreBySlug(
        @Path("slug") slug: String
    ): Response<ApiResponse<Store>>

    @GET("api/zappshop/categories")
    suspend fun getCategories(): Response<ApiResponse<List<Category>>>
}