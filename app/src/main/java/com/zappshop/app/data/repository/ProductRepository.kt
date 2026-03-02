package com.zappshop.app.data.repository

import com.zappshop.app.data.model.Product
import com.zappshop.app.data.remote.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
) {
    private val mockProducts = listOf(
        Product(1, "Tênis Nike Air", "Tênis esportivo confortável", 299.99, null, 10, "Loja do João", 1),
        Product(2, "Camiseta Básica", "Camiseta 100% algodão", 49.90, null, 50, "Moda Store", 2),
        Product(3, "Fone Bluetooth", "Som de qualidade sem fio", 189.00, null, 15, "Tech Shop", 3),
        Product(4, "Mochila Escolar", "Resistente e espaçosa", 120.00, null, 8, "Bolsas & Cia", 4),
        Product(5, "Livro Kotlin", "Aprenda Kotlin do zero", 75.00, null, 30, "Livraria Dev", 5),
        Product(6, "Mouse Gamer", "DPI ajustável, RGB", 210.00, null, 20, "Tech Shop", 3)
    )

    suspend fun getProducts(search: String? = null): Result<List<Product>> {
        val filtered = if (search.isNullOrBlank()) mockProducts
        else mockProducts.filter { it.name.contains(search, ignoreCase = true) }
        return Result.success(filtered)
    }

    suspend fun getProductById(id: Int): Result<Product> {
        val product = mockProducts.find { it.id == id }
        return if (product != null) Result.success(product)
        else Result.failure(Exception("Produto não encontrado"))
    }
}