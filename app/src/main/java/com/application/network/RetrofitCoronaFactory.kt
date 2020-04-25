package com.application.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 14:48    
  myusufcl7@gmail.com
*/

class RetrofitCoronaFactory {
    companion object{
        var BASE_URL="https://api.collectapi.com/"
        fun getCovidInformation():IApiService{

            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build()

            val retrofit:Retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }
}