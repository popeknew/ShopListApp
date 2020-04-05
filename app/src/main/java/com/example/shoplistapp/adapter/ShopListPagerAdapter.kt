package com.example.shoplistapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.shoplistapp.fragment.ArchivesFragment
import com.example.shoplistapp.fragment.ShopListFragment

class ShopListPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ShopListFragment()
        else -> ArchivesFragment()
    }

    override fun getCount(): Int = TABS_NUMBER

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> SHOP_LIST_TITLE
        else -> ARCHIVES_TITLE
    }

    companion object {
        const val TABS_NUMBER = 2
        const val SHOP_LIST_TITLE = "Active"
        const val ARCHIVES_TITLE = "Archived"
    }
}