package com.zero.myapplication.data.network

import com.zero.myapplication.BuildConfig
import com.zero.myapplication.data.model.login.ResponseLogin
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiServices {

    private val api: ApiEndpoint

    init {
        val client = OkHttpClient().newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else
                    HttpLoggingInterceptor.Level.NONE
            })
            addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")

                return@Interceptor chain.proceed(builder.build())
            })
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }

        val server =
            "https://aplikasiku.co.id/mydoa/api/"
        api = Retrofit.Builder()
            .baseUrl(server)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(ApiEndpoint::class.java)
    }

    fun postLogin(username: String, password: String): Single<ResponseLogin> {
        return api.login(username, password)
    }

}