package com.zero.myapplication.data.model.login

import com.google.gson.annotations.SerializedName
import com.zero.myapplication.data.model.login.DataLogin

data class ResponseLogin(
    @SerializedName("sukses") val success: Int?,
    @SerializedName("pesan") val message: String?,
    @SerializedName("received") val data: List<DataLogin>?
)
