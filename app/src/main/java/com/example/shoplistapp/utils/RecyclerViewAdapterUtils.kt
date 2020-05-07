package com.example.shoplistapp.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.model.Product
import com.example.shoplistapp.model.ShopList

val shopListDiffUtilCallback = object : DiffUtil.ItemCallback<ShopList>() {
    override fun areItemsTheSame(oldItem: ShopList, newItem: ShopList): Boolean =
        oldItem.uid == newItem.uid

    override fun areContentsTheSame(oldItem: ShopList, newItem: ShopList): Boolean =
         oldItem.archived == newItem.archived
}

val productDiffUtilCallback = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.uid == newItem.uid

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.checked == newItem.checked
}

fun scrollToNewItemInserted(recyclerView: RecyclerView) =
    object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)

          //  if ()
            recyclerView.layoutManager?.scrollToPosition(positionStart)
            //recyclerView.layoutManager?.smoothScrollToPosition(recyclerView, RecyclerView.State().targetScrollPosition)
        }

/*        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)

           // recyclerView.layoutManager?.smoothScrollToPosition(recyclerView, recyclerView.state)
            recyclerView.layoutManager?.scrollToPosition(positionStart)
        }*/
    }

/*val shopListDiffUtilCallback = object : DiffUtil.ItemCallback<ShopList>() {
    override fun areItemsTheSame(oldItem: ShopList, newItem: ShopList): Boolean =
        oldItem.uid == newItem.uid

    override fun areContentsTheSame(oldItem: ShopList, newItem: ShopList): Boolean =
         oldItem.archived == newItem.archived && oldItem.created == newItem.created
}

val productDiffUtilCallback = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.checked == newItem.checked
}*/
