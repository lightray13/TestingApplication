package com.testing.application.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.Fragment
import com.testing.application.R

fun Fragment.createProgressDialog(context: Context?) {
    context?.let {
        val progressDialog = Dialog(context)
        progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.show()
    }
//    val progressDialog = Dialog(context)
//    progressDialog.show()
//    if (progressDialog.window != null) {
//        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    }
//    progressDialog.setContentView(R.layout.progress_dialog)
//    progressDialog.setCancelable(false)
//    progressDialog.setCanceledOnTouchOutside(false)
//    return progressDialog
}