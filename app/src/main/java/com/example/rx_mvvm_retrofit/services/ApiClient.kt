package com.example.rx_mvvm_retrofit.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit: Retrofit? = null
    val client: Retrofit?
        get() = retrofit
                ?: Retrofit.Builder()
                        .baseUrl(ApiService.Companion.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()

    val apiService: ApiService
        get() = client!!.create(ApiService::class.java)
}