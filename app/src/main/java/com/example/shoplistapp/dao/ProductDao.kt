package com.example.shoplistapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Insert
import androidx.room.Delete
import com.example.shoplistapp.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM items_table ORDER BY checked")
    fun getAllProducts(): LiveData<List<Product>>

    @Update
    fun updateProducts(vararg product: Product)

    @Insert
    fun addProduct(product: Product)

    @Delete
    fun removeProduct(product: Product)
}