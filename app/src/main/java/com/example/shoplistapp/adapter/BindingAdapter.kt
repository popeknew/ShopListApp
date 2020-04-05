package com.example.shoplistapp.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.shoplistapp.R
import com.example.shoplistapp.ext.toDateString

@BindingAdapter("isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("creationDate")
fun AppCompatTextView.creationDate(created: Long) {
    text = resources.getString(R.string.created) + " " + created.toDateString()
}