package com.application.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CaseUpdateFragment : Fragment() {

    lateinit var recyclerViewCaseUpdateAdapter: RecyclerViewAdapter
    lateinit var recyclerViewNewsAdapter: RecyclerViewAdapter

    lateinit var recyclerViewCaseUpdate: RecyclerView
    lateinit var recyclerViewNews: RecyclerView

    private val caseUpdateList = mutableListOf<IBaseModel>()
    private val newsListBaseModel = mutableListOf<IBaseModel>()

    private lateinit var topRatedListObject: CaseUpdateDTO
    var arrayCase: ArrayList<CaseUpdateDTO> = ArrayList()
    private lateinit var newsListObject: NewsDTO

    private lateinit var getView:View

    var recyclerViewItemClickListener = object : IRecyclerViewClickListener {
        override fun onClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLongClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getView=inflater.inflate(R.layout.fragment_case_update, container, false)
        getRecyclerViewAdapter()

        var selectedCountry: String = "USA"
        var arrayCountry: ArrayList<String> = ArrayList()

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
                    caseUpdateList.add(arrayCase[0])
                    recyclerViewCaseUpdateAdapter.notifyDataSetChanged()
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
                Log.d("başarılı", "başarılı")
                response.body().let {
                    it!!.result.forEach { _result ->
                        newsListObject = NewsDTO(
                            name = _result.name,
                            description = _result.description,
                            image = _result.image,
                            url = _result.url,
                            date = _result.date,
                            source = _result.source
                        )
                        newsListBaseModel.add(newsListObject)
                        recyclerViewNewsAdapter.notifyDataSetChanged()

                    }
                }
            }
        })

        //For Spinner - Mustafa
        val spinner = getView.findViewById<Spinner>(R.id.country_spinner) as SearchableSpinner
        if (spinner != null) {
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayCountry)

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

                        Log.d("Ülkeler For", arrayCase[i].country)
                    }

                    selectedCountry = arrayCountry[position]

                    //Api Data Pull - Yusuf
                    caseUpdateList.clear()

                    if (arrayCase[position].country.equals(selectedCountry)) {

                        caseUpdateList.add(arrayCase[position])
                        recyclerViewCaseUpdateAdapter.notifyDataSetChanged()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        return getView

    }

    private fun getRecyclerViewAdapter() {
        //ReycyclerViewCaseUpdateAdapter
        recyclerViewCaseUpdate = getView.findViewById(R.id.recyclerViewCaseUpdate)
        recyclerViewCaseUpdateAdapter =
            RecyclerViewAdapter(caseUpdateList, recyclerViewItemClickListener)
        recyclerViewCaseUpdate.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewCaseUpdate.adapter = recyclerViewCaseUpdateAdapter


        //RecyclerviewNewsAdapter
        recyclerViewNews = getView.findViewById(R.id.recyclerViewNews)
        recyclerViewNewsAdapter =
            RecyclerViewAdapter(newsListBaseModel, recyclerViewItemClickListener)
        recyclerViewNews.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewNews.adapter = recyclerViewNewsAdapter
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CaseUpdateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
