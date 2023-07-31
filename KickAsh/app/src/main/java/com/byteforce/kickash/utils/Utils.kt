package com.byteforce.kickash.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

object Utils {
    fun showSimpleDialog(title: String = "",message: String, context: Context) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
        dialog.show()
    }

    fun showSimpleToast(message: String, context: Context) {
        val duration = Toast.LENGTH_SHORT

        Toast.makeText(context, message, duration).show()
    }
}