package com.prestamype.login.presentation.extensions

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.prestamype.login.R

fun View.alertSnack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length)
    snack.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryText))
    snack.show()
}