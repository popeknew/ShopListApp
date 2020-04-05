package com.example.shoplistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.utils.shopListDiffUtilCallback
import com.example.shoplistapp.databinding.RowShopListBinding
import com.example.shoplistapp.model.ShopList

typealias onItemClickedCallback = (ShopList) -> (Unit)
typealias onLongItemClickedCallback = (ShopList) -> (Unit)

class ShopListRecyclerAdapter :
    ListAdapter<ShopList, ShopListRecyclerAdapter.ShopListRecyclerViewHolder>(shopListDiffUtilCallback) {

    var onItemClickedCallback: onItemClickedCallback? = null
    var onLongItemClickedCallback: onLongItemClickedCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowShopListBinding.inflate(inflater, parent, false)

        return ShopListRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopListRecyclerViewHolder, position: Int) {
        holder.bind(
            shopList = getItem(position),
            clickCallback = onItemClickedCallback,
            longClickCallback = onLongItemClickedCallback
        )
    }

    class ShopListRecyclerViewHolder(private val binding: RowShopListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            shopList: ShopList,
            clickCallback: onItemClickedCallback?,
            longClickCallback: onLongItemClickedCallback?
        ) {
            with(binding) {
                list = shopList
                rowShopListCardView.setOnClickListener { clickCallback?.invoke(shopList) }
                rowShopListCardView.setOnLongClickListener {
                    longClickCallback?.invoke(shopList)
                    true
                }
            }
        }
    }
}