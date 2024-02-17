package com.route.gpstrackerc39_gsat

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun showDialog(
    context: Context,
    message: String? = null,
    positiveButtonText: String? = null,
    onPositiveButtonClick: (() -> Unit)? = null,// (no Parameters) -> Return Type
    negativeButtonText: String? = null,
) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage(message)
    dialogBuilder.setPositiveButton(
        positiveButtonText
    ) { dialog, which ->
        onPositiveButtonClick?.invoke()
        dialog?.dismiss()
    }
    dialogBuilder.setNegativeButton(
        negativeButtonText
    ) { dialog, which -> dialog?.dismiss() }

    dialogBuilder.show()
}