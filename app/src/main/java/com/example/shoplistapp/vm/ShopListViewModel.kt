package com.example.shoplistapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistapp.model.ShopList
import com.example.shoplistapp.repository.ShopListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListViewModel(private val shopListRepository: ShopListRepository) : ViewModel() {

    val allActiveLists = shopListRepository.allShopLists

    fun addList(shopList: ShopList) =
        viewModelScope.launch(Dispatchers.IO) { shopListRepository.addList(shopList) }

    fun removeList(shopList: ShopList) =
        viewModelScope.launch(Dispatchers.IO) { shopListRepository.removeList(shopList) }
}