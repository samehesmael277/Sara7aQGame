package com.example.sara7aq

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.sara7aq.databinding.LoadingDialogBinding

class LoadingDialog(private val context: Context) {

    private lateinit var alertDialog: AlertDialog.Builder
    private lateinit var alert: AlertDialog

    fun show() {
        alertDialog = AlertDialog.Builder(context)
        val bind: LoadingDialogBinding = LoadingDialogBinding.inflate(
            LayoutInflater.from(
                context
            )
        )
        alertDialog.setView(bind.root)
        alertDialog.setTitle("Please wait")
        alert = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.setCancelable(false)
        alert.show()
    }

    fun hide() {
        alert.dismiss()
    }
}