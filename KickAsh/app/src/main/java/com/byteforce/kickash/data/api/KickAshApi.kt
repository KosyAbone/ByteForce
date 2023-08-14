package com.byteforce.kickash.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object KickAshApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build())
        .baseUrl("https://api-kickash-8fefbb641f24.herokuapp.com/").build()

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}