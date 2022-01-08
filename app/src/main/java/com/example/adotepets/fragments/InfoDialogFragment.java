package com.example.adotepets.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.adotepets.R;

public class InfoDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.sobre_titulo);
        builder.setMessage(R.string.sobre_integrantes);
        builder.setNegativeButton("SAIR", this);
        builder.setPositiveButton("VER SITE", this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/uriellopes/AdotePets"));
                startActivity(it);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.cancel();
                break;
            default:
                break;
        }
    }
}