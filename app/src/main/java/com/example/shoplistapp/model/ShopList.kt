package com.example.shoplistapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "shop_list_table")
@Parcelize
data class ShopList(
    @ColumnInfo(name = "list_name") val name: String,
    @ColumnInfo(name = "created") val created: Long = Date().time,
    @ColumnInfo(name = "archived") var archived: Boolean = false,
    @PrimaryKey val uid: String = UUID.randomUUID().toString()
) : Parcelable