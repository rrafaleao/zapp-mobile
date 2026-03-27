package com.zappshop.app.data.repository

import android.os.Build
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.zappshop.app.data.model.Category
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.model.ProductsResponse
import com.zappshop.app.data.remote.ApiService
import com.zappshop.app.BuildConfig
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
) {
    private val gson = Gson()
    private val storeSlug = BuildConfig.STORE_SLUG
    private val baseUrl: String
        get() {
            val isEmulator = Build.FINGERPRINT.contains("generic", ignoreCase = true) ||
                Build.MODEL.contains("Emulator", ignoreCase = true) ||
                Build.MODEL.contains("Android SDK built for x86", ignoreCase = true)

            return if (isEmulator) "http://10.0.2.2:5000" else "http://172.20.48.116:5000"
        }

    suspend fun getProducts(
        search: String? = null,
        category: String? = null,
        sortBy: String? = null,
        page: Int = 1
    ): Result<List<Product>> {
        return try {
            val response = api.fetchProductsV1(
                search = search,
                storeSlug = storeSlug,
                categoryId = null,
                page = page,
                perPage = 20
            )
            if (response.isSuccessful) {
                val rawBody = response.body()?.string()
                if (rawBody.isNullOrBlank()) {
                    return Result.failure(Exception("Resposta vazia da API de produtos"))
                }

                val parsed = gson.fromJson(rawBody, ProductsResponse::class.java)
                if (parsed.success) {
                    val normalized = parsed.data.orEmpty().map { product ->
                        normalizeProductImage(
                            product.copy(
                            name = product.name ?: "Produto sem nome",
                            storeName = product.storeName ?: product.store?.name ?: "Loja Parceira"
                            )
                        )
                    }
                    return Result.success(normalized)
                }

                return Result.failure(Exception(parsed.error ?: "Erro ao carregar produtos"))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = try {
                    if (errorBody.isNullOrBlank()) {
                        "Erro ao carregar produtos"
                    } else {
                        val root = JsonParser.parseString(errorBody).asJsonObject
                        if (root.has("error") && !root.get("error").isJsonNull) {
                            root.get("error").asString
                        } else {
                            "Erro ao carregar produtos"
                        }
                    }
                } catch (_: Exception) {
                    "Erro ao carregar produtos"
                }
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: String): Result<Product> {
        return try {
            val response = api.getProductById(id)
            if (response.isSuccessful && response.body()?.success == true && response.body()?.data != null) {
                Result.success(normalizeProductImage(response.body()!!.data!!))
            } else {
                Result.failure(Exception(response.body()?.error ?: "Produto não encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun normalizeProductImage(product: Product): Product {
        val raw = (product.imageUrl ?: product.image)?.trim().orEmpty()
        if (raw.isBlank()) {
            return product.copy(imageUrl = null, image = null)
        }

        val normalized = when {
            raw.startsWith("http://", ignoreCase = true) || raw.startsWith("https://", ignoreCase = true) -> raw
            raw.startsWith("//") -> "https:$raw"
            raw.startsWith("/") -> "$baseUrl$raw"
            else -> "$baseUrl/$raw"
        }

        return product.copy(imageUrl = normalized, image = normalized)
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