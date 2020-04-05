package com.example.shoplistapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Insert
import androidx.room.Delete
import com.example.shoplistapp.model.ShopList

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_list_table ORDER BY created DESC")
    fun getAllShopLists(): LiveData<List<ShopList>>

    @Update
    fun updateShopLists(vararg shopList: ShopList)

    @Insert
    fun addList(shopList: ShopList)

    @Delete
    fun removeList(shopList: ShopList)
}