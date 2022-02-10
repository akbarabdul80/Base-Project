package com.zero.myapplication.data.model.login

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("id_user") val id_user : String?,
    @SerializedName("username") val username : String?,
    @SerializedName("fullname") val fullname : String?,
    @SerializedName("level") val level : String?,
    @SerializedName("notelp") val notelp : String?,
    @SerializedName("no_wa") val no_wa : String?,
    @SerializedName("alamat") val alamat : String?,
    @SerializedName("email") val email : String?,
    @SerializedName("catatan") val catatan : String?,
    @SerializedName("dateadd") val dateadd : String?,
    @SerializedName("lastlog") val lastlog : String?,
)
