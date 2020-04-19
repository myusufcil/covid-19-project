package com.application.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 14:48    
  myusufcl7@gmail.com
*/      

class RetrofitCoronaFactory {
    companion object{
        var BASE_URL="https://api.collectapi.com/countriesData/"
        fun getCovidInformation():IApiService{
            val retrofit:Retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }
}