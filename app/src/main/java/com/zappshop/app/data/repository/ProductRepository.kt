package com.zappshop.app.data.repository

import android.os.Build
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.zappshop.app.data.model.Category
import com.zappshop.app.data.model.Product
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

                val root = JsonParser.parseString(rawBody).asJsonObject
                val success = root.get("success")?.asBoolean == true
                if (success) {
                    val products = mutableListOf<Product>()
                    val dataArray = root.getAsJsonArray("data")

                    dataArray?.forEach { item ->
                        if (!item.isJsonObject) return@forEach
                        val obj = item.asJsonObject

                        val id = obj.getAsSafeString("id") ?: return@forEach
                        val name = obj.getAsSafeString("name")
                            ?: obj.getAsSafeString("title")
                            ?: "Produto sem nome"
                        val price = obj.getAsSafeDouble("price") ?: 0.0
                        val description = obj.getAsSafeString("description")

                        // Suporta payloads diferentes: image_url, image, url, images[0].url
                        val rawImage = obj.getAsSafeString("image_url")
                            ?: obj.getAsSafeString("image")
                            ?: obj.getAsSafeString("url")
                            ?: obj.getFirstImageUrlFromArray("images")

                        val storeName = obj.getAsSafeString("store_name")
                            ?: obj.getAsJsonObject("store")?.getAsSafeString("name")
                            ?: "Loja Parceira"

                        val normalizedImage = normalizeImagePath(rawImage)
                        val product = Product(
                            id = id,
                            name = name,
                            price = price,
                            description = description,
                            image = normalizedImage,
                            imageUrl = normalizedImage,
                            storeName = storeName
                        )
                        products.add(product)
                    }

                    return Result.success(products)
                }

                return Result.failure(Exception(root.getAsSafeString("error") ?: "Erro ao carregar produtos"))
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
        val normalized = normalizeImagePath(raw)
        return product.copy(imageUrl = normalized, image = normalized)
    }

    private fun normalizeImagePath(rawPath: String?): String? {
        val raw = rawPath?.trim().orEmpty()
        if (raw.isBlank()) {
            return null
        }

        val normalized = when {
            raw.startsWith("http://", ignoreCase = true) || raw.startsWith("https://", ignoreCase = true) -> raw
            raw.startsWith("//") -> "https:$raw"
            raw.startsWith("/") -> "$baseUrl$raw"
            else -> "$baseUrl/$raw"
        }
        return normalized
    }

    private fun com.google.gson.JsonObject.getAsSafeString(name: String): String? {
        val e: JsonElement = this.get(name) ?: return null
        if (e.isJsonNull) return null
        return runCatching { e.asString }.getOrNull()?.takeIf { it.isNotBlank() }
    }

    private fun com.google.gson.JsonObject.getAsSafeDouble(name: String): Double? {
        val e: JsonElement = this.get(name) ?: return null
        if (e.isJsonNull) return null
        return runCatching { e.asDouble }.getOrNull()
            ?: runCatching { e.asString.replace(",", ".").toDouble() }.getOrNull()
    }

    private fun com.google.gson.JsonObject.getFirstImageUrlFromArray(arrayName: String): String? {
        val arr = this.getAsJsonArray(arrayName) ?: return null
        if (arr.size() == 0) return null
        val first = arr[0]
        if (!first.isJsonObject) return null
        val firstObj = first.asJsonObject
        return firstObj.getAsSafeString("url")
            ?: firstObj.getAsSafeString("image_url")
            ?: firstObj.getAsSafeString("image")
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