package com.zero.myapplication.root

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.zero.myapplication.data.db.Sessions
import com.zero.myapplication.di.module.networkModule
import com.zero.myapplication.di.module.viewModelModule
import com.zero.myapplication.utils.MakeToast
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@SuppressLint("StaticFieldLeak")
class App : Application() {

    companion object {
        lateinit var sessions: Sessions
        lateinit var makeToast: MakeToast
        lateinit var disposible: CompositeDisposable
        lateinit var context: Context

        const val TAG_BACK = 9999
    }

    override fun onCreate() {
        super.onCreate()
        sessions = Sessions(this)
        makeToast = MakeToast(this)
        disposible = CompositeDisposable()
        context = this
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    viewModelModule
                )
            )
        }
    }
}