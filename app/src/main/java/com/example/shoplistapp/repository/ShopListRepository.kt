package com.example.shoplistapp.repository

import androidx.lifecycle.LiveData
import com.example.shoplistapp.database.ShopListDatabase
import com.example.shoplistapp.model.ShopList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class ShopListRepository(shopListDatabase: ShopListDatabase) {

    private val shopListDao = shopListDatabase.shopListDao()

    val allShopLists: LiveData<List<ShopList>> = shopListDao.getAllShopLists()

    suspend fun addList(shopList: ShopList) = withContext(Dispatchers.IO) {
        shopListDao.addList(shopList)
    }

    suspend fun removeList(shopList: ShopList) = withContext(Dispatchers.IO) {
        shopListDao.removeList(shopList)
    }

    suspend fun updateShopList(vararg shopList: ShopList) = withContext(Dispatchers.IO) {
        shopListDao.updateShopLists(*shopList)
    }
}