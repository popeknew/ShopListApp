package com.example.shoplistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.shoplistapp.R
import com.example.shoplistapp.adapter.ShopListPagerAdapter
import com.example.shoplistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        with(binding) {
            viewPager.adapter = ShopListPagerAdapter(supportFragmentManager)
            tabLayout.setupWithViewPager(viewPager)
        }
    }
}
