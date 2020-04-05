package com.example.shoplistapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.databinding.RowProductBinding
import com.example.shoplistapp.ext.hideKeyboard
import com.example.shoplistapp.ext.showKeyboard
import com.example.shoplistapp.model.Product
import com.example.shoplistapp.utils.productDiffUtilCallback

typealias onDeleteItemCallback = (Product) -> Unit
typealias onProductNameChangeCallback = (Product) -> Unit
typealias onProductCheckedCallback = (Product) -> Unit

class ProductRecyclerAdapter :
    ListAdapter<Product, ProductRecyclerAdapter.ProductRecyclerViewHolder>(productDiffUtilCallback) {

    var onDeleteProductCallback: onDeleteItemCallback? = null
    var onProductNameChangeCallback: onProductNameChangeCallback? = null
    var onProductCheckedCallback: onProductCheckedCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowProductBinding.inflate(inflater, parent, false)

        return ProductRecyclerViewHolder(
            binding,
            onDeleteProductCallback,
            onProductNameChangeCallback,
            onProductCheckedCallback
        )
    }

    override fun onBindViewHolder(holder: ProductRecyclerViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductRecyclerViewHolder(
        private val binding: RowProductBinding,
        private val deleteCallback: onDeleteItemCallback?,
        private val nameChangeCallback: onProductNameChangeCallback?,
        private val checkBoxCallback: onProductCheckedCallback?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                this.product = product
                productCardView.requestFocus()
                deleteProductButton.setOnClickListener { deleteCallback?.invoke(product) }
                productName.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        nameChangeCallback?.invoke(product)
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                })
                productCheckBox.setOnClickListener {
                    checkBoxCallback?.invoke(product)
                }
            }
        }
    }
}