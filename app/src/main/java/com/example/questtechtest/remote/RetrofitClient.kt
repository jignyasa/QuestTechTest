package com.example.questtechtest.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private var retrofitClient: RestApi?=null
        var loggingInterCeptor=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        fun getInstance() : RestApi? {
            if(retrofitClient==null){
                retrofitClient= Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").client(OkHttpClient.Builder().addInterceptor(
                    loggingInterCeptor).build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(RestApi::class.java)
            }
            return retrofitClient
        }
    }
}