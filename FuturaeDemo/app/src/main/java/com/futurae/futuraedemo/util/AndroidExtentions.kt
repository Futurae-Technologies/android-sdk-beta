package com.futurae.futuraedemo.util

import android.app.Activity
import android.content.Context
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.futurae.sdk.public_api.session.model.ApproveSession
import timber.log.Timber

fun Context.showDialog(
    title: String,
    message: String,
    positiveButton: String,
    positiveButtonCallback: () -> Unit,
    negativeButton: String? = null,
    negativeButtonCallback: (() -> Unit)? = null,
) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButton) { dialog, which ->
            positiveButtonCallback.invoke()
            dialog.dismiss()
        }
    if (negativeButton != null && negativeButtonCallback != null) {
        builder.setNegativeButton(negativeButton) { dialog, which ->
            negativeButtonCallback.invoke()
            dialog.dismiss()
        }
    }
    builder
        .create()
        .show()
}

fun Context.showAlert(
    title: String,
    message: String,
) {
    android.os.Handler(Looper.getMainLooper()).post {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("ok") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}

fun Fragment.showAlert(
    title: String,
    message: String
) {
    Timber.i(message)
    if(this.isResumed) {
        requireContext().showAlert(title, message)
    }
}

fun Fragment.showErrorAlert(
    title: String,
    throwable: Throwable
) {
    Timber.e(throwable)
    if(this.isResumed) {
        requireContext().showAlert(title, "Error:\n${throwable.localizedMessage}")
    }
}

fun Activity.showErrorAlert(
    title: String,
    throwable: Throwable
) {
    Timber.e(throwable)
    showAlert(title, "Error:\n${throwable.localizedMessage}")
}

fun Fragment.showDialog(
    title: String,
    message: String,
    positiveButton: String,
    positiveButtonCallback: () -> Unit,
    negativeButton: String? = null,
    negativeButtonCallback: (() -> Unit)? = null,
) {
    val context = context ?: return
    context.showDialog(
        title,
        message,
        positiveButton,
        positiveButtonCallback,
        negativeButton,
        negativeButtonCallback
    )
}

fun ApproveSession.toDialogMessage() = buildString {
    append("\n")
    append(info?.joinToString(separator = "\n") {
        "${it.key}: ${it.value}"
    } ?: "")
    append("\nTimeout: $sessionTimeout")
}