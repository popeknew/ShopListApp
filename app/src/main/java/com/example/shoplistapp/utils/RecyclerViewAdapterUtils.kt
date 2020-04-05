package com.example.shoplistapp.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.model.Product
import com.example.shoplistapp.model.ShopList

val shopListDiffUtilCallback = object : DiffUtil.ItemCallback<ShopList>() {
    override fun areItemsTheSame(oldItem: ShopList, newItem: ShopList): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: ShopList, newItem: ShopList): Boolean {
        return oldItem.archived == newItem.archived
    }
}

val productDiffUtilCallback = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.checked == newItem.checked
    }
}

fun scrollToNewItemInserted(recyclerView: RecyclerView) =
    object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)

            recyclerView.layoutManager?.scrollToPosition(positionStart)
        }
    }