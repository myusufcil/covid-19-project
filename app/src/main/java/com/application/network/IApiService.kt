package com.application.network

import com.application.model.CoronaCountriesInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 14:48    
  myusufcl7@gmail.com
*/

//https://api.collectapi.com/corona/countriesData

interface IApiService {
    @Headers(
        "authorization: apikey 2WNOzsNJx8ud5KbCBwwRA6:3yM1VFyR9Wn5kXLj7OQJW2"
        , "content-type: application/json"
    )
    @GET("countriesData/")
    fun getCoronaForCountries(): Call<CoronaCountriesInformation>


}