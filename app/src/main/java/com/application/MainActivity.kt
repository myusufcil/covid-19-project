package com.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.covid_19.R
import com.application.dto.CoronaCountriesDTO
import com.application.model.CoronaCountriesInformation
import com.application.network.IApiService
import com.application.network.RetrofitCoronaFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiService = RetrofitCoronaFactory.getCovidInformation().getCoronaForCountries()
        apiService.enqueue(object : Callback<CoronaCountriesInformation>{
            override fun onFailure(call: Call<CoronaCountriesInformation>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResponse(
                call: Call<CoronaCountriesInformation>,
                response: Response<CoronaCountriesInformation>
            ) {
             /*   response.body().let {
                   it!!.result.forEach {
                        var topRatedListObject = CoronaCountriesDTO(
                            it.country,
                            it.totalCases,
                            it.newCases,
                            it.totalDeaths,
                            it.newDeaths,
                            it.totalRecovered,
                            it.activeCases
                        )
                    }
                }*/
            }
        })
    }
}
