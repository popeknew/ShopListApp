package com.example.shoplistapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoplistapp.model.Product
import com.example.shoplistapp.model.ShopList
import com.example.shoplistapp.repository.ProductRepository
import com.example.shoplistapp.repository.ShopListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val shopListRepository: ShopListRepository
) : ViewModel() {

    val allProducts: LiveData<List<Product>> = productRepository.allProducts

    private val allShopLists= shopListRepository.allShopLists

    fun addToArchive(listId: String, add: Boolean) {
        val currentShopList = allShopLists.value?.first { it.uid == listId }
        currentShopList?.let { shopList ->
            shopList.archived = add
            updateShopList(shopList)
        }
        allProducts.value?.let { products ->
            products.filter { it.listId == listId }.forEach {product ->
                product.archived = add
                updateProduct(product)
            }
        }
    }

    fun addProduct(product: Product) =
        viewModelScope.launch(Dispatchers.IO) { productRepository.addProduct(product) }

    fun deleteProduct(product: Product) =
        viewModelScope.launch(Dispatchers.IO) { productRepository.deleteProduct(product)}

    fun updateProduct(vararg product: Product) =
        viewModelScope.launch(Dispatchers.IO) { productRepository.updateProducts(*product) }

    private fun updateShopList(vararg shopList: ShopList) =
        viewModelScope.launch(Dispatchers.IO) { shopListRepository.updateShopList(*shopList)}
}
