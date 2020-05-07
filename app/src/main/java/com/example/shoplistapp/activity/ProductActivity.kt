package com.example.shoplistapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.R
import com.example.shoplistapp.adapter.ProductRecyclerAdapter
import com.example.shoplistapp.databinding.ActivityProductBinding
import com.example.shoplistapp.ext.showKeyboard
import com.example.shoplistapp.model.Product
import com.example.shoplistapp.model.ShopList
import com.example.shoplistapp.utils.scrollToNewItemInserted
import com.example.shoplistapp.vm.ProductViewModel
import org.koin.android.ext.android.get

class ProductActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel = get()
    private val shopListAdapter = ProductRecyclerAdapter()
    private lateinit var binding: ActivityProductBinding
    private lateinit var currentShopList: ShopList
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private val productListObserver = Observer<List<Product>> { productList ->
        productList?.let { shopListAdapter.submitList(productList.filter { it.listId == currentShopList.uid }) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)

        currentShopList = intent.getParcelableExtra(SHOP_LIST)
        setupToolbar()

        with(binding) {
            viewModel = this@ProductActivity.viewModel
            isArchived = currentShopList.archived
            lifecycleOwner = this@ProductActivity
        }

        handleAddButton()
        handleCallback()
        setupRecyclerAdapter()
    }

    private fun setupToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        title = currentShopList.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun handleAddButton() {
        binding.addNewItemButton.setOnClickListener {
            val newProduct = Product(currentShopList.uid)
            viewModel.addProduct(newProduct)
            it.showKeyboard()
        }
    }

    private fun handleCallback() {
        with(shopListAdapter) {
            onDeleteProductCallback = { product ->
                viewModel.deleteProduct(product)
            }
            onProductNameChangeCallback = { product ->
                viewModel.updateProduct(product)
            }
            onProductCheckedCallback = { product ->
                viewModel.updateProduct(product)
            }
        }
    }

    private fun setupRecyclerAdapter() {
        binding.recyclerView.adapter = shopListAdapter
        viewModel.allProducts.observe(this, productListObserver)
        shopListAdapter.registerAdapterDataObserver(scrollToNewItemInserted(binding.recyclerView))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            if (currentShopList.archived) {
                R.menu.product_toolbar_menu_archived
            } else {
                R.menu.product_toolbar_menu
            }, menu
        )

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_to_archive -> addToArchive(currentShopList.uid, true)
            R.id.retrieve_from_archive -> addToArchive(currentShopList.uid, false)

        }
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun addToArchive(listId: String, add: Boolean) {
        viewModel.addToArchive(listId, add)
        Toast.makeText(
            this,
            if (add) getString(R.string.archived_message) else getString(R.string.unarchived_message),
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val SHOP_LIST = "shopList"

        fun getIntent(context: Context, shopList: ShopList) =
            Intent(context, ProductActivity::class.java).apply {
                putExtra(SHOP_LIST, shopList)
            }
    }
}
