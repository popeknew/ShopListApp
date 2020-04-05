package com.example.shoplistapp.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.shoplistapp.R
import kotlinx.android.synthetic.main.add_new_list_dialog.view.*

typealias nameOfNewListCallback = (String) -> Unit

class CreateNewListDialogFragment: DialogFragment() {

    var nameOfNewListCallback: nameOfNewListCallback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogTheme)
            val dialogView = LayoutInflater.from(context).inflate(R.layout.add_new_list_dialog, null)

            builder.setView(dialogView)
                .setPositiveButton(R.string.new_list_dialog_ok_button) { _, _ ->
                    nameOfNewListCallback?.invoke(dialogView.new_list_dialog_list_name.text.toString())
                }
                .setNegativeButton(R.string.new_list_dialog_cancel_button) { _, _ ->
                    dialog?.cancel()
                }
                .setTitle(R.string.new_list_dialog_title)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }
}