package com.app.mydrai.core.network


import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitCallAPI {

    internal var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    private var retrofitAPIinterface: RetrofitApiInterface? = null
    fun getInstance(baseUrl: String): RetrofitApiInterface? {
        if (retrofitAPIinterface == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build()
            retrofitAPIinterface = retrofit.create(RetrofitApiInterface::class.java)
        }
        return retrofitAPIinterface
    }
}



