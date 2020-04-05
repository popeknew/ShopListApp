package com.example.shoplistapp.repository

import androidx.lifecycle.LiveData
import com.example.shoplistapp.database.ProductDatabase
import com.example.shoplistapp.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(productDatabase: ProductDatabase) {

    private val productDao = productDatabase.productDao()

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun addProduct(product: Product) = withContext(Dispatchers.IO) {
        productDao.addProduct(product)
    }

    suspend fun deleteProduct(product: Product) = withContext(Dispatchers.IO) {
        productDao.removeProduct(product)
    }

    suspend fun updateProducts(vararg products: Product) = withContext(Dispatchers.IO) {
        productDao.updateProducts(*products)
    }
}