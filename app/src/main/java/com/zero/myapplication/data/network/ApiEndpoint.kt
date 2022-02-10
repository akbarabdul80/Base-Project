package com.zero.myapplication.data.network

import com.zero.myapplication.data.model.login.ResponseLogin
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {

    /**
     * Login
     */
    @FormUrlEncoded
    @POST("?data=login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Single<ResponseLogin>

}