package com.example.shoplistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoplistapp.dao.ProductDao
import com.example.shoplistapp.model.Product

@Database(entities = arrayOf(Product::class), version = 3)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}