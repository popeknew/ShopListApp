package com.example.shoplistapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistapp.model.ShopList
import com.example.shoplistapp.repository.ShopListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArchivesViewModel(private val repository: ShopListRepository) : ViewModel() {

    val archivedLists = repository.allShopLists

    fun removeList(shopList: ShopList) =
        viewModelScope.launch(Dispatchers.IO) { repository.removeList(shopList) }
}