package com.example.shoplistapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.shoplistapp.R
import com.example.shoplistapp.activity.ProductActivity
import com.example.shoplistapp.adapter.ShopListRecyclerAdapter
import com.example.shoplistapp.databinding.FragmentShopListBinding
import com.example.shoplistapp.dialog.CreateNewListDialogFragment
import com.example.shoplistapp.dialog.DeleteListDialogFragment
import com.example.shoplistapp.model.ShopList
import com.example.shoplistapp.vm.ShopListViewModel
import org.koin.android.ext.android.get

class ShopListFragment : Fragment() {

    private val viewModel: ShopListViewModel = get()
    private val shopListRecyclerAdapter = ShopListRecyclerAdapter()
    private lateinit var binding: FragmentShopListBinding

    private val shopListObserver = Observer<List<ShopList>> { shopList ->
        shopList?.let {
            shopListRecyclerAdapter.submitList(shopList.filter { it.archived.not() })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopListBinding.inflate(inflater, container, false)

        with(binding) {
            viewModel = this@ShopListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        handleCallbacks()
        handleAddButton()
    }

    private fun handleCallbacks() {
        shopListRecyclerAdapter.onItemClickedCallback = { shopList ->
            navigateToProducts(shopList)
        }
        shopListRecyclerAdapter.onLongItemClickedCallback = { shopList ->
            deleteList(shopList)
        }
    }

    private fun navigateToProducts(shopList: ShopList) =
        ProductActivity.getIntent(requireContext(), shopList).run { startActivity(this) }

    private fun deleteList(shopList: ShopList) {
        val deleteDialog = DeleteListDialogFragment()
        deleteDialog.show(requireActivity().supportFragmentManager, DELETE_DIALOG_TAG)
        deleteDialog.setDeleteListener { delete ->
            if (delete) {
                viewModel.removeList(shopList)
            }
        }
    }

    private fun handleAddButton() {
        binding.addNewListButton.setOnClickListener {
            val dialog =
                CreateNewListDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, NEW_LIST_DIALOG_TAG)
            dialog.nameOfNewListCallback = { listName ->
                if (listName.isNotEmpty()) {
                    val newShopList = ShopList(listName)
                    viewModel.addList(newShopList)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = shopListRecyclerAdapter
        viewModel.allActiveLists.observe(viewLifecycleOwner, shopListObserver)
    }

    companion object {
        const val NEW_LIST_DIALOG_TAG = "NewListDialog"
        const val DELETE_DIALOG_TAG = "DeleteDialog"
    }
}
