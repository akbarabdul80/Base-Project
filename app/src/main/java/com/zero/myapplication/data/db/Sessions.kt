package com.zero.myapplication.data.db

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.zero.myapplication.BuildConfig
import com.zero.myapplication.data.model.login.DataLogin

@SuppressLint("CommitPrefEdits")
class Sessions(context: Context) {
    companion object {
        var PREF_NAME = BuildConfig.APPLICATION_ID + ".session"

        const val id_user: String = "id_user"
        const val username: String = "username"
        const val fullname: String = "fullname"
        const val level: String = "level"
        const val email: String = "email"
        const val notelp: String = "notelp"
        const val no_wa: String = "no_wa"
        const val alamat: String = "alamat"
        const val catatan: String = "catatan"
        const val dateadd: String = "dateadd"
        const val lastlog: String = "lastlog"
    }

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor? = null

    var context: Context? = null
    val PRIVATE_MODE: Int = 0

    init {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun putString(key: String, value: String) {
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun getString(key: String): String {
        return pref.getString(key, "").toString()
    }

    fun isLogin(): Boolean {
        return getString(id_user).isNotEmpty()
    }

    fun Logout() {
        editor!!.clear()
        editor!!.commit()
    }

    fun doLogin(data: DataLogin?) {
        if (data != null) pref.edit {
            putString(id_user, data.id_user)
            putString(username, data.username)
            putString(email, email)
            putString(fullname, data.fullname)
            putString(notelp, data.notelp)
            putString(no_wa, no_wa)
            putString(alamat, data.alamat)
            putString(level, data.level)
            putString(catatan, catatan)
            putString(lastlog, lastlog)
        }
    }
}