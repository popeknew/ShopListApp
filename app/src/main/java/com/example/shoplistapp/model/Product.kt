package com.example.shoplistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "items_table")
data class Product(
    @ColumnInfo(name = "list_id") val listId: String,
    @ColumnInfo(name = "archived") var archived: Boolean = false,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "checked") var checked: Boolean = false,
    @PrimaryKey val uid: String = UUID.randomUUID().toString()
)