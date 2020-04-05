package com.example.shoplistapp.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.shoplistapp.R;

public class DeleteListDialogFragment extends DialogFragment {

    private DeleteListDialogListener listener;

    public interface DeleteListDialogListener {
        void onDialogPositiveClick(Boolean delete);
    }

    public void setDeleteListener(DeleteListDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        builder.setMessage(R.string.delete_dialog_message)
                .setPositiveButton(R.string.delete_dialog_ok_button, (dialogInterface, i) -> listener.onDialogPositiveClick(true))
                .setNegativeButton(R.string.delete_dialog_cancel_button, (dialogInterface, i) -> getDialog().cancel());
        return builder.create();
    }
}
