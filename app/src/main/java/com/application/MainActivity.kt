package com.application

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.adapter.IRecyclerViewClickListener
import com.application.adapter.RecyclerViewAdapter
import com.application.covid_19.R
import com.application.dto.CaseUpdateDTO
import com.application.model.CoronaCountriesInformation
import com.application.model.IBaseModel
import com.application.network.RetrofitCoronaFactory
import kotlinx.android.synthetic.main.item_case_update_card.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewCaseUpdateAdapter: RecyclerViewAdapter
    lateinit var recyclerViewCaseUpdate: RecyclerView

    private val caseUpdateList = mutableListOf<IBaseModel>()

    var recyclerViewItemClickListener = object : IRecyclerViewClickListener {
        override fun onClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLongClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRecyclerViewAdapter()

        var selectedCountry:String="USA"
        var arrayCountry:ArrayList<String> = ArrayList()
        arrayCountry.add("Seçiniz")

        var animation:Animation



        // Ükle Dizisi çekmek için

        val apiService = RetrofitCoronaFactory.getCovidInformation()
            .getCoronaForCountries()
        apiService.enqueue(object : Callback<CoronaCountriesInformation> {
            override fun onFailure(call: Call<CoronaCountriesInformation>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<CoronaCountriesInformation>,
                response: Response<CoronaCountriesInformation>
            ) {
                response.body().let {
                    it!!.result.forEach {_result->
                        /*var item= CountryDTO(
                            country = _result.country
                        )*/
                        arrayCountry.add(_result.country)
                    }
                }
            }
        })

        //

        //For Spinner - Mustafa
        val testicerik = resources.getStringArray(R.array.test)

        val spinner = findViewById<Spinner>(R.id.country_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayCountry)

            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                    Toast.makeText(this@MainActivity, "Seç" + " " + "" + testicerik[position], Toast.LENGTH_SHORT).show()
                    selectedCountry=arrayCountry[position]

                    //Api Data Pull - Yusuf
                    caseUpdateList.clear()
                    val apiService = RetrofitCoronaFactory.getCovidInformation()
                        .getCoronaForCountries()
                    apiService.enqueue(object : Callback<CoronaCountriesInformation> {
                        override fun onFailure(call: Call<CoronaCountriesInformation>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResponse(
                            call: Call<CoronaCountriesInformation>,
                            response: Response<CoronaCountriesInformation>
                        ) {
                            response.body().let {
                                it!!.result.forEach {_result->
                                    /*var item= CountryDTO(
                                        country = _result.country
                                    )*/

                                    var topRatedListObject = CaseUpdateDTO(
                                        totalCases = _result.totalCases,
                                        totalDeaths = _result.totalDeaths,
                                        totalRecovered = _result.totalRecovered
//                            totalCases = "000000000",
//                            totalDeaths = "000000000",
//                            totalRecovered = "000000000"
                                    )

                                    if(_result.country.equals(selectedCountry)) {
                                        caseUpdateList.add(topRatedListObject)
                                        recyclerViewCaseUpdateAdapter.notifyDataSetChanged()



                                    }


                                }
                            }
                        }
                    })


                    //



                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }

            }
        }

            //For Spinner



    }

    private fun getRecyclerViewAdapter() {
        recyclerViewCaseUpdate =
            findViewById(R.id.recyclerViewCaseUpdate)
        recyclerViewCaseUpdateAdapter =
            RecyclerViewAdapter(caseUpdateList, recyclerViewItemClickListener)
        recyclerViewCaseUpdate.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewCaseUpdate.adapter = recyclerViewCaseUpdateAdapter
    }
}
