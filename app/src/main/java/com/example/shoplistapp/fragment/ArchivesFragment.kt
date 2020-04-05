package com.example.shoplistapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.shoplistapp.activity.ProductActivity
import com.example.shoplistapp.adapter.ShopListRecyclerAdapter
import com.example.shoplistapp.databinding.FragmentArchivesBinding
import com.example.shoplistapp.dialog.DeleteListDialogFragment
import com.example.shoplistapp.model.ShopList
import com.example.shoplistapp.vm.ArchivesViewModel
import org.koin.android.ext.android.get

class ArchivesFragment : Fragment() {

    private val viewModel: ArchivesViewModel = get()
    private lateinit var binding: FragmentArchivesBinding
    private val shopListAdapter = ShopListRecyclerAdapter()

    private val archivedListsObserver = Observer<List<ShopList>> { shopList ->
        shopList?.let { shopListAdapter.submitList(shopList.filter { it.archived }) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchivesBinding.inflate(inflater, container, false)

        with(binding) {
            viewModel = this@ArchivesFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        handleCallbacks()
    }

    private fun setupRecycler() {
        binding.recyclerView.adapter = shopListAdapter
        viewModel.archivedLists.observe(viewLifecycleOwner, archivedListsObserver)
    }

    private fun handleCallbacks() {
        with(shopListAdapter) {
            onItemClickedCallback = { shopList ->
                navigateToProducts(shopList)
            }
            onLongItemClickedCallback = { shopList ->
                deleteList(shopList)
            }
        }
    }

    private fun deleteList(shopList: ShopList) {
        val deleteDialog = DeleteListDialogFragment()
        deleteDialog.show(requireActivity().supportFragmentManager,
            ShopListFragment.DELETE_DIALOG_TAG
        )
        deleteDialog.setDeleteListener { delete ->
            if (delete) {
                viewModel.removeList(shopList)
            }
        }
    }

    private fun navigateToProducts(shopList: ShopList) =
        ProductActivity.getIntent(requireContext(), shopList).run { startActivity(this) }
}
