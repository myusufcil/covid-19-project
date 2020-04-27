package com.application

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.adapter.IRecyclerViewClickListener
import com.application.adapter.RecyclerViewAdapter
import com.application.covid_19.R
import com.application.dto.CaseUpdateDTO
import com.application.dto.NewsDTO
import com.application.model.CoronaCountriesInformation
import com.application.model.CoronaNewsInformation
import com.application.model.IBaseModel
import com.application.network.RetrofitCoronaFactory
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewCaseUpdateAdapter: RecyclerViewAdapter
    lateinit var recyclerViewNewsAdapter: RecyclerViewAdapter

    lateinit var recyclerViewCaseUpdate: RecyclerView
    lateinit var recyclerViewNews: RecyclerView

    private val caseUpdateList = mutableListOf<IBaseModel>()
    private val newsListBaseModel = mutableListOf<IBaseModel>()

    private lateinit var topRatedListObject: CaseUpdateDTO
    var arrayCase: ArrayList<CaseUpdateDTO> = ArrayList()
    private lateinit var newsListObject: NewsDTO

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

        var selectedCountry: String = "USA"
        var arrayCountry: ArrayList<String> = ArrayList()
//        var arrayCase:ArrayList<CaseUpdateDTO> = ArrayList()

//        var spFirstClick: Boolean =
//        true // Searchable Spinner için gerekli,ilk tıklamayı yapıyor ardına hata veriyor. Uygulama açılırken tıklama yapmaması gerekiyor. -- Düzenleme - Gereksizmiş
//        var animationCase: Animation

        var sayac = 0


        var apiService = RetrofitCoronaFactory.getCovidInformation()
            .getCoronaForCountries()
        apiService.enqueue(object : Callback<CoronaCountriesInformation> {
            override fun onFailure(
                call: Call<CoronaCountriesInformation>,
                t: Throwable
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<CoronaCountriesInformation>,
                response: Response<CoronaCountriesInformation>
            ) {
                response.body().let {
                    it!!.result.forEach() { _result ->
                        topRatedListObject = CaseUpdateDTO(
                            totalCases = _result.totalCases,
                            totalDeaths = _result.totalDeaths,
                            totalRecovered = _result.totalRecovered,
                            country = _result.country
                        )
                        arrayCountry.add(_result.country)
                        arrayCase.add(topRatedListObject)

                    }
                }
            }
        })

        val newsApiService = RetrofitCoronaFactory.getCovidInformation().getCoronaNews()
        newsApiService.enqueue(object : Callback<CoronaNewsInformation> {
            override fun onFailure(call: Call<CoronaNewsInformation>, t: Throwable) {
                Log.d("başarısız", "başarısız")
            }

            override fun onResponse(
                call: Call<CoronaNewsInformation>,
                response: Response<CoronaNewsInformation>
            ) {
                Log.d("başarılı","başarılı")
               response.body().let {
                    it!!.result.forEach { _result ->
                        newsListObject = NewsDTO(
                            name = _result.name,
                            description = _result.description,
                            image = _result.image
                        )
                        newsListBaseModel.add(newsListObject)
                        recyclerViewNewsAdapter.notifyDataSetChanged()

                    }
                }
            }
        })


        // Ülkeleri çekmek için


//        apiService = RetrofitCoronaFactory.getCovidInformation()
//            .getCoronaForCountries()
//        apiService.enqueue(object : Callback<CoronaCountriesInformation> {
//            override fun onFailure(call: Call<CoronaCountriesInformation>, t: Throwable) {
//              Log.d("","")
//            }
//
//            override fun onResponse(
//                call: Call<CoronaCountriesInformation>,
//                response: Response<CoronaCountriesInformation>
//            ) {
//                response.body().let {
//                    it!!.result.forEach { _result ->
//                        /*var item= CountryDTO(
//                            country = _result.country
//                        )*/
//                        arrayCountry.add(_result.country)
//                    }
//                }
//            }
//        })

        //For Spinner - Mustafa
        val spinner = findViewById<Spinner>(R.id.country_spinner) as SearchableSpinner
        if (spinner != null) {
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayCountry)

            spinner.adapter = adapter as SpinnerAdapter?
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

//                    if (!spFirstClick) {

                    for (i in 0..arrayCase.size - 1) {
//            Toast.makeText(this,arrayCase[i].country,Toast.LENGTH_SHORT).show()
                        Log.d("Ülkeler For", arrayCase[i].country)
                    }

                    selectedCountry = arrayCountry[position]

                    //Api Data Pull - Yusuf
                    caseUpdateList.clear()

                    if (arrayCase[position].country.equals(selectedCountry)) {

                        caseUpdateList.add(arrayCase[position])
                        recyclerViewCaseUpdateAdapter.notifyDataSetChanged()

                        sayac = 1

                    }
                    //Animasyon Case Update - Mustafa
//                        if (sayac == 1) {
//                            animationCase = AnimationUtils.loadAnimation(
//                                this@MainActivity,
//                                R.anim.caseupdategelen_animation
//                            )
//                            animationCase.fillAfter = true
//                            caseUpdate_constraint.startAnimation(animationCase)
//                        }

//                    }else
//                        spFirstClick=false
//                    Toast.makeText(this@MainActivity, "Seç" + " " + "" + testicerik[position], Toast.LENGTH_SHORT).show()
                    //
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun getRecyclerViewAdapter() {
        //ReycyclerViewCaseUpdateAdapter
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


        //RecyclerviewNewsAdapter
        recyclerViewNews =
            findViewById(R.id.recyclerViewNews)
        recyclerViewNewsAdapter =
            RecyclerViewAdapter(newsListBaseModel, recyclerViewItemClickListener)
        recyclerViewNews.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewNews.adapter = recyclerViewNewsAdapter
    }
}