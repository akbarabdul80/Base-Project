package com.zero.myapplication.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.zero.myapplication.BuildConfig
import retrofit2.HttpException
import java.net.SocketTimeoutException

class MakeToast(val context: Context) {

    fun toastThrowable(error: Throwable) {
        if (error is HttpException) {
            when (error.code()) {
                400 -> {
                    Toast.makeText(context, "Silahkan cek koneksi anda", Toast.LENGTH_LONG).show()
                }
                404 -> Toast.makeText(
                    context,
                    "Url yang diminta tidak ditemukan",
                    Toast.LENGTH_LONG
                ).show()

                500 -> Toast.makeText(context, "Server sedang gangguan", Toast.LENGTH_LONG).show()

                else -> if (BuildConfig.DEBUG)
                    Toast.makeText(
                        context, "Gagal memuat data : " +
                                "${error.message()} ${error.code()}", Toast.LENGTH_LONG
                    ).show()
            }
        } else if (error is SocketTimeoutException) {
            Toast.makeText(context, "Silahkan cek koneksi anda", Toast.LENGTH_LONG).show()
        } else {
            if (BuildConfig.DEBUG)
                Toast.makeText(
                    context, "Gagal memuat data : " +
                            "${error.printStackTrace()}", Toast.LENGTH_LONG
                ).show()
        }
    }

    fun snackBar(root: View, action: () -> Unit) {
        Snackbar.make(
            root, "Gagal memuat data",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
            .setAction("Coba Lagi") {
                action()
            }.show()
    }

}