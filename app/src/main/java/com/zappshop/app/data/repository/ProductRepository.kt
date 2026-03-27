package com.zappshop.app.data.repository

import com.zappshop.app.data.model.Category
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.remote.ApiService
import com.zappshop.app.BuildConfig
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
) {
    private val storeSlug = BuildConfig.STORE_SLUG

    suspend fun getProducts(
        search: String? = null,
        category: String? = null,
        sortBy: String? = null,
        page: Int = 1
    ): Result<List<Product>> {
        return try {
            val response = api.getProducts(
                search = search,
                storeSlug = storeSlug,
                categoryId = null,
                page = page,
                perPage = 20
            )
            if (response.isSuccessful && response.body()?.success == true) {
                val normalized = response.body()?.data.orEmpty().map { product ->
                    product.copy(
                        name = product.name ?: "Produto sem nome",
                        storeName = product.storeName ?: product.store?.name ?: "Loja Parceira"
                    )
                }
                Result.success(normalized)
            } else {
                Result.failure(Exception(response.body()?.error ?: "Erro ao carregar produtos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: String): Result<Product> {
        return try {
            val response = api.getProductById(id)
            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.error ?: "Produto não encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = api.getCategories()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.data.orEmpty())
            } else {
                Result.failure(Exception(response.body()?.error ?: "Erro ao carregar categorias"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}