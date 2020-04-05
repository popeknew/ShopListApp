package com.example.shoplistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoplistapp.dao.ShopListDao
import com.example.shoplistapp.model.ShopList

@Database(entities = arrayOf(ShopList::class), version = 5)
abstract class ShopListDatabase : RoomDatabase() {
    abstract fun shopListDao(): ShopListDao

    private var INSTANCE: ShopListDatabase? = null

    fun getInstance(context: Context): ShopListDatabase {
        if (INSTANCE == null) {
            INSTANCE =
                Room.inMemoryDatabaseBuilder(
                    context,
                    ShopListDatabase::class.java
                )
                    .allowMainThreadQueries()
                    .build()
        }

        return INSTANCE!!
    }
}