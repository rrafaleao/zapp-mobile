package com.zappshop.app.data.repository

import com.zappshop.app.data.model.Category
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.remote.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getProducts(search: String? = null, page: Int = 1): Result<List<Product>> {
        return try {
            val response = api.getProducts(search, page)
            if (response.isSuccessful) {
                Result.success(response.body()?.data ?: emptyList())
            } else {
                Result.failure(Exception("Erro ao carregar produtos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ESTA É A FUNÇÃO QUE ESTAVA FALTANDO
    suspend fun getProductById(id: String): Result<Product> {
        return try {
            val response = api.getProductById(id)
            if (response.isSuccessful && response.body()?.success == true) {
                // Aqui retornamos o produto. Se sua API retornar dentro de .data, use response.body()?.data
                // Vou assumir que o detalhe do produto vem no campo 'data' da AuthResponse ou similar
                val product = response.body()?.data?.let {
                    Product(id = it.customerId, name = it.customerName, price = 0.0, description = "", image = "")
                }
                if (product != null) Result.success(product)
                else Result.failure(Exception("Produto nulo"))
            } else {
                Result.failure(Exception("Produto não encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = api.getCategories()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Erro categorias"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}